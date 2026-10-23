package com.mensajeria.ui;

import com.formdev.flatlaf.FlatLightLaf;
import com.mensajeria.model.Usuario;
import com.mensajeria.util.Sesion;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.Supplier;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;

/** Prueba de formularios y ciclo de ventanas sin conectar ni modificar Oracle. */
public final class FormSmokeCheck {
    public static void main(String[] args) throws Exception {
        FlatLightLaf.setup();
        SwingUtilities.invokeAndWait(() -> {
            try {
                comprobar();
                System.out.println("OK: 9 formularios, controles, permisos y ciclo de ventanas.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                for (Window ventana : Window.getWindows()) ventana.dispose();
                Sesion.cerrar();
            }
        });
    }

    private static void comprobar() throws Exception {
        Sesion.iniciar(new Usuario(1, 1, "Administrador", "Prueba UI", "Activo"));
        JFrame[] formularios = {new LoginFrame(), new MainFrame(), new InicioFrame(),
            new ClienteFrame(), new MensajeroFrame(), new UsuarioFrame(),
            new EnvioFrame(), new NuevoEnvioFrame(), new ReporteFrame()};
        Path salida = Path.of("target", "ui-preview");
        Files.createDirectories(salida);
        for (JFrame frame : formularios) {
            exigir(frame.getClass().getSuperclass() == JFrame.class, "Debe extender JFrame directamente");
            if (!(frame instanceof LoginFrame || frame instanceof MainFrame)) {
                exigir(frame.getDefaultCloseOperation() == JFrame.DISPOSE_ON_CLOSE,
                        "Cerrar un módulo no debe terminar la aplicación");
            }
            frame.validate();
            revisarControles(frame.getContentPane());
            Container contenido = frame.getContentPane();
            BufferedImage imagen = new BufferedImage(contenido.getWidth(), contenido.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = imagen.createGraphics();
            contenido.printAll(g);
            g.dispose();
            ImageIO.write(imagen, "png", salida.resolve(frame.getClass().getSimpleName() + ".png").toFile());
        }

        MainFrame menu = (MainFrame) formularios[1];
        Map<String, Supplier<JFrame>> fabricas = campo(menu, "fabricas");
        Map<String, JFrame> ventanas = campo(menu, "ventanas");
        fabricas.put("Prueba", ModuloPrueba::new);
        menu.mostrar("Prueba");
        ModuloPrueba primera = (ModuloPrueba) ventanas.get("Prueba");
        Map<String, Container> vistas = campo(menu, "vistasModulos");
        Container vista = vistas.get("Prueba");
        exigir(primera.getContentPane() != vista,
                "La ventana original no debe conservar el contenido insertado en el principal");
        menu.validate();
        primera.getRootPane().doLayout();
        exigir(vista.getY() == 0,
                "El layout de la ventana original no debe desplazar el módulo ni dejar una franja superior");
        exigir(!primera.isVisible() && SwingUtilities.isDescendingFrom(((Map<String, java.awt.Container>) campo(menu, "vistasModulos")).get("Prueba"), menu),
                "El módulo debe mostrarse dentro del principal con navegación persistente" );
        menu.mostrar("Prueba");
        exigir(ventanas.get("Prueba") == primera && primera.recargas == 2,
                "Reabrir un módulo debe reutilizarlo y refrescar sus datos");
        menu.mostrar("Inicio");
        exigir(campo(menu, "contenidoActivo") == campo(menu, "pie"),
                "Inicio debe restaurar el panel principal después de abrir un módulo");
        menu.mostrar("Inicio");
        menu.mostrar("Prueba");
        exigir(ventanas.get("Prueba") == primera && primera.recargas == 3,
                "Se debe poder alternar entre Inicio y un módulo sin perder su instancia");
        primera.dispose();
        menu.mostrar("Prueba");
        JFrame segunda = ventanas.get("Prueba");
        exigir(segunda != primera && segunda.isDisplayable(), "Se debe poder reabrir una ventana cerrada");

        EnvioFrame envios = (EnvioFrame) formularios[6];
        NuevoEnvioFrame nuevo = (NuevoEnvioFrame) formularios[7];
        Field hijo = EnvioFrame.class.getDeclaredField("nuevoEnvio");
        hijo.setAccessible(true);
        hijo.set(envios, nuevo);
        ventanas.put("Envíos", envios);
        menu.dispose();
        exigir(!segunda.isDisplayable() && !envios.isDisplayable() && !nuevo.isDisplayable(),
                "Cerrar la sesión debe liberar módulos y formularios de envío");

        ClienteFrame clientes = (ClienteFrame) formularios[3];
        clientes.dispatchEvent(new java.awt.event.WindowEvent(clientes, java.awt.event.WindowEvent.WINDOW_CLOSING));
        exigir(!clientes.isDisplayable(), "Cerrar la ventana de Clientes debe liberarla");

        Sesion.iniciar(new Usuario(2, 2, "Operador", "Operador UI", "Activo"));
        MainFrame operador = new MainFrame();
        JButton usuarios = campo(operador, "btnUsuarios");
        Map<String, Supplier<JFrame>> modulosOperador = campo(operador, "fabricas");
        exigir(!usuarios.isVisible() && !modulosOperador.containsKey("Usuarios"), "Usuarios solo disponible para administrador");
        MensajeroFrame mensajeros = new MensajeroFrame();
        JButton guardar = campo(mensajeros, "btnGuardar");
        exigir(!guardar.isEnabled(), "El operador no debe editar mensajeros");
    }

    private static void revisarControles(Container contenedor) {
        for (Component c : contenedor.getComponents()) {
            if (!c.isVisible()) continue;
            if (c instanceof JButton boton && !boton.getText().isEmpty()) {
                exigir(boton.getActionListeners().length == 1, "Evento faltante o duplicado: " + boton.getText());
                exigir(boton.getWidth() > 0 && boton.getHeight() > 0, "Botón sin tamaño: " + boton.getText());
            }
            if (c instanceof JTextField) exigir(c.getHeight() >= 28, "Campo de texto demasiado pequeño");
            if (c instanceof Container hijo) revisarControles(hijo);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T campo(Object objeto, String nombre) throws Exception {
        Field f = objeto.getClass().getDeclaredField(nombre);
        f.setAccessible(true);
        return (T) f.get(objeto);
    }

    private static void exigir(boolean condicion, String mensaje) {
        if (!condicion) throw new AssertionError(mensaje);
    }

    private static final class ModuloPrueba extends JFrame implements VistaModulo {
        int recargas;
        @Override public void refrescar() { recargas++; }
    }
}
