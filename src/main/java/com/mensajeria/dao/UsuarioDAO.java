package com.mensajeria.dao;

import com.mensajeria.db.Conexion;
import com.mensajeria.model.Usuario;
import com.mensajeria.util.Seguridad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private static final String SELECT_BASE
            = "SELECT u.id_usuario, u.id_rol, r.nombre_rol, u.nombre_usuario, u.estado "
            + "FROM USUARIO u JOIN ROL r ON r.id_rol = u.id_rol ";

    private Usuario mapear(ResultSet rs) throws SQLException {
        return new Usuario(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5));
    }

    /** Devuelve el usuario si las credenciales son correctas y esta activo; si no, null. */
    public Usuario autenticar(String nombreUsuario, String clave) throws SQLException {
        String sql = "SELECT u.id_usuario, u.id_rol, r.nombre_rol, u.nombre_usuario, u.estado, u.clave_hash "
                + "FROM USUARIO u JOIN ROL r ON r.id_rol = u.id_rol WHERE u.nombre_usuario = ?";
        try (Connection c = Conexion.getConexion(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && "Activo".equals(rs.getString(5))
                        && Seguridad.verificar(clave, rs.getString(6))) {
                    return mapear(rs);
                }
            }
        }
        return null;
    }

    public List<Usuario> listar() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        try (Connection c = Conexion.getConexion();
             PreparedStatement ps = c.prepareStatement(SELECT_BASE + "ORDER BY u.nombre_usuario");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    public void crear(String nombreUsuario, String clave, int idRol) throws SQLException {
        String sql = "INSERT INTO USUARIO (id_rol, nombre_usuario, clave_hash, estado) VALUES (?, ?, ?, 'Activo')";
        try (Connection c = Conexion.getConexion(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idRol);
            ps.setString(2, nombreUsuario);
            ps.setString(3, Seguridad.hash(clave));
            ps.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1) {
                throw new SQLException("Ya existe un usuario con ese nombre.", e);
            }
            throw e;
        }
    }

    public void actualizarRolEstado(int idUsuario, int idRol, String estado) throws SQLException {
        String sql = "UPDATE USUARIO SET id_rol = ?, estado = ? WHERE id_usuario = ?";
        try (Connection c = Conexion.getConexion(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idRol);
            ps.setString(2, estado);
            ps.setInt(3, idUsuario);
            ps.executeUpdate();
        }
    }

    public void cambiarClave(int idUsuario, String nuevaClave) throws SQLException {
        String sql = "UPDATE USUARIO SET clave_hash = ? WHERE id_usuario = ?";
        try (Connection c = Conexion.getConexion(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, Seguridad.hash(nuevaClave));
            ps.setInt(2, idUsuario);
            ps.executeUpdate();
        }
    }

    /** Si todavia no hay usuarios, crea el administrador inicial (admin / admin123). */
    public boolean asegurarAdminInicial() throws SQLException {
        try (Connection c = Conexion.getConexion();
             PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM USUARIO");
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            if (rs.getInt(1) > 0) {
                return false;
            }
        }
        int idAdmin;
        try (Connection c = Conexion.getConexion();
             PreparedStatement ps = c.prepareStatement("SELECT id_rol FROM ROL WHERE nombre_rol = 'Administrador'");
             ResultSet rs = ps.executeQuery()) {
            if (!rs.next()) {
                throw new SQLException("Falta el rol 'Administrador'. Ejecute el script sql/script_oracle.sql.");
            }
            idAdmin = rs.getInt(1);
        }
        crear("admin", "admin123", idAdmin);
        return true;
    }
}
