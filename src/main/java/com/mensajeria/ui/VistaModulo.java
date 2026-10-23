package com.mensajeria.ui;

/** Contrato de las ventanas cuyos datos se recargan desde el menú principal. */
public interface VistaModulo {
    void refrescar();

    /** Regresa al principal cuando el formulario se presenta dentro de él. */
    default void cerrarVista(javax.swing.JFrame ventana) {
        Object accion = ((javax.swing.JComponent) ventana.getContentPane()).getClientProperty("cerrarModulo");
        if (accion instanceof Runnable volver) volver.run();
        else ventana.dispose();
    }
}
