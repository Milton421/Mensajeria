package com.mensajeria.util;

import com.mensajeria.model.Usuario;

/** Guarda el usuario que inicio sesion. */
public final class Sesion {

    private static Usuario actual;

    private Sesion() {
    }

    public static void iniciar(Usuario u) {
        actual = u;
    }

    public static void cerrar() {
        actual = null;
    }

    public static Usuario getUsuario() {
        return actual;
    }

    public static boolean esAdministrador() {
        return actual != null && "Administrador".equalsIgnoreCase(actual.rolNombre());
    }
}
