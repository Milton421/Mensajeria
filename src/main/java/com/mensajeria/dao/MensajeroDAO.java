package com.mensajeria.dao;

import com.mensajeria.db.Conexion;
import com.mensajeria.model.Mensajero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MensajeroDAO {

    private List<Mensajero> consultar(String where, String filtro) throws SQLException {
        List<Mensajero> lista = new ArrayList<>();
        String sql = "SELECT id_mensajero, nombre, telefono, direccion, estado FROM MENSAJERO "
                + where + " ORDER BY nombre";
        try (Connection c = Conexion.getConexion(); PreparedStatement ps = c.prepareStatement(sql)) {
            if (filtro != null) {
                ps.setString(1, filtro);
                ps.setString(2, filtro);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Mensajero(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5)));
                }
            }
        }
        return lista;
    }

    public List<Mensajero> listar(String filtro) throws SQLException {
        String f = "%" + (filtro == null ? "" : filtro.trim().toUpperCase()) + "%";
        return consultar("WHERE UPPER(nombre) LIKE ? OR UPPER(telefono) LIKE ?", f);
    }

    public List<Mensajero> listarActivos() throws SQLException {
        return consultar("WHERE estado = 'Activo'", null);
    }

    public void insertar(Mensajero x) throws SQLException {
        String sql = "INSERT INTO MENSAJERO (nombre, telefono, direccion, estado) VALUES (?, ?, ?, ?)";
        try (Connection c = Conexion.getConexion(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, x.nombre());
            ps.setString(2, ClienteDAO.vacioANull(x.telefono()));
            ps.setString(3, ClienteDAO.vacioANull(x.direccion()));
            ps.setString(4, x.estado());
            ps.executeUpdate();
        }
    }

    public void actualizar(Mensajero x) throws SQLException {
        String sql = "UPDATE MENSAJERO SET nombre = ?, telefono = ?, direccion = ?, estado = ? WHERE id_mensajero = ?";
        try (Connection c = Conexion.getConexion(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, x.nombre());
            ps.setString(2, ClienteDAO.vacioANull(x.telefono()));
            ps.setString(3, ClienteDAO.vacioANull(x.direccion()));
            ps.setString(4, x.estado());
            ps.setInt(5, x.id());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        try (Connection c = Conexion.getConexion();
             PreparedStatement ps = c.prepareStatement("DELETE FROM MENSAJERO WHERE id_mensajero = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 2292) {
                throw new SQLException("No se puede eliminar: el mensajero tiene envios asignados. "
                        + "Marquelo como Inactivo en su lugar.", e);
            }
            throw e;
        }
    }
}
