package com.mensajeria.dao;

import com.mensajeria.db.Conexion;
import com.mensajeria.model.Envio;
import com.mensajeria.model.Paquete;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EnvioDAO {

    /** Valor especial de filtro: Registrado + Asignado + En transito. */
    public static final String PENDIENTES = "*PENDIENTES*";

    private static final String SELECT_BASE
            = "SELECT e.id_envio, e.id_cliente, c.nombre, e.id_paquete, p.tipo, p.descripcion, "
            + "e.id_mensajero, m.nombre, e.fecha_envio, e.direccion_destino, e.estado, e.observaciones, "
            + "e.nombre_destinatario, e.telefono_destinatario, e.fecha_entrega, e.recibido_por "
            + "FROM ENVIO e "
            + "JOIN CLIENTE c ON c.id_cliente = e.id_cliente "
            + "JOIN PAQUETE p ON p.id_paquete = e.id_paquete "
            + "LEFT JOIN MENSAJERO m ON m.id_mensajero = e.id_mensajero ";

    private static LocalDate fecha(Date d) {
        return d == null ? null : d.toLocalDate();
    }

    private Envio mapear(ResultSet rs) throws SQLException {
        int idMens = rs.getInt(7);
        Integer idMensajero = rs.wasNull() ? null : idMens;
        return new Envio(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                rs.getString(6), idMensajero, rs.getString(8), fecha(rs.getDate(9)), rs.getString(10),
                rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14),
                fecha(rs.getDate(15)), rs.getString(16));
    }

    /**
     * Lista envios filtrando por texto (cliente, destinatario, descripcion o numero)
     * y por estado (null = todos, PENDIENTES o un estado concreto).
     */
    public List<Envio> listar(String filtro, String estado) throws SQLException {
        StringBuilder sql = new StringBuilder(SELECT_BASE);
        sql.append("WHERE (UPPER(c.nombre) LIKE ? OR UPPER(e.nombre_destinatario) LIKE ? "
                + "OR UPPER(p.descripcion) LIKE ? OR TO_CHAR(e.id_envio) LIKE ?) ");
        boolean estadoConcreto = estado != null && !estado.isEmpty() && !PENDIENTES.equals(estado);
        if (PENDIENTES.equals(estado)) {
            sql.append("AND e.estado IN ('Registrado','Asignado','En tránsito') ");
        } else if (estadoConcreto) {
            sql.append("AND e.estado = ? ");
        }
        sql.append("ORDER BY e.id_envio DESC");

        String f = "%" + (filtro == null ? "" : filtro.trim().toUpperCase()) + "%";
        List<Envio> lista = new ArrayList<>();
        try (Connection c = Conexion.getConexion(); PreparedStatement ps = c.prepareStatement(sql.toString())) {
            for (int i = 1; i <= 4; i++) {
                ps.setString(i, f);
            }
            if (estadoConcreto) {
                ps.setString(5, estado);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        }
        return lista;
    }

    /** Registra el paquete y el envio en una sola transaccion. Devuelve el id del envio. */
    public int crear(int idCliente, Paquete paquete, Integer idMensajero, String direccionDestino,
            String nombreDestinatario, String telefonoDestinatario, String observaciones) throws SQLException {
        String sqlPaq = "INSERT INTO PAQUETE (tipo, descripcion, peso, dimensiones) VALUES (?, ?, ?, ?)";
        String sqlEnv = "INSERT INTO ENVIO (id_cliente, id_paquete, id_mensajero, fecha_envio, direccion_destino, "
                + "estado, observaciones, nombre_destinatario, telefono_destinatario) "
                + "VALUES (?, ?, ?, SYSDATE, ?, ?, ?, ?, ?)";
        try (Connection c = Conexion.getConexion()) {
            c.setAutoCommit(false);
            try {
                int idPaquete;
                try (PreparedStatement ps = c.prepareStatement(sqlPaq, new String[]{"ID_PAQUETE"})) {
                    ps.setString(1, paquete.tipo());
                    ps.setString(2, paquete.descripcion());
                    if (paquete.peso() == null) {
                        ps.setNull(3, Types.NUMERIC);
                    } else {
                        ps.setDouble(3, paquete.peso());
                    }
                    ps.setString(4, ClienteDAO.vacioANull(paquete.dimensiones()));
                    ps.executeUpdate();
                    try (ResultSet k = ps.getGeneratedKeys()) {
                        k.next();
                        idPaquete = k.getInt(1);
                    }
                }
                int idEnvio;
                try (PreparedStatement ps = c.prepareStatement(sqlEnv, new String[]{"ID_ENVIO"})) {
                    ps.setInt(1, idCliente);
                    ps.setInt(2, idPaquete);
                    if (idMensajero == null) {
                        ps.setNull(3, Types.NUMERIC);
                    } else {
                        ps.setInt(3, idMensajero);
                    }
                    ps.setString(4, direccionDestino);
                    ps.setString(5, idMensajero == null ? Envio.REGISTRADO : Envio.ASIGNADO);
                    ps.setString(6, ClienteDAO.vacioANull(observaciones));
                    ps.setString(7, nombreDestinatario);
                    ps.setString(8, ClienteDAO.vacioANull(telefonoDestinatario));
                    ps.executeUpdate();
                    try (ResultSet k = ps.getGeneratedKeys()) {
                        k.next();
                        idEnvio = k.getInt(1);
                    }
                }
                c.commit();
                return idEnvio;
            } catch (SQLException e) {
                c.rollback();
                throw e;
            }
        }
    }

    /** Asigna (o reasigna) un mensajero. Solo si el envio esta Registrado, Asignado o con Entrega fallida. */
    public void asignarMensajero(int idEnvio, int idMensajero) throws SQLException {
        String actual = estadoActual(idEnvio);
        if (!(Envio.REGISTRADO.equals(actual) || Envio.ASIGNADO.equals(actual) || Envio.FALLIDA.equals(actual))) {
            throw new SQLException("No se puede asignar mensajero a un envio en estado '" + actual + "'.");
        }
        String sql = "UPDATE ENVIO SET id_mensajero = ?, estado = 'Asignado' WHERE id_envio = ?";
        try (Connection c = Conexion.getConexion(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idMensajero);
            ps.setInt(2, idEnvio);
            ps.executeUpdate();
        }
    }

    /** Estados a los que se puede pasar desde el estado actual (la asignacion se hace aparte). */
    public static List<String> siguientesEstados(String actual) {
        List<String> r = new ArrayList<>();
        if (Envio.ASIGNADO.equals(actual)) {
            r.add(Envio.EN_TRANSITO);
            r.add(Envio.CANCELADO);
        } else if (Envio.EN_TRANSITO.equals(actual)) {
            r.add(Envio.ENTREGADO);
            r.add(Envio.FALLIDA);
            r.add(Envio.CANCELADO);
        } else if (Envio.REGISTRADO.equals(actual) || Envio.FALLIDA.equals(actual)) {
            r.add(Envio.CANCELADO);
        }
        return r;
    }

    /**
     * Cambia el estado. Para "Entregado" registra la fecha de entrega y quien recibio;
     * para "Entrega fallida" o "Cancelado" el motivo se guarda en observaciones.
     */
    public void cambiarEstado(int idEnvio, String nuevoEstado, String observaciones, String recibidoPor)
            throws SQLException {
        String actual = estadoActual(idEnvio);
        if (!siguientesEstados(actual).contains(nuevoEstado)) {
            throw new SQLException("No se puede pasar de '" + actual + "' a '" + nuevoEstado + "'.");
        }
        // Las observaciones nuevas se agregan a las existentes (maximo 300 caracteres).
        String nueva = ClienteDAO.vacioANull(observaciones);
        String previa = observacionesActuales(idEnvio);
        String finalObs = nueva == null ? previa : (previa == null ? nueva : previa + " | " + nueva);
        if (finalObs != null && finalObs.length() > 300) {
            finalObs = finalObs.substring(finalObs.length() - 300);
        }
        boolean entregado = Envio.ENTREGADO.equals(nuevoEstado);
        String sql = "UPDATE ENVIO SET estado = ?, observaciones = ?, "
                + "fecha_entrega = " + (entregado ? "SYSDATE" : "fecha_entrega") + ", "
                + "recibido_por = " + (entregado ? "?" : "recibido_por") + " "
                + "WHERE id_envio = ?";
        try (Connection c = Conexion.getConexion(); PreparedStatement ps = c.prepareStatement(sql)) {
            int i = 1;
            ps.setString(i++, nuevoEstado);
            ps.setString(i++, finalObs);
            if (entregado) {
                ps.setString(i++, ClienteDAO.vacioANull(recibidoPor));
            }
            ps.setInt(i, idEnvio);
            ps.executeUpdate();
        }
    }

    private String observacionesActuales(int idEnvio) throws SQLException {
        try (Connection c = Conexion.getConexion();
             PreparedStatement ps = c.prepareStatement("SELECT observaciones FROM ENVIO WHERE id_envio = ?")) {
            ps.setInt(1, idEnvio);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getString(1) : null;
            }
        }
    }

    public String estadoActual(int idEnvio) throws SQLException {
        try (Connection c = Conexion.getConexion();
             PreparedStatement ps = c.prepareStatement("SELECT estado FROM ENVIO WHERE id_envio = ?")) {
            ps.setInt(1, idEnvio);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        }
        throw new SQLException("El envio #" + idEnvio + " no existe.");
    }

    /** Cantidad de envios por estado (para el panel de inicio). */
    public Map<String, Integer> contarPorEstado() throws SQLException {
        Map<String, Integer> mapa = new LinkedHashMap<>();
        for (String e : Envio.ESTADOS) {
            mapa.put(e, 0);
        }
        try (Connection c = Conexion.getConexion();
             PreparedStatement ps = c.prepareStatement("SELECT estado, COUNT(*) FROM ENVIO GROUP BY estado");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                mapa.put(rs.getString(1), rs.getInt(2));
            }
        }
        return mapa;
    }
}
