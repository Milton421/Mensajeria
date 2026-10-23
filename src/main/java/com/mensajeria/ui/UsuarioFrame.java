package com.mensajeria.ui;

import com.mensajeria.dao.RolDAO;
import com.mensajeria.dao.UsuarioDAO;
import com.mensajeria.model.Rol;
import com.mensajeria.model.Usuario;
import com.mensajeria.util.Estilo;
import com.mensajeria.util.Sesion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/** Administracion de usuarios autorizados (solo administrador). */
public class UsuarioFrame extends javax.swing.JFrame implements VistaModulo {

    private final UsuarioDAO dao;
    private final RolDAO rolDAO;
    private List<Usuario> datos = new ArrayList<>();
    private int idSeleccionado = 0;

    private final DefaultTableModel modelo = Estilo.modelo("ID", "Usuario", "Rol", "Estado");


    public UsuarioFrame() {
        this(new UsuarioDAO(), new RolDAO());
    }

    UsuarioFrame(UsuarioDAO dao, RolDAO rolDAO) {
        this.dao = dao;
        this.rolDAO = rolDAO;
        initComponents();
        tabla.setModel(modelo);
        Estilo.configurarCabeceraTabla(tabla);
        tabla.getColumnModel().getColumn(0).setMaxWidth(70);
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabla.getSelectedRow() >= 0) {
                Usuario u = datos.get(tabla.convertRowIndexToModel(tabla.getSelectedRow()));
                idSeleccionado = u.id();
                txtUsuario.setText(u.nombreUsuario());
                txtUsuario.setEnabled(false);
                txtClave.setText("");
                cmbRol.setSelectedIndex(-1);
                for (int i = 0; i < cmbRol.getItemCount(); i++) {
                    if (cmbRol.getItemAt(i).id() == u.idRol()) {
                        cmbRol.setSelectedIndex(i);
                    }
                }
                cmbEstado.setSelectedItem(u.estado());
                actualizarBotones();
            }
        });
        actualizarBotones();
        tabla.getColumnModel().getColumn(0).setPreferredWidth(58);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(120);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        raiz = new javax.swing.JPanel();
        cabecera = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        contenido = new javax.swing.JPanel();
        formulario = new javax.swing.JPanel();
        campos = new javax.swing.JPanel();
        filaUsuario = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        filaClave = new javax.swing.JPanel();
        lblClave = new javax.swing.JLabel();
        txtClave = new javax.swing.JPasswordField();
        filaRol = new javax.swing.JPanel();
        lblRol = new javax.swing.JLabel();
        cmbRol = new javax.swing.JComboBox<>();
        filaEstado = new javax.swing.JPanel();
        lblEstado = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        acciones = new javax.swing.JPanel();
        btnCrear = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnClave = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        listado = new javax.swing.JPanel();
        scrollTabla = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Usuarios del sistema");
        setMinimumSize(new java.awt.Dimension(1000, 660));
        setPreferredSize(new java.awt.Dimension(1180, 740));

        raiz.setBackground(new java.awt.Color(243, 245, 247));
        raiz.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 28, 24, 28));
        raiz.setLayout(new java.awt.BorderLayout(16, 18));

        cabecera.setBackground(new java.awt.Color(243, 245, 247));
        cabecera.setLayout(new java.awt.BorderLayout(12, 12));

        lblTitulo.setForeground(new java.awt.Color(30, 36, 51));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitulo.setText("Usuarios del sistema");
        cabecera.add(lblTitulo, java.awt.BorderLayout.CENTER);

        raiz.add(cabecera, java.awt.BorderLayout.NORTH);

        contenido.setBackground(new java.awt.Color(243, 245, 247));
        contenido.setLayout(new java.awt.BorderLayout(16, 0));

        formulario.setBackground(new java.awt.Color(243, 245, 247));
        formulario.setPreferredSize(new java.awt.Dimension(340, 0));
        formulario.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(212, 220, 227)), javax.swing.BorderFactory.createEmptyBorder(14, 12, 12, 12)));
        formulario.setLayout(new java.awt.BorderLayout(12, 12));

        campos.setBackground(new java.awt.Color(243, 245, 247));
        campos.setAlignmentX(0.49834985F);
        campos.setLayout(new javax.swing.BoxLayout(campos, javax.swing.BoxLayout.Y_AXIS));

        filaUsuario.setBackground(new java.awt.Color(243, 245, 247));
        filaUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0));
        filaUsuario.setLayout(new java.awt.BorderLayout(0, 6));

        lblUsuario.setForeground(new java.awt.Color(30, 36, 51));
        lblUsuario.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblUsuario.setText("Nombre de usuario *");
        filaUsuario.add(lblUsuario, java.awt.BorderLayout.NORTH);

        txtUsuario.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtUsuario.setMinimumSize(new java.awt.Dimension(140, 34));
        txtUsuario.setPreferredSize(new java.awt.Dimension(180, 34));
        filaUsuario.add(txtUsuario, java.awt.BorderLayout.CENTER);

        campos.add(filaUsuario);

        filaClave.setBackground(new java.awt.Color(243, 245, 247));
        filaClave.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0));
        filaClave.setLayout(new java.awt.BorderLayout(0, 6));

        lblClave.setForeground(new java.awt.Color(30, 36, 51));
        lblClave.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblClave.setText("Contraseña (nuevo usuario o nueva clave)");
        filaClave.add(lblClave, java.awt.BorderLayout.NORTH);

        txtClave.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtClave.setMinimumSize(new java.awt.Dimension(140, 34));
        txtClave.setPreferredSize(new java.awt.Dimension(180, 34));
        filaClave.add(txtClave, java.awt.BorderLayout.CENTER);

        campos.add(filaClave);

        filaRol.setBackground(new java.awt.Color(243, 245, 247));
        filaRol.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0));
        filaRol.setLayout(new java.awt.BorderLayout(0, 6));

        lblRol.setForeground(new java.awt.Color(30, 36, 51));
        lblRol.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblRol.setText("Rol *");
        filaRol.add(lblRol, java.awt.BorderLayout.NORTH);

        cmbRol.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbRol.setPreferredSize(new java.awt.Dimension(180, 34));
        filaRol.add(cmbRol, java.awt.BorderLayout.CENTER);

        campos.add(filaRol);

        filaEstado.setBackground(new java.awt.Color(243, 245, 247));
        filaEstado.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0));
        filaEstado.setLayout(new java.awt.BorderLayout(0, 6));

        lblEstado.setForeground(new java.awt.Color(30, 36, 51));
        lblEstado.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblEstado.setText("Estado");
        filaEstado.add(lblEstado, java.awt.BorderLayout.NORTH);

        cmbEstado.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbEstado.setPreferredSize(new java.awt.Dimension(180, 34));
        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        filaEstado.add(cmbEstado, java.awt.BorderLayout.CENTER);

        campos.add(filaEstado);

        acciones.setBackground(new java.awt.Color(243, 245, 247));
        acciones.setLayout(new java.awt.GridLayout(0, 2, 7, 7));

        btnCrear.setBackground(new java.awt.Color(245, 166, 35));
        btnCrear.setForeground(new java.awt.Color(20, 33, 61));
        btnCrear.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnCrear.setPreferredSize(new java.awt.Dimension(150, 38));
        btnCrear.setText("Crear usuario");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        acciones.add(btnCrear);

        btnActualizar.setForeground(new java.awt.Color(20, 33, 61));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnActualizar.setPreferredSize(new java.awt.Dimension(150, 38));
        btnActualizar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 226, 236)), javax.swing.BorderFactory.createEmptyBorder(7, 12, 7, 12)));
        btnActualizar.setText("Guardar rol/estado");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        acciones.add(btnActualizar);

        btnClave.setForeground(new java.awt.Color(20, 33, 61));
        btnClave.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnClave.setPreferredSize(new java.awt.Dimension(150, 38));
        btnClave.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 226, 236)), javax.swing.BorderFactory.createEmptyBorder(7, 12, 7, 12)));
        btnClave.setText("Cambiar contraseña");
        btnClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClaveActionPerformed(evt);
            }
        });
        acciones.add(btnClave);

        btnNuevo.setForeground(new java.awt.Color(20, 33, 61));
        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnNuevo.setPreferredSize(new java.awt.Dimension(150, 38));
        btnNuevo.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 226, 236)), javax.swing.BorderFactory.createEmptyBorder(7, 12, 7, 12)));
        btnNuevo.setText("Limpiar formulario");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        acciones.add(btnNuevo);

        campos.add(acciones);

        formulario.add(campos, java.awt.BorderLayout.NORTH);

        contenido.add(formulario, java.awt.BorderLayout.WEST);

        listado.setBackground(new java.awt.Color(255, 255, 255));
        listado.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        listado.setLayout(new java.awt.BorderLayout(12, 12));

        tabla.setForeground(new java.awt.Color(30, 36, 51));
        tabla.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Usuario", "Rol", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.setRowHeight(32);
        tabla.setFillsViewportHeight(true);
        tabla.setSelectionBackground(new java.awt.Color(223, 234, 243));
        tabla.setSelectionForeground(new java.awt.Color(20, 33, 61));
        tabla.setGridColor(new java.awt.Color(229, 234, 240));
        tabla.setShowHorizontalLines(true);
        scrollTabla.setViewportView(tabla);

        listado.add(scrollTabla, java.awt.BorderLayout.CENTER);

        contenido.add(listado, java.awt.BorderLayout.CENTER);

        raiz.add(contenido, java.awt.BorderLayout.CENTER);

        getContentPane().add(raiz, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        crear();
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClaveActionPerformed
        cambiarClave();
    }//GEN-LAST:event_btnClaveActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiar();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void actualizarBotones() {
        boolean haySel = idSeleccionado != 0;
        btnCrear.setEnabled(!haySel);
        btnActualizar.setEnabled(haySel);
        btnClave.setEnabled(haySel);
    }

    @Override
    public void refrescar() {
        limpiar();
        if (!Sesion.esAdministrador()) return;
        Estilo.intentar(this, () -> {
            List<Rol> roles = rolDAO.listar();
            List<Usuario> usuarios = dao.listar();
            cmbRol.removeAllItems();
            for (Rol r : roles) {
                cmbRol.addItem(r);
            }
            cmbRol.setSelectedIndex(-1);
            datos = usuarios;
            modelo.setRowCount(0);
            for (Usuario u : datos) {
                modelo.addRow(new Object[]{u.id(), u.nombreUsuario(), u.rolNombre(), u.estado()});
            }
        });
    }

    private void limpiar() {
        idSeleccionado = 0;
        txtUsuario.setText("");
        txtUsuario.setEnabled(true);
        txtClave.setText("");
        cmbEstado.setSelectedIndex(0);
        cmbRol.setSelectedIndex(-1);
        tabla.clearSelection();
        actualizarBotones();
    }

    private void crear() {
        if (!Sesion.esAdministrador()) return;
        String nombre = txtUsuario.getText().trim();
        String clave = new String(txtClave.getPassword());
        if (!nombre.matches("[A-Za-z0-9._-]{3,50}")) {
            Estilo.advertencia(this, "El usuario debe tener de 3 a 50 caracteres (letras, números, punto, guion).");
            return;
        }
        if (clave.length() < 6) {
            Estilo.advertencia(this, "La contraseña debe tener al menos 6 caracteres.");
            return;
        }
        Rol rol = (Rol) cmbRol.getSelectedItem();
        if (rol == null) {
            Estilo.advertencia(this, "Seleccione el rol del nuevo usuario.");
            return;
        }
        if (Estilo.intentar(this, () -> dao.crear(nombre, clave, rol.id()))) {
            limpiar();
            refrescar();
        }
    }

    private void actualizar() {
        if (!Sesion.esAdministrador() || idSeleccionado == 0) return;
        Rol rol = (Rol) cmbRol.getSelectedItem();
        String estado = (String) cmbEstado.getSelectedItem();
        if (idSeleccionado == Sesion.getUsuario().id() && "Inactivo".equals(estado)) {
            Estilo.advertencia(this, "No puede desactivar su propia cuenta.");
            return;
        }
        if (idSeleccionado == Sesion.getUsuario().id() && rol != null && !"Administrador".equals(rol.nombre())) {
            Estilo.advertencia(this, "No puede quitarse a sí mismo el rol de Administrador.");
            return;
        }
        if (rol != null && Estilo.intentar(this, () -> dao.actualizarRolEstado(idSeleccionado, rol.id(), estado))) {
            limpiar();
            refrescar();
        }
    }

    private void cambiarClave() {
        if (!Sesion.esAdministrador() || idSeleccionado == 0) return;
        String clave = new String(txtClave.getPassword());
        if (clave.length() < 6) {
            Estilo.advertencia(this, "Escriba la nueva contraseña (mínimo 6 caracteres) en el campo de contraseña.");
            return;
        }
        if (Estilo.intentar(this, () -> dao.cambiarClave(idSeleccionado, clave))) {
            Estilo.info(this, "Contraseña actualizada.");
            txtClave.setText("");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel acciones;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnClave;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JPanel cabecera;
    private javax.swing.JPanel campos;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<com.mensajeria.model.Rol> cmbRol;
    private javax.swing.JPanel contenido;
    private javax.swing.JPanel filaClave;
    private javax.swing.JPanel filaEstado;
    private javax.swing.JPanel filaRol;
    private javax.swing.JPanel filaUsuario;
    private javax.swing.JPanel formulario;
    private javax.swing.JLabel lblClave;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel listado;
    private javax.swing.JPanel raiz;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JTable tabla;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
