package com.mensajeria.ui;

import com.mensajeria.dao.EnvioDAO;
import com.mensajeria.dao.MensajeroDAO;
import com.mensajeria.model.Envio;
import com.mensajeria.model.Mensajero;
import com.mensajeria.util.Estilo;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/** Creacion, asignacion de mensajero, seguimiento de estados y consulta de envios. */
public class EnvioFrame extends javax.swing.JFrame implements VistaModulo {

    private static final DateTimeFormatter FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final EnvioDAO dao = new EnvioDAO();
    private final MensajeroDAO mensajeroDAO = new MensajeroDAO();
    private List<Envio> datos = new ArrayList<>();

    private final DefaultTableModel modelo = Estilo.modelo(
            "No.", "Fecha", "Cliente", "Tipo", "Descripción", "Destinatario", "Mensajero", "Estado");

    private NuevoEnvioFrame nuevoEnvio;

    public EnvioFrame() {
        initComponents();
        tabla.setModel(modelo);
        Estilo.configurarCabeceraTabla(tabla);
        tabla.getColumnModel().getColumn(0).setMaxWidth(70);
        Estilo.renderizarEstado(tabla, 7);
        cmbFiltro.addActionListener(e -> cargar());
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
            if (!e.getValueIsAdjusting()) {
                mostrarDetalle();
            }
        });
        int[] anchos = {62, 105, 170, 88, 220, 185, 155, 145};
        for (int i = 0; i < anchos.length; i++) tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        tabla.getColumnModel().getColumn(0).setMaxWidth(76);
        javax.swing.SwingUtilities.invokeLater(() -> divisionEnvios.setDividerLocation(210));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        raiz = new javax.swing.JPanel();
        cabecera = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        contenido = new javax.swing.JPanel();
        barra = new javax.swing.JPanel();
        filtros = new javax.swing.JPanel();
        busqueda = new javax.swing.JPanel();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        estado = new javax.swing.JPanel();
        lblFiltro = new javax.swing.JLabel();
        cmbFiltro = new javax.swing.JComboBox<>();
        acciones = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnAsignar = new javax.swing.JButton();
        btnEstado = new javax.swing.JButton();
        divisionEnvios = new javax.swing.JSplitPane();
        scrollTabla = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        panelDetalle = new javax.swing.JPanel();
        lblDetalle = new javax.swing.JLabel();
        scrollDetalle = new javax.swing.JScrollPane();
        detalle = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Envíos");
        setMinimumSize(new java.awt.Dimension(1000, 660));
        setPreferredSize(new java.awt.Dimension(1180, 740));

        raiz.setBackground(new java.awt.Color(243, 245, 247));
        raiz.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 28, 24, 28));
        raiz.setLayout(new java.awt.BorderLayout(16, 18));

        cabecera.setBackground(new java.awt.Color(243, 245, 247));
        cabecera.setLayout(new java.awt.BorderLayout(12, 12));

        lblTitulo.setForeground(new java.awt.Color(30, 36, 51));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitulo.setText("Envíos");
        cabecera.add(lblTitulo, java.awt.BorderLayout.CENTER);

        raiz.add(cabecera, java.awt.BorderLayout.NORTH);

        contenido.setBackground(new java.awt.Color(243, 245, 247));
        contenido.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        contenido.setLayout(new java.awt.BorderLayout(12, 12));

        barra.setBackground(new java.awt.Color(243, 245, 247));
        barra.setLayout(new java.awt.BorderLayout(12, 12));

        filtros.setBackground(new java.awt.Color(243, 245, 247));
        filtros.setLayout(new java.awt.BorderLayout(12, 12));

        busqueda.setBackground(new java.awt.Color(243, 245, 247));
        busqueda.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0));
        busqueda.setLayout(new java.awt.BorderLayout(0, 6));

        lblBuscar.setForeground(new java.awt.Color(30, 36, 51));
        lblBuscar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblBuscar.setText("Buscar envío");
        busqueda.add(lblBuscar, java.awt.BorderLayout.NORTH);

        txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtBuscar.setMinimumSize(new java.awt.Dimension(140, 34));
        txtBuscar.setPreferredSize(new java.awt.Dimension(180, 34));
        busqueda.add(txtBuscar, java.awt.BorderLayout.CENTER);

        filtros.add(busqueda, java.awt.BorderLayout.CENTER);

        estado.setBackground(new java.awt.Color(243, 245, 247));
        estado.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0));
        estado.setLayout(new java.awt.BorderLayout(0, 6));

        lblFiltro.setForeground(new java.awt.Color(30, 36, 51));
        lblFiltro.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblFiltro.setText("Estado");
        estado.add(lblFiltro, java.awt.BorderLayout.NORTH);

        cmbFiltro.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbFiltro.setPreferredSize(new java.awt.Dimension(180, 34));
        cmbFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos los estados", "Pendientes", "Registrado", "Asignado", "En tránsito", "Entregado", "Entrega fallida", "Cancelado" }));
        estado.add(cmbFiltro, java.awt.BorderLayout.CENTER);

        filtros.add(estado, java.awt.BorderLayout.EAST);

        barra.add(filtros, java.awt.BorderLayout.NORTH);

        acciones.setBackground(new java.awt.Color(243, 245, 247));
        acciones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 0));

        btnNuevo.setBackground(new java.awt.Color(245, 166, 35));
        btnNuevo.setForeground(new java.awt.Color(20, 33, 61));
        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnNuevo.setPreferredSize(new java.awt.Dimension(150, 38));
        btnNuevo.setText("Nuevo envío");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        acciones.add(btnNuevo);

        btnAsignar.setForeground(new java.awt.Color(20, 33, 61));
        btnAsignar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnAsignar.setPreferredSize(new java.awt.Dimension(150, 38));
        btnAsignar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 226, 236)), javax.swing.BorderFactory.createEmptyBorder(7, 12, 7, 12)));
        btnAsignar.setText("Asignar mensajero");
        btnAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarActionPerformed(evt);
            }
        });
        acciones.add(btnAsignar);

        btnEstado.setForeground(new java.awt.Color(20, 33, 61));
        btnEstado.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnEstado.setPreferredSize(new java.awt.Dimension(150, 38));
        btnEstado.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 226, 236)), javax.swing.BorderFactory.createEmptyBorder(7, 12, 7, 12)));
        btnEstado.setText("Cambiar estado");
        btnEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadoActionPerformed(evt);
            }
        });
        acciones.add(btnEstado);

        barra.add(acciones, java.awt.BorderLayout.SOUTH);

        contenido.add(barra, java.awt.BorderLayout.NORTH);

        divisionEnvios.setForeground(new java.awt.Color(0, 0, 0));
        divisionEnvios.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        divisionEnvios.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        divisionEnvios.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        divisionEnvios.setResizeWeight(0.6);
        divisionEnvios.setOneTouchExpandable(true);
        divisionEnvios.setDividerLocation(210);
        divisionEnvios.setDividerSize(8);
        divisionEnvios.setPreferredSize(new java.awt.Dimension(0, 350));

        scrollTabla.setPreferredSize(new java.awt.Dimension(0, 210));

        tabla.setForeground(new java.awt.Color(30, 36, 51));
        tabla.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Fecha", "Cliente", "Tipo", "Descripción", "Destinatario", "Mensajero", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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

        divisionEnvios.setLeftComponent(scrollTabla);

        panelDetalle.setBackground(new java.awt.Color(255, 255, 255));
        panelDetalle.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(221, 226, 236)), javax.swing.BorderFactory.createEmptyBorder(8, 4, 4, 4)));
        panelDetalle.setPreferredSize(new java.awt.Dimension(0, 126));
        panelDetalle.setLayout(new java.awt.BorderLayout(12, 12));

        lblDetalle.setForeground(new java.awt.Color(30, 36, 51));
        lblDetalle.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblDetalle.setText("Detalle del envío seleccionado");
        panelDetalle.add(lblDetalle, java.awt.BorderLayout.NORTH);

        scrollDetalle.setPreferredSize(new java.awt.Dimension(0, 96));

        detalle.setBackground(new java.awt.Color(242, 242, 242));
        detalle.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        detalle.setEditable(false);
        detalle.setColumns(20);
        detalle.setRows(4);
        detalle.setLineWrap(true);
        detalle.setWrapStyleWord(true);
        scrollDetalle.setViewportView(detalle);

        panelDetalle.add(scrollDetalle, java.awt.BorderLayout.CENTER);

        divisionEnvios.setRightComponent(panelDetalle);

        contenido.add(divisionEnvios, java.awt.BorderLayout.CENTER);

        raiz.add(contenido, java.awt.BorderLayout.CENTER);

        getContentPane().add(raiz, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        nuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarActionPerformed
        asignar();
    }//GEN-LAST:event_btnAsignarActionPerformed

    private void btnEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadoActionPerformed
        cambiarEstado();
    }//GEN-LAST:event_btnEstadoActionPerformed

    @Override
    public void refrescar() {
        cargar();
    }

    private String estadoFiltro() {
        int i = cmbFiltro.getSelectedIndex();
        if (i <= 0) {
            return null;
        }
        if (i == 1) {
            return EnvioDAO.PENDIENTES;
        }
        return (String) cmbFiltro.getSelectedItem();
    }

    private void cargar() {
        int idPrevio = envioSeleccionado() == null ? -1 : envioSeleccionado().id();
        Estilo.intentar(this, () -> {
            datos = dao.listar(txtBuscar.getText(), estadoFiltro());
            modelo.setRowCount(0);
            for (Envio e : datos) {
                modelo.addRow(new Object[]{e.id(), e.fechaEnvio().format(FECHA), e.clienteNombre(),
                    e.tipoPaquete(), e.descripcionPaquete(), e.nombreDestinatario(),
                    e.mensajeroNombre() == null ? "Sin asignar" : e.mensajeroNombre(), e.estado()});
            }
        });
        for (int i = 0; i < datos.size(); i++) {
            if (datos.get(i).id() == idPrevio) {
                tabla.setRowSelectionInterval(i, i);
                break;
            }
        }
        mostrarDetalle();
    }

    private Envio envioSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila < 0 || fila >= datos.size()) {
            return null;
        }
        return datos.get(tabla.convertRowIndexToModel(fila));
    }

    private void mostrarDetalle() {
        Envio e = envioSeleccionado();
        if (e == null) {
            detalle.setText("Seleccione un envío para ver su detalle.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Envío #").append(e.id()).append("  |  ").append(e.estado()).append("\n");
        sb.append("Cliente: ").append(e.clienteNombre()).append("   |   Fecha: ").append(e.fechaEnvio().format(FECHA)).append("\n");
        sb.append(e.tipoPaquete()).append(": ").append(e.descripcionPaquete()).append("\n");
        sb.append("Entregar a: ").append(e.nombreDestinatario());
        if (e.telefonoDestinatario() != null) {
            sb.append(" (").append(e.telefonoDestinatario()).append(")");
        }
        sb.append("  en  ").append(e.direccionDestino()).append("\n");
        sb.append("Mensajero: ").append(e.mensajeroNombre() == null ? "Sin asignar" : e.mensajeroNombre());
        if (e.fechaEntrega() != null) {
            sb.append("   |   Entregado el ").append(e.fechaEntrega().format(FECHA));
            if (e.recibidoPor() != null) {
                sb.append(", recibió: ").append(e.recibidoPor());
            }
        }
        if (e.observaciones() != null) {
            sb.append("\nObservaciones: ").append(e.observaciones());
        }
        detalle.setText(sb.toString());
        detalle.setCaretPosition(0);
    }

    private void nuevo() {
        if (nuevoEnvio != null && nuevoEnvio.isDisplayable()) {
            nuevoEnvio.setVisible(true);
            nuevoEnvio.toFront();
            return;
        }
        nuevoEnvio = new NuevoEnvioFrame(this);
        nuevoEnvio.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowClosed(java.awt.event.WindowEvent e) {
                NuevoEnvioFrame cerrada = (NuevoEnvioFrame) e.getWindow();
                if (nuevoEnvio == cerrada) nuevoEnvio = null;
                if (cerrada.fueCreado() && isDisplayable()) cargar();
            }
        });
        nuevoEnvio.setVisible(true);
    }

    @Override public void dispose() {
        if (nuevoEnvio != null) {
            NuevoEnvioFrame abierta = nuevoEnvio;
            nuevoEnvio = null;
            abierta.dispose();
        }
        super.dispose();
    }

    private void asignar() {
        Envio e = envioSeleccionado();
        if (e == null) {
            Estilo.advertencia(this, "Seleccione primero un envío de la tabla.");
            return;
        }
        List<Mensajero> activos;
        try {
            activos = mensajeroDAO.listarActivos();
        } catch (SQLException ex) {
            Estilo.error(this, "No se pudieron cargar los mensajeros:\n" + ex.getMessage());
            return;
        }
        if (activos.isEmpty()) {
            Estilo.advertencia(this, "No hay mensajeros activos. Registre uno en la sección Mensajeros.");
            return;
        }
        JComboBox<Mensajero> combo = new JComboBox<>(activos.toArray(new Mensajero[0]));
        if (e.idMensajero() != null) {
            for (Mensajero m : activos) {
                if (m.id() == e.idMensajero()) {
                    combo.setSelectedItem(m);
                }
            }
        }
        JPanel p = new JPanel(new GridLayout(0, 1, 0, 6));
        p.add(new JLabel("Envío #" + e.id() + " — " + e.descripcionPaquete()));
        p.add(Estilo.etiqueta("Mensajero responsable"));
        p.add(combo);
        int r = javax.swing.JOptionPane.showConfirmDialog(this, p, "Asignar mensajero",
                javax.swing.JOptionPane.OK_CANCEL_OPTION, javax.swing.JOptionPane.PLAIN_MESSAGE);
        if (r == javax.swing.JOptionPane.OK_OPTION) {
            Mensajero m = (Mensajero) combo.getSelectedItem();
            if (Estilo.intentar(this, () -> dao.asignarMensajero(e.id(), m.id()))) {
                cargar();
            }
        }
    }

    private void cambiarEstado() {
        Envio e = envioSeleccionado();
        if (e == null) {
            Estilo.advertencia(this, "Seleccione primero un envío de la tabla.");
            return;
        }
        List<String> opciones = EnvioDAO.siguientesEstados(e.estado());
        if (opciones.isEmpty()) {
            Estilo.info(this, "El envío #" + e.id() + " está '" + e.estado() + "' y ya no admite cambios de estado.");
            return;
        }
        if (Envio.REGISTRADO.equals(e.estado()) || Envio.FALLIDA.equals(e.estado())) {
            Estilo.info(this, "Este envío está '" + e.estado() + "'. Para continuar, asígnele un mensajero "
                    + "(botón 'Asignar mensajero'); también puede cancelarlo.");
        }
        JComboBox<String> combo = new JComboBox<>(opciones.toArray(new String[0]));
        JTextField txtRecibio = new JTextField();
        JTextArea txtMotivo = new JTextArea(3, 25);
        txtMotivo.setLineWrap(true);
        txtMotivo.setWrapStyleWord(true);
        JLabel lblRecibio = Estilo.etiqueta("Nombre de quien recibió *");
        JLabel lblMotivo = Estilo.etiqueta("Observaciones");

        Runnable ajustar = () -> {
            String s = (String) combo.getSelectedItem();
            boolean entregado = Envio.ENTREGADO.equals(s);
            lblRecibio.setVisible(entregado);
            txtRecibio.setVisible(entregado);
            lblMotivo.setText(Envio.FALLIDA.equals(s) || Envio.CANCELADO.equals(s)
                    ? "Motivo *" : "Observaciones");
        };
        combo.addActionListener(x -> ajustar.run());
        ajustar.run();

        JPanel p = new JPanel(new GridLayout(0, 1, 0, 4));
        p.add(new JLabel("Envío #" + e.id() + " — actualmente: " + e.estado()));
        p.add(Estilo.etiqueta("Nuevo estado"));
        p.add(combo);
        p.add(lblRecibio);
        p.add(txtRecibio);
        p.add(lblMotivo);
        p.add(new JScrollPane(txtMotivo));
        p.setPreferredSize(new Dimension(380, 260));

        int r = javax.swing.JOptionPane.showConfirmDialog(this, p, "Cambiar estado",
                javax.swing.JOptionPane.OK_CANCEL_OPTION, javax.swing.JOptionPane.PLAIN_MESSAGE);
        if (r != javax.swing.JOptionPane.OK_OPTION) {
            return;
        }
        String nuevo = (String) combo.getSelectedItem();
        String motivo = txtMotivo.getText().trim();
        if (Envio.ENTREGADO.equals(nuevo) && txtRecibio.getText().trim().isEmpty()) {
            Estilo.advertencia(this, "Indique el nombre de la persona que recibió el envío.");
            return;
        }
        if ((Envio.FALLIDA.equals(nuevo) || Envio.CANCELADO.equals(nuevo)) && motivo.isEmpty()) {
            Estilo.advertencia(this, "Debe registrar el motivo.");
            return;
        }
        if (Envio.CANCELADO.equals(nuevo) && !Estilo.confirmar(this, "¿Cancelar el envío #" + e.id() + "?")) {
            return;
        }
        // Para fallida/cancelado se antepone el tipo de motivo para facilitar consultas posteriores.
        String obs = motivo.isEmpty() ? null
                : (Envio.FALLIDA.equals(nuevo) ? "Entrega fallida: " + motivo
                : Envio.CANCELADO.equals(nuevo) ? "Cancelado: " + motivo : motivo);
        if (Estilo.intentar(this, () -> dao.cambiarEstado(e.id(), nuevo, obs, txtRecibio.getText()))) {
            cargar();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel acciones;
    private javax.swing.JPanel barra;
    private javax.swing.JButton btnAsignar;
    private javax.swing.JButton btnEstado;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JPanel busqueda;
    private javax.swing.JPanel cabecera;
    private javax.swing.JComboBox<String> cmbFiltro;
    private javax.swing.JPanel contenido;
    private javax.swing.JTextArea detalle;
    private javax.swing.JSplitPane divisionEnvios;
    private javax.swing.JPanel estado;
    private javax.swing.JPanel filtros;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblDetalle;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelDetalle;
    private javax.swing.JPanel raiz;
    private javax.swing.JScrollPane scrollDetalle;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
