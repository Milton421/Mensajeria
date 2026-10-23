package com.mensajeria.dao;

import com.mensajeria.db.Conexion;
import com.mensajeria.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public List<Cliente> listar(String filtro) throws SQLException {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT id_cliente, nombre, telefono, direccion, correo FROM CLIENTE "
                + "WHERE UPPER(nombre) LIKE ? OR UPPER(telefono) LIKE ? OR UPPER(correo) LIKE ? "
                + "ORDER BY nombre";
        String f = "%" + (filtro == null ? "" : filtro.trim().toUpperCase()) + "%";
        try (Connection c = Conexion.getConexion(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, f);
            ps.setString(2, f);
            ps.setString(3, f);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5)));
                }
            }
        }
        return lista;
    }

    public void insertar(Cliente x) throws SQLException {
        String sql = "INSERT INTO CLIENTE (nombre, telefono, direccion, correo) VALUES (?, ?, ?, ?)";
        try (Connection c = Conexion.getConexion(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, x.nombre());
            ps.setString(2, vacioANull(x.telefono()));
            ps.setString(3, vacioANull(x.direccion()));
            ps.setString(4, vacioANull(x.correo()));
            ps.executeUpdate();
        }
    }

    public void actualizar(Cliente x) throws SQLException {
        String sql = "UPDATE CLIENTE SET nombre = ?, telefono = ?, direccion = ?, correo = ? WHERE id_cliente = ?";
        try (Connection c = Conexion.getConexion(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, x.nombre());
            ps.setString(2, vacioANull(x.telefono()));
            ps.setString(3, vacioANull(x.direccion()));
            ps.setString(4, vacioANull(x.correo()));
            ps.setInt(5, x.id());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        try (Connection c = Conexion.getConexion();
             PreparedStatement ps = c.prepareStatement("DELETE FROM CLIENTE WHERE id_cliente = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 2292) {
                throw new SQLException("No se puede eliminar: el cliente tiene envios registrados.", e);
            }
            throw e;
        }
    }

    static String vacioANull(String s) {
        return (s == null || s.trim().isEmpty()) ? null : s.trim();
    }
}
