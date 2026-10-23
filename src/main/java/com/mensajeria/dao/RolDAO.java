package com.mensajeria.dao;

import com.mensajeria.db.Conexion;
import com.mensajeria.model.Rol;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RolDAO {

    public List<Rol> listar() throws SQLException {
        List<Rol> lista = new ArrayList<>();
        String sql = "SELECT id_rol, nombre_rol, descripcion FROM ROL ORDER BY id_rol";
        try (Connection c = Conexion.getConexion();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Rol(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        }
        return lista;
    }
}
