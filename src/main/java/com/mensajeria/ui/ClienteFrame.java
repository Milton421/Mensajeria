package com.mensajeria.ui;

import com.mensajeria.dao.ClienteDAO;
import com.mensajeria.model.Cliente;
import com.mensajeria.util.Estilo;
import com.mensajeria.util.Sesion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/** Registro, consulta y actualizacion de clientes. */
public class ClienteFrame extends javax.swing.JFrame implements VistaModulo {

    private final ClienteDAO dao = new ClienteDAO();
    private List<Cliente> datos = new ArrayList<>();
    private int idSeleccionado = 0;

    private final DefaultTableModel modelo = Estilo.modelo("ID", "Nombre", "Teléfono", "Dirección", "Correo");


    public ClienteFrame() {
        initComponents();
        tabla.setModel(modelo);
        Estilo.configurarCabeceraTabla(tabla);
        tabla.getColumnModel().getColumn(0).setMaxWidth(70);
        txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                cargar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                cargar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                cargar();
            }
        });
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabla.getSelectedRow() >= 0) {
                Cliente x = datos.get(tabla.convertRowIndexToModel(tabla.getSelectedRow()));
                idSeleccionado = x.id();
                txtNombre.setText(x.nombre());
                txtTelefono.setText(x.telefono() == null ? "" : x.telefono());
                txtDireccion.setText(x.direccion() == null ? "" : x.direccion());
                txtCorreo.setText(x.correo() == null ? "" : x.correo());
                btnEliminar.setEnabled(Sesion.esAdministrador());
            }
        });
        btnEliminar.setEnabled(false);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(58);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(210);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(125);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(250);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(205);
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
        filaNombre = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        filaTelefono = new javax.swing.JPanel();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        filaDireccion = new javax.swing.JPanel();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        filaCorreo = new javax.swing.JPanel();
        lblCorreo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        acciones = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        listado = new javax.swing.JPanel();
        busqueda = new javax.swing.JPanel();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        scrollTabla = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Clientes");
        setMinimumSize(new java.awt.Dimension(1000, 660));
        setPreferredSize(new java.awt.Dimension(1180, 740));

        raiz.setBackground(new java.awt.Color(243, 245, 247));
        raiz.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 28, 24, 28));
        raiz.setLayout(new java.awt.BorderLayout(16, 18));

        cabecera.setBackground(new java.awt.Color(243, 245, 247));
        cabecera.setLayout(new java.awt.BorderLayout(12, 12));

        lblTitulo.setForeground(new java.awt.Color(30, 36, 51));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitulo.setText("Clientes");
        cabecera.add(lblTitulo, java.awt.BorderLayout.CENTER);

        raiz.add(cabecera, java.awt.BorderLayout.NORTH);

        contenido.setBackground(new java.awt.Color(243, 245, 247));
        contenido.setLayout(new java.awt.BorderLayout(16, 0));

        formulario.setBackground(new java.awt.Color(243, 245, 247));
        formulario.setPreferredSize(new java.awt.Dimension(300, 0));
        formulario.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(212, 220, 227)), javax.swing.BorderFactory.createEmptyBorder(14, 12, 12, 12)));
        formulario.setLayout(new java.awt.BorderLayout(12, 12));

        campos.setBackground(new java.awt.Color(243, 245, 247));
        campos.setLayout(new javax.swing.BoxLayout(campos, javax.swing.BoxLayout.Y_AXIS));

        filaNombre.setBackground(new java.awt.Color(243, 245, 247));
        filaNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0));
        filaNombre.setLayout(new java.awt.BorderLayout(0, 6));

        lblNombre.setForeground(new java.awt.Color(30, 36, 51));
        lblNombre.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblNombre.setText("Nombre completo *");
        filaNombre.add(lblNombre, java.awt.BorderLayout.NORTH);

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNombre.setMinimumSize(new java.awt.Dimension(140, 34));
        txtNombre.setPreferredSize(new java.awt.Dimension(180, 34));
        filaNombre.add(txtNombre, java.awt.BorderLayout.CENTER);

        campos.add(filaNombre);

        filaTelefono.setBackground(new java.awt.Color(243, 245, 247));
        filaTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0));
        filaTelefono.setLayout(new java.awt.BorderLayout(0, 6));

        lblTelefono.setForeground(new java.awt.Color(30, 36, 51));
        lblTelefono.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblTelefono.setText("Teléfono");
        filaTelefono.add(lblTelefono, java.awt.BorderLayout.NORTH);

        txtTelefono.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTelefono.setMinimumSize(new java.awt.Dimension(140, 34));
        txtTelefono.setPreferredSize(new java.awt.Dimension(180, 34));
        filaTelefono.add(txtTelefono, java.awt.BorderLayout.CENTER);

        campos.add(filaTelefono);

        filaDireccion.setBackground(new java.awt.Color(243, 245, 247));
        filaDireccion.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0));
        filaDireccion.setLayout(new java.awt.BorderLayout(0, 6));

        lblDireccion.setForeground(new java.awt.Color(30, 36, 51));
        lblDireccion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblDireccion.setText("Dirección");
        filaDireccion.add(lblDireccion, java.awt.BorderLayout.NORTH);

        txtDireccion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDireccion.setMinimumSize(new java.awt.Dimension(140, 34));
        txtDireccion.setPreferredSize(new java.awt.Dimension(260, 34));
        filaDireccion.add(txtDireccion, java.awt.BorderLayout.CENTER);

        campos.add(filaDireccion);

        filaCorreo.setBackground(new java.awt.Color(243, 245, 247));
        filaCorreo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0));
        filaCorreo.setLayout(new java.awt.BorderLayout(0, 6));

        lblCorreo.setForeground(new java.awt.Color(30, 36, 51));
        lblCorreo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblCorreo.setText("Correo electrónico");
        filaCorreo.add(lblCorreo, java.awt.BorderLayout.NORTH);

        txtCorreo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtCorreo.setMinimumSize(new java.awt.Dimension(140, 34));
        txtCorreo.setPreferredSize(new java.awt.Dimension(180, 34));
        filaCorreo.add(txtCorreo, java.awt.BorderLayout.CENTER);

        campos.add(filaCorreo);

        acciones.setBackground(new java.awt.Color(243, 245, 247));
        acciones.setLayout(new java.awt.GridLayout(0, 2, 6, 6));

        btnGuardar.setBackground(new java.awt.Color(245, 166, 35));
        btnGuardar.setForeground(new java.awt.Color(20, 33, 61));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnGuardar.setPreferredSize(new java.awt.Dimension(150, 38));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        acciones.add(btnGuardar);

        btnNuevo.setForeground(new java.awt.Color(20, 33, 61));
        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnNuevo.setPreferredSize(new java.awt.Dimension(150, 38));
        btnNuevo.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 226, 236)), javax.swing.BorderFactory.createEmptyBorder(7, 12, 7, 12)));
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        acciones.add(btnNuevo);

        btnEliminar.setForeground(new java.awt.Color(20, 33, 61));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnEliminar.setPreferredSize(new java.awt.Dimension(150, 38));
        btnEliminar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 226, 236)), javax.swing.BorderFactory.createEmptyBorder(7, 12, 7, 12)));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        acciones.add(btnEliminar);

        campos.add(acciones);

        formulario.add(campos, java.awt.BorderLayout.NORTH);

        contenido.add(formulario, java.awt.BorderLayout.WEST);

        listado.setBackground(new java.awt.Color(255, 255, 255));
        listado.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        listado.setLayout(new java.awt.BorderLayout(12, 12));

        busqueda.setBackground(new java.awt.Color(255, 255, 255));
        busqueda.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0));
        busqueda.setLayout(new java.awt.BorderLayout(0, 6));

        lblBuscar.setForeground(new java.awt.Color(30, 36, 51));
        lblBuscar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblBuscar.setText("Buscar");
        busqueda.add(lblBuscar, java.awt.BorderLayout.NORTH);

        txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtBuscar.setMinimumSize(new java.awt.Dimension(140, 34));
        txtBuscar.setPreferredSize(new java.awt.Dimension(180, 34));
        busqueda.add(txtBuscar, java.awt.BorderLayout.CENTER);

        listado.add(busqueda, java.awt.BorderLayout.NORTH);

        tabla.setForeground(new java.awt.Color(30, 36, 51));
        tabla.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Teléfono", "Dirección", "Correo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiar();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    @Override
    public void refrescar() {
        cargar();
    }

    private void cargar() {
        Estilo.intentar(this, () -> {
            datos = dao.listar(txtBuscar.getText());
            modelo.setRowCount(0);
            for (Cliente x : datos) {
                modelo.addRow(new Object[]{x.id(), x.nombre(), x.telefono(), x.direccion(), x.correo()});
            }
        });
    }

    private void limpiar() {
        idSeleccionado = 0;
        txtNombre.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");
        tabla.clearSelection();
        btnEliminar.setEnabled(false);
        txtNombre.requestFocus();
    }

    private void guardar() {
        String nombre = txtNombre.getText().trim();
        String tel = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();
        if (nombre.isEmpty()) {
            Estilo.advertencia(this, "El nombre del cliente es obligatorio.");
            return;
        }
        if (!tel.isEmpty() && !tel.matches("[0-9+\\-() ]{6,20}")) {
            Estilo.advertencia(this, "El teléfono solo puede contener números, espacios, + - ( ) (6 a 20 caracteres).");
            return;
        }
        if (!correo.isEmpty() && !correo.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            Estilo.advertencia(this, "El correo electrónico no tiene un formato válido.");
            return;
        }
        Cliente x = new Cliente(idSeleccionado, nombre, tel, txtDireccion.getText().trim(), correo);
        boolean ok = Estilo.intentar(this, () -> {
            if (idSeleccionado == 0) {
                dao.insertar(x);
            } else {
                dao.actualizar(x);
            }
        });
        if (ok) {
            limpiar();
            cargar();
        }
    }

    private void eliminar() {
        if (idSeleccionado == 0) {
            return;
        }
        if (Estilo.confirmar(this, "¿Eliminar al cliente seleccionado?")) {
            if (Estilo.intentar(this, () -> dao.eliminar(idSeleccionado))) {
                limpiar();
                cargar();
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel acciones;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JPanel busqueda;
    private javax.swing.JPanel cabecera;
    private javax.swing.JPanel campos;
    private javax.swing.JPanel contenido;
    private javax.swing.JPanel filaCorreo;
    private javax.swing.JPanel filaDireccion;
    private javax.swing.JPanel filaNombre;
    private javax.swing.JPanel filaTelefono;
    private javax.swing.JPanel formulario;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel listado;
    private javax.swing.JPanel raiz;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
