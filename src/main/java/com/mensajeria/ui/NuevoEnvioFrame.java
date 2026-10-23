package com.mensajeria.ui;

import com.mensajeria.dao.ClienteDAO;
import com.mensajeria.dao.EnvioDAO;
import com.mensajeria.dao.MensajeroDAO;
import com.mensajeria.model.Cliente;
import com.mensajeria.model.Mensajero;
import com.mensajeria.model.Paquete;
import com.mensajeria.util.Estilo;
import com.mensajeria.util.Sesion;
import java.awt.Window;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/** Formulario para registrar un nuevo envio (cliente + paquete/documento + destino). */
public class NuevoEnvioFrame extends javax.swing.JFrame {

    private static final String SIN_ASIGNAR = "— Sin asignar (asignar después) —";

    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final MensajeroDAO mensajeroDAO = new MensajeroDAO();
    private final EnvioDAO envioDAO = new EnvioDAO();

    private List<Cliente> clientes = new ArrayList<>();
    private boolean creado = false;



    public NuevoEnvioFrame() {
        initComponents();
        txtFiltroCliente.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarClientes();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarClientes();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarClientes();
            }
        });

    }

    /** Agrupa campos por flujo y asigna espacio según la información que reciben. */
    public NuevoEnvioFrame(Window owner) {
        this();
        setLocationRelativeTo(owner);
        cargarDatos();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        raiz = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        scrollFormulario = new javax.swing.JScrollPane();
        formulario = new javax.swing.JPanel();
        campos = new javax.swing.JPanel();
        lblVista1 = new javax.swing.JLabel();
        filaFiltro = new javax.swing.JPanel();
        lblFiltroCliente = new javax.swing.JLabel();
        txtFiltroCliente = new javax.swing.JTextField();
        filaCliente = new javax.swing.JPanel();
        lblCliente = new javax.swing.JLabel();
        cmbCliente = new javax.swing.JComboBox<>();
        lblVista2 = new javax.swing.JLabel();
        filaTipo = new javax.swing.JPanel();
        lblTipo = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        filaPeso = new javax.swing.JPanel();
        lblPeso = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        filaDescripcion = new javax.swing.JPanel();
        lblDescripcion = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        filaDimensiones = new javax.swing.JPanel();
        lblDimensiones = new javax.swing.JLabel();
        txtDimensiones = new javax.swing.JTextField();
        lblVista3 = new javax.swing.JLabel();
        filaDireccion = new javax.swing.JPanel();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        filaDestinatario = new javax.swing.JPanel();
        lblDestinatario = new javax.swing.JLabel();
        txtDestinatario = new javax.swing.JTextField();
        filaTelefono = new javax.swing.JPanel();
        lblTelDest = new javax.swing.JLabel();
        txtTelDest = new javax.swing.JTextField();
        lblVista4 = new javax.swing.JLabel();
        filaMensajero = new javax.swing.JPanel();
        lblMensajero = new javax.swing.JLabel();
        cmbMensajero = new javax.swing.JComboBox<>();
        filaObs = new javax.swing.JPanel();
        lblollObs = new javax.swing.JLabel();
        scrollObs = new javax.swing.JScrollPane();
        txtObs = new javax.swing.JTextArea();
        acciones = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo envío");
        setMinimumSize(new java.awt.Dimension(800, 660));
        setPreferredSize(new java.awt.Dimension(900, 820));

        raiz.setBackground(new java.awt.Color(243, 245, 247));
        raiz.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 24, 16, 24));
        raiz.setLayout(new java.awt.BorderLayout(12, 16));

        lblTitulo.setForeground(new java.awt.Color(30, 36, 51));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitulo.setText("Registrar nuevo envío");
        raiz.add(lblTitulo, java.awt.BorderLayout.NORTH);

        formulario.setBackground(new java.awt.Color(243, 245, 247));
        formulario.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 2, 12, 2));
        formulario.setLayout(new java.awt.BorderLayout(12, 12));

        campos.setBackground(new java.awt.Color(243, 245, 247));
        campos.setLayout(new java.awt.GridBagLayout());

        lblVista1.setForeground(new java.awt.Color(36, 90, 129));
        lblVista1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblVista1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(221, 226, 236)));
        lblVista1.setText("Cliente solicitante");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 4, 4, 4);
        campos.add(lblVista1, gridBagConstraints);

        filaFiltro.setBackground(new java.awt.Color(243, 245, 247));
        filaFiltro.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 6, 5, 6));
        filaFiltro.setLayout(new java.awt.BorderLayout(0, 6));

        lblFiltroCliente.setForeground(new java.awt.Color(30, 36, 51));
        lblFiltroCliente.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblFiltroCliente.setText("Buscar cliente");
        filaFiltro.add(lblFiltroCliente, java.awt.BorderLayout.NORTH);

        txtFiltroCliente.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtFiltroCliente.setMinimumSize(new java.awt.Dimension(140, 34));
        txtFiltroCliente.setPreferredSize(new java.awt.Dimension(220, 34));
        filaFiltro.add(txtFiltroCliente, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        campos.add(filaFiltro, gridBagConstraints);

        filaCliente.setBackground(new java.awt.Color(243, 245, 247));
        filaCliente.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 6, 5, 6));
        filaCliente.setLayout(new java.awt.BorderLayout(0, 6));

        lblCliente.setForeground(new java.awt.Color(30, 36, 51));
        lblCliente.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblCliente.setText("Cliente *");
        filaCliente.add(lblCliente, java.awt.BorderLayout.NORTH);

        cmbCliente.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbCliente.setPreferredSize(new java.awt.Dimension(220, 34));
        filaCliente.add(cmbCliente, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        campos.add(filaCliente, gridBagConstraints);

        lblVista2.setForeground(new java.awt.Color(36, 90, 129));
        lblVista2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblVista2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(221, 226, 236)));
        lblVista2.setText("Contenido del envío");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 4, 4, 4);
        campos.add(lblVista2, gridBagConstraints);

        filaTipo.setBackground(new java.awt.Color(243, 245, 247));
        filaTipo.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 6, 5, 6));
        filaTipo.setLayout(new java.awt.BorderLayout(0, 6));

        lblTipo.setForeground(new java.awt.Color(30, 36, 51));
        lblTipo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblTipo.setText("Tipo *");
        filaTipo.add(lblTipo, java.awt.BorderLayout.NORTH);

        cmbTipo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbTipo.setPreferredSize(new java.awt.Dimension(220, 34));
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Paquete", "Documento" }));
        filaTipo.add(cmbTipo, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        campos.add(filaTipo, gridBagConstraints);

        filaPeso.setBackground(new java.awt.Color(243, 245, 247));
        filaPeso.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 6, 5, 6));
        filaPeso.setLayout(new java.awt.BorderLayout(0, 6));

        lblPeso.setForeground(new java.awt.Color(30, 36, 51));
        lblPeso.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPeso.setText("Peso (kg)");
        filaPeso.add(lblPeso, java.awt.BorderLayout.NORTH);

        txtPeso.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtPeso.setMinimumSize(new java.awt.Dimension(140, 34));
        txtPeso.setPreferredSize(new java.awt.Dimension(220, 34));
        filaPeso.add(txtPeso, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        campos.add(filaPeso, gridBagConstraints);

        filaDescripcion.setBackground(new java.awt.Color(243, 245, 247));
        filaDescripcion.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 6, 5, 6));
        filaDescripcion.setLayout(new java.awt.BorderLayout(0, 6));

        lblDescripcion.setForeground(new java.awt.Color(30, 36, 51));
        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblDescripcion.setText("Descripción *");
        filaDescripcion.add(lblDescripcion, java.awt.BorderLayout.NORTH);

        txtDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDescripcion.setMinimumSize(new java.awt.Dimension(140, 34));
        txtDescripcion.setPreferredSize(new java.awt.Dimension(220, 34));
        filaDescripcion.add(txtDescripcion, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        campos.add(filaDescripcion, gridBagConstraints);

        filaDimensiones.setBackground(new java.awt.Color(243, 245, 247));
        filaDimensiones.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 6, 5, 6));
        filaDimensiones.setLayout(new java.awt.BorderLayout(0, 6));

        lblDimensiones.setForeground(new java.awt.Color(30, 36, 51));
        lblDimensiones.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblDimensiones.setText("Dimensiones");
        filaDimensiones.add(lblDimensiones, java.awt.BorderLayout.NORTH);

        txtDimensiones.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDimensiones.setMinimumSize(new java.awt.Dimension(140, 34));
        txtDimensiones.setPreferredSize(new java.awt.Dimension(220, 34));
        filaDimensiones.add(txtDimensiones, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        campos.add(filaDimensiones, gridBagConstraints);

        lblVista3.setForeground(new java.awt.Color(36, 90, 129));
        lblVista3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblVista3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(221, 226, 236)));
        lblVista3.setText("Entrega");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 4, 4, 4);
        campos.add(lblVista3, gridBagConstraints);

        filaDireccion.setBackground(new java.awt.Color(243, 245, 247));
        filaDireccion.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 6, 5, 6));
        filaDireccion.setLayout(new java.awt.BorderLayout(0, 6));

        lblDireccion.setForeground(new java.awt.Color(30, 36, 51));
        lblDireccion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblDireccion.setText("Dirección de destino *");
        filaDireccion.add(lblDireccion, java.awt.BorderLayout.NORTH);

        txtDireccion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDireccion.setMinimumSize(new java.awt.Dimension(140, 34));
        txtDireccion.setPreferredSize(new java.awt.Dimension(220, 34));
        filaDireccion.add(txtDireccion, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        campos.add(filaDireccion, gridBagConstraints);

        filaDestinatario.setBackground(new java.awt.Color(243, 245, 247));
        filaDestinatario.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 6, 5, 6));
        filaDestinatario.setLayout(new java.awt.BorderLayout(0, 6));

        lblDestinatario.setForeground(new java.awt.Color(30, 36, 51));
        lblDestinatario.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblDestinatario.setText("Nombre del destinatario *");
        filaDestinatario.add(lblDestinatario, java.awt.BorderLayout.NORTH);

        txtDestinatario.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDestinatario.setMinimumSize(new java.awt.Dimension(140, 34));
        txtDestinatario.setPreferredSize(new java.awt.Dimension(220, 34));
        filaDestinatario.add(txtDestinatario, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        campos.add(filaDestinatario, gridBagConstraints);

        filaTelefono.setBackground(new java.awt.Color(243, 245, 247));
        filaTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 6, 5, 6));
        filaTelefono.setLayout(new java.awt.BorderLayout(0, 6));

        lblTelDest.setForeground(new java.awt.Color(30, 36, 51));
        lblTelDest.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblTelDest.setText("Teléfono del destinatario");
        filaTelefono.add(lblTelDest, java.awt.BorderLayout.NORTH);

        txtTelDest.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTelDest.setMinimumSize(new java.awt.Dimension(140, 34));
        txtTelDest.setPreferredSize(new java.awt.Dimension(220, 34));
        filaTelefono.add(txtTelDest, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        campos.add(filaTelefono, gridBagConstraints);

        lblVista4.setForeground(new java.awt.Color(36, 90, 129));
        lblVista4.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblVista4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(221, 226, 236)));
        lblVista4.setText("Asignación y observaciones");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 4, 4, 4);
        campos.add(lblVista4, gridBagConstraints);

        filaMensajero.setBackground(new java.awt.Color(243, 245, 247));
        filaMensajero.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 6, 5, 6));
        filaMensajero.setLayout(new java.awt.BorderLayout(0, 6));

        lblMensajero.setForeground(new java.awt.Color(30, 36, 51));
        lblMensajero.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblMensajero.setText("Mensajero (opcional)");
        filaMensajero.add(lblMensajero, java.awt.BorderLayout.NORTH);

        cmbMensajero.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbMensajero.setPreferredSize(new java.awt.Dimension(220, 34));
        filaMensajero.add(cmbMensajero, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        campos.add(filaMensajero, gridBagConstraints);

        formulario.add(campos, java.awt.BorderLayout.NORTH);

        filaObs.setBackground(new java.awt.Color(243, 245, 247));
        filaObs.setPreferredSize(new java.awt.Dimension(700, 138));
        filaObs.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 6, 6, 6));
        filaObs.setLayout(new java.awt.BorderLayout(0, 6));

        lblollObs.setForeground(new java.awt.Color(30, 36, 51));
        lblollObs.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblollObs.setText("Observaciones");
        filaObs.add(lblollObs, java.awt.BorderLayout.NORTH);

        txtObs.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtObs.setColumns(20);
        txtObs.setRows(4);
        txtObs.setLineWrap(true);
        txtObs.setWrapStyleWord(true);
        scrollObs.setViewportView(txtObs);

        filaObs.add(scrollObs, java.awt.BorderLayout.CENTER);

        formulario.add(filaObs, java.awt.BorderLayout.CENTER);

        scrollFormulario.setViewportView(formulario);

        raiz.add(scrollFormulario, java.awt.BorderLayout.CENTER);

        acciones.setBackground(new java.awt.Color(243, 245, 247));
        acciones.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        acciones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 0));

        btnCancelar.setForeground(new java.awt.Color(20, 33, 61));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnCancelar.setPreferredSize(new java.awt.Dimension(118, 36));
        btnCancelar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 226, 236)), javax.swing.BorderFactory.createEmptyBorder(7, 12, 7, 12)));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        acciones.add(btnCancelar);

        btnGuardar.setBackground(new java.awt.Color(245, 166, 35));
        btnGuardar.setForeground(new java.awt.Color(20, 33, 61));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnGuardar.setPreferredSize(new java.awt.Dimension(150, 36));
        btnGuardar.setText("Guardar envío");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        acciones.add(btnGuardar);

        raiz.add(acciones, java.awt.BorderLayout.SOUTH);

        getContentPane().add(raiz, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cargarDatos() {
        try {
            clientes = clienteDAO.listar("");
            filtrarClientes();
            DefaultComboBoxModel<Object> m = new DefaultComboBoxModel<>();
            m.addElement(SIN_ASIGNAR);
            for (Mensajero x : mensajeroDAO.listarActivos()) {
                m.addElement(x);
            }
            cmbMensajero.setModel(m);
        } catch (SQLException e) {
            Estilo.error(this, "No se pudieron cargar los datos:\n" + e.getMessage());
        }
    }

    private void filtrarClientes() {
        String f = txtFiltroCliente.getText().trim().toLowerCase();
        DefaultComboBoxModel<Cliente> m = new DefaultComboBoxModel<>();
        for (Cliente x : clientes) {
            if (f.isEmpty() || x.nombre().toLowerCase().contains(f)) {
                m.addElement(x);
            }
        }
        cmbCliente.setModel(m);
    }

    public boolean fueCreado() {
        return creado;
    }

    private void guardar() {
        if (Sesion.getUsuario() == null) {
            dispose();
            return;
        }
        Cliente cliente = (Cliente) cmbCliente.getSelectedItem();
        String desc = txtDescripcion.getText().trim();
        String dir = txtDireccion.getText().trim();
        String dest = txtDestinatario.getText().trim();
        if (cliente == null) {
            Estilo.advertencia(this, "Seleccione el cliente que solicita el envío. "
                    + "Si no existe, regístrelo primero en la sección Clientes.");
            return;
        }
        if (desc.isEmpty() || dir.isEmpty() || dest.isEmpty()) {
            Estilo.advertencia(this, "Complete la descripción, la dirección de destino y el nombre del destinatario.");
            return;
        }
        Double peso = null;
        String pesoTxt = txtPeso.getText().trim().replace(',', '.');
        if (!pesoTxt.isEmpty()) {
            try {
                peso = Double.valueOf(pesoTxt);
                if (peso < 0 || peso > 999999) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                Estilo.advertencia(this, "El peso debe ser un número válido (ej. 2.5).");
                return;
            }
        }
        Object sel = cmbMensajero.getSelectedItem();
        Integer idMensajero = (sel instanceof Mensajero m) ? m.id() : null;
        Paquete paquete = new Paquete(0, (String) cmbTipo.getSelectedItem(), desc, peso, txtDimensiones.getText());
        try {
            int id = envioDAO.crear(cliente.id(), paquete, idMensajero, dir, dest,
                    txtTelDest.getText().trim(), txtObs.getText().trim());
            creado = true;
            Estilo.info(this, "Envío #" + id + " registrado correctamente.");
            dispose();
        } catch (SQLException e) {
            Estilo.error(this, "No se pudo registrar el envío:\n" + e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel acciones;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JPanel campos;
    private javax.swing.JComboBox<com.mensajeria.model.Cliente> cmbCliente;
    private javax.swing.JComboBox<Object> cmbMensajero;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JPanel filaCliente;
    private javax.swing.JPanel filaDescripcion;
    private javax.swing.JPanel filaDestinatario;
    private javax.swing.JPanel filaDimensiones;
    private javax.swing.JPanel filaDireccion;
    private javax.swing.JPanel filaFiltro;
    private javax.swing.JPanel filaMensajero;
    private javax.swing.JPanel filaObs;
    private javax.swing.JPanel filaPeso;
    private javax.swing.JPanel filaTelefono;
    private javax.swing.JPanel filaTipo;
    private javax.swing.JPanel formulario;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDestinatario;
    private javax.swing.JLabel lblDimensiones;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblFiltroCliente;
    private javax.swing.JLabel lblMensajero;
    private javax.swing.JLabel lblPeso;
    private javax.swing.JLabel lblTelDest;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblVista1;
    private javax.swing.JLabel lblVista2;
    private javax.swing.JLabel lblVista3;
    private javax.swing.JLabel lblVista4;
    private javax.swing.JLabel lblollObs;
    private javax.swing.JPanel raiz;
    private javax.swing.JScrollPane scrollFormulario;
    private javax.swing.JScrollPane scrollObs;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDestinatario;
    private javax.swing.JTextField txtDimensiones;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFiltroCliente;
    private javax.swing.JTextArea txtObs;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextField txtTelDest;
    // End of variables declaration//GEN-END:variables
}
