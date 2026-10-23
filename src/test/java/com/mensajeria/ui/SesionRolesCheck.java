package com.mensajeria.ui;

import com.formdev.flatlaf.FlatLightLaf;
import com.mensajeria.dao.RolDAO;
import com.mensajeria.dao.UsuarioDAO;
import com.mensajeria.model.Rol;
import com.mensajeria.model.Usuario;
import com.mensajeria.util.Sesion;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/** Regresiones de cierre de ventanas y edición de roles, sin acceso a Oracle. */
public final class SesionRolesCheck {
    private static final Usuario ADMIN = new Usuario(1, 1, "Administrador", "admin", "Activo");
    private static final Usuario OPERADOR = new Usuario(2, 2, "Operador", "operador", "Activo");

    public static void main(String[] args) throws Exception {
        FlatLightLaf.setup();
        SwingUtilities.invokeAndWait(() -> {
            try {
                comprobarCierre();
                comprobarRoles();
                System.out.println("OK: cierre desde Inicio, reutilización de ventanas, bloqueo sin sesión y edición de roles.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                Sesion.cerrar();
                for (Window ventana : Window.getWindows()) ventana.dispose();
            }
        });
    }

    private static void comprobarCierre() throws Exception {
        Sesion.iniciar(ADMIN);
        MenuPrueba menu = new MenuPrueba();
        JButton abrir = campo(menu, "btnNuevoEnvio", MainFrame.class);
        abrir.doClick();
        NuevoEnvioFrame primera = menu.ultima;
        exigir(primera.isDisplayable(), "Inicio debe abrir el formulario de envío");
        abrir.doClick();
        exigir(menu.creadas == 1 && menu.ultima == primera, "Debe reutilizar el formulario abierto");

        primera.dispose();
        abrir.doClick();
        NuevoEnvioFrame segunda = menu.ultima;
        exigir(menu.creadas == 2 && segunda != primera, "Debe poder reabrirse tras cancelar");
        // Un evento pendiente de la ventana anterior no debe perder la referencia a la nueva.
        primera.dispatchEvent(new WindowEvent(primera, WindowEvent.WINDOW_CLOSED));
        Sesion.cerrar();
        menu.dispose();
        exigir(!segunda.isDisplayable(), "Cerrar sesión debe cerrar el envío abierto desde Inicio");
        abrir.doClick();
        exigir(menu.creadas == 2, "No debe abrir envíos sin sesión");

        NuevoEnvioFrame sinSesion = new NuevoEnvioFrame();
        JButton guardar = campo(sinSesion, "btnGuardar", NuevoEnvioFrame.class);
        guardar.doClick();
        exigir(!sinSesion.isDisplayable(), "Guardar sin sesión debe cerrar el formulario antes de validar o consultar");
    }

    private static void comprobarRoles() throws Exception {
        Sesion.iniciar(ADMIN);
        UsuariosPrueba dao = new UsuariosPrueba();
        RolDAO roles = new RolDAO() {
            @Override public List<Rol> listar() {
                return List.of(new Rol(1, "Administrador", ""), new Rol(2, "Operador", ""));
            }
        };
        UsuarioFrame usuarios = new UsuarioFrame(dao, roles);
        usuarios.refrescar();
        JTable tabla = campo(usuarios, "tabla", UsuarioFrame.class);
        JComboBox<Rol> rol = campo(usuarios, "cmbRol", UsuarioFrame.class);
        JComboBox<String> estado = campo(usuarios, "cmbEstado", UsuarioFrame.class);
        JButton actualizar = campo(usuarios, "btnActualizar", UsuarioFrame.class);
        JButton clave = campo(usuarios, "btnClave", UsuarioFrame.class);
        JTextField nombre = campo(usuarios, "txtUsuario", UsuarioFrame.class);

        tabla.setRowSelectionInterval(1, 1);
        exigir(((Rol) rol.getSelectedItem()).id() == 2, "Debe cargar el rol del operador");
        usuarios.refrescar(); // MainFrame llama a refrescar al volver al módulo.
        exigir(tabla.getSelectedRow() == -1 && nombre.getText().isEmpty() && nombre.isEnabled(),
                "La recarga debe limpiar la cuenta que se estaba editando");
        exigir(rol.getSelectedItem() == null && !actualizar.isEnabled() && !clave.isEnabled(),
                "La recarga no debe dejar un rol por defecto ni acciones sobre la cuenta anterior");
        invocar(usuarios, "actualizar");
        exigir(dao.cambios == 0, "Sin selección no debe actualizar cuentas");

        tabla.setRowSelectionInterval(1, 1);
        estado.setSelectedItem("Inactivo");
        actualizar.doClick();
        exigir(dao.cambios == 1 && dao.id == 2 && dao.rol == 2 && "Inactivo".equals(dao.estado),
                "Cambiar el estado del operador debe conservar su rol");

        tabla.setRowSelectionInterval(1, 1);
        Sesion.cerrar();
        invocar(usuarios, "actualizar");
        invocar(usuarios, "cambiarClave");
        invocar(usuarios, "crear");
        Sesion.iniciar(OPERADOR);
        invocar(usuarios, "actualizar");
        invocar(usuarios, "cambiarClave");
        invocar(usuarios, "crear");
        exigir(dao.cambios == 1 && dao.otrasEscrituras == 0,
                "Sin sesión de administrador no se deben modificar usuarios");
        usuarios.dispose();
    }

    private static final class MenuPrueba extends MainFrame {
        int creadas;
        NuevoEnvioFrame ultima;
        @Override NuevoEnvioFrame crearNuevoEnvio() {
            creadas++;
            ultima = new NuevoEnvioFrame();
            return ultima;
        }
    }

    private static final class UsuariosPrueba extends UsuarioDAO {
        int cambios, otrasEscrituras, id, rol;
        String estado;
        @Override public List<Usuario> listar() { return List.of(ADMIN, OPERADOR); }
        @Override public void actualizarRolEstado(int id, int rol, String estado) {
            cambios++;
            this.id = id;
            this.rol = rol;
            this.estado = estado;
        }
        @Override public void crear(String usuario, String clave, int rol) { otrasEscrituras++; }
        @Override public void cambiarClave(int id, String clave) { otrasEscrituras++; }
    }

    @SuppressWarnings("unchecked")
    private static <T> T campo(Object objeto, String nombre, Class<?> tipo) throws Exception {
        Field campo = tipo.getDeclaredField(nombre);
        campo.setAccessible(true);
        return (T) campo.get(objeto);
    }

    private static void invocar(Object objeto, String nombre) throws Exception {
        Method metodo = objeto.getClass().getDeclaredMethod(nombre);
        metodo.setAccessible(true);
        metodo.invoke(objeto);
    }

    private static void exigir(boolean condicion, String mensaje) {
        if (!condicion) throw new AssertionError(mensaje);
    }
}
