package com.mensajeria.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Administra la conexion a Oracle. Lee la configuracion de un archivo
 * "db.properties" ubicado junto a la aplicacion,
 * del que viene dentro del proyecto (src/main/resources/db.properties).
 */
public final class Conexion {

    private static Properties props;

    private Conexion() {
    }

    private static synchronized Properties cargar() throws SQLException {
        if (props != null) {
            return props;
        }
        Properties p = new Properties();
        File externo = new File("db.properties");
        try {
            if (externo.exists()) {
                try (InputStream in = new FileInputStream(externo)) {
                    p.load(in);
                }
            } else {
                try (InputStream in = Conexion.class.getResourceAsStream("/db.properties")) {
                    if (in == null) {
                        throw new SQLException("No se encontro el archivo db.properties");
                    }
                    p.load(in);
                }
            }
        } catch (IOException e) {
            throw new SQLException("No se pudo leer db.properties: " + e.getMessage(), e);
        }
        props = p;
        return props;
    }

    public static Connection getConexion() throws SQLException {
        Properties p = cargar();
        return DriverManager.getConnection(
                p.getProperty("db.url"),
                p.getProperty("db.user"),
                p.getProperty("db.password"));
    }

    /** Prueba rapida de conexion; devuelve null si todo esta bien o el mensaje de error. */
    public static String probar() {
        try (Connection c = getConexion()) {
            return c.isValid(3) ? null : "La conexion no es valida";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
}
