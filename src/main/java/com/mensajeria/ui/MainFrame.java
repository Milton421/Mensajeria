package com.mensajeria.ui;

import com.mensajeria.dao.EnvioDAO;
import com.mensajeria.model.Envio;
import com.mensajeria.util.Estilo;
import com.mensajeria.util.Sesion;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/** Menu de acceso a los formularios JFrame del sistema. */
public class MainFrame extends JFrame {
    private final Map<String, Supplier<JFrame>> fabricas = new LinkedHashMap<>();
    private final Map<String, JFrame> ventanas = new LinkedHashMap<>();
    private final Map<String, java.awt.Container> vistasModulos = new LinkedHashMap<>();
    private java.awt.Component contenidoActivo;
    private NuevoEnvioFrame nuevoEnvio;
    private final EnvioDAO envioDAO = new EnvioDAO();
    private final DefaultTableModel modeloEnvios = Estilo.modelo("No.", "Cliente", "Destinatario", "Mensajero", "Estado");

    public MainFrame() {
        initComponents();
        contenidoActivo = pie;
        fabricas.put("Inicio", () -> new InicioFrame(this));
        fabricas.put("Envíos", EnvioFrame::new);
        fabricas.put("Clientes", ClienteFrame::new);
        fabricas.put("Mensajeros", MensajeroFrame::new);
        if (Sesion.esAdministrador()) fabricas.put("Usuarios", UsuarioFrame::new);
        fabricas.put("Reportes", ReporteFrame::new);
        btnUsuarios.setVisible(Sesion.esAdministrador());
        if (Sesion.getUsuario() != null) {
            lblUsuario.setText(Sesion.getUsuario().nombreUsuario() + " · " + Sesion.getUsuario().rolNombre());
        }
        tablaEnvios.setModel(modeloEnvios);
        configurarTablaPrincipal();
        tablaEnvios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowOpened(java.awt.event.WindowEvent e) { refrescarInicio(); }
        });
    }

    /** Renderizadores y anchos para los datos de la tabla principal. */
    private void configurarTablaPrincipal() {
                javax.swing.table.JTableHeader cabeceraTabla = tablaEnvios.getTableHeader();
        cabeceraTabla.setFont(Estilo.F_NEGRITA);
        cabeceraTabla.setBackground(new java.awt.Color(0xE9EEF3));
        cabeceraTabla.setForeground(Estilo.MARINO);
        cabeceraTabla.setPreferredSize(new java.awt.Dimension(0, 34));
        cabeceraTabla.setReorderingAllowed(false);
        // Mantiene el renderer del Look and Feel y sus affordances de redimensionado.
        javax.swing.table.TableCellRenderer original = cabeceraTabla.getDefaultRenderer();
        cabeceraTabla.setDefaultRenderer((tabla, valor, seleccionado, foco, fila, columna) -> {
            java.awt.Component c = original.getTableCellRendererComponent(
                    tabla, valor, seleccionado, foco, fila, columna);
            if (c instanceof JLabel etiqueta) {
                etiqueta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                etiqueta.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                        javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(0xD4DCE3)),
                        javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
            }
            return c;
        });
        tablaEnvios.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override public java.awt.Component getTableCellRendererComponent(javax.swing.JTable tabla,
                    Object valor, boolean seleccionado, boolean foco, int fila, int columna) {
                super.getTableCellRendererComponent(tabla, valor, seleccionado, foco, fila, columna);
                // Conserva el borde de foco de Swing para navegación con teclado.
                setBorder(javax.swing.BorderFactory.createCompoundBorder(getBorder(),
                        javax.swing.BorderFactory.createEmptyBorder(0, 8, 0, 8)));
                setToolTipText(valor == null ? null : valor.toString());
                return this;
            }
        });
        tablaEnvios.getColumnModel().getColumn(4).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override public java.awt.Component getTableCellRendererComponent(javax.swing.JTable tabla,
                    Object valor, boolean seleccionado, boolean foco, int fila, int columna) {
                super.getTableCellRendererComponent(tabla, valor, seleccionado, foco, fila, columna);
                String estado = valor == null ? "" : valor.toString();
                setText(estado.isEmpty() ? "" : "▪  " + estado);
                setToolTipText(estado);
                setForeground(seleccionado ? tabla.getSelectionForeground() : new java.awt.Color(0x245A81));
                setBorder(javax.swing.BorderFactory.createCompoundBorder(getBorder(),
                        javax.swing.BorderFactory.createEmptyBorder(0, 8, 0, 8)));
                return this;
            }
        });
        int[] anchos = {62, 200, 200, 165, 155};
        for (int i = 0; i < anchos.length; i++) {
            tablaEnvios.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            tablaEnvios.getColumnModel().getColumn(i).setMinWidth(i == 0 ? 54 : i == 4 ? 140 : 100);
        }
        tablaEnvios.getColumnModel().getColumn(0).setMaxWidth(76);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        raiz = new javax.swing.JPanel();
        cabecera = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        modulos = new javax.swing.JPanel();
        lblVista1 = new javax.swing.JLabel();
        btnInicio = new javax.swing.JButton();
        btnEnvios = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnMensajeros = new javax.swing.JButton();
        btnUsuarios = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        espacio2 = new javax.swing.JPanel();
        separador3 = new javax.swing.JSeparator();
        btnSalir = new javax.swing.JButton();
        pie = new javax.swing.JPanel();
        panelVista4 = new javax.swing.JPanel();
        panelVista5 = new javax.swing.JPanel();
        panelVista6 = new javax.swing.JPanel();
        lblVista7 = new javax.swing.JLabel();
        panelVista8 = new javax.swing.JPanel();
        lblVista9 = new javax.swing.JLabel();
        lblAyuda = new javax.swing.JLabel();
        btnNuevoEnvio = new javax.swing.JButton();
        panelVista10 = new javax.swing.JPanel();
        panelVista11 = new javax.swing.JPanel();
        panelVista12 = new javax.swing.JPanel();
        lblVista13 = new javax.swing.JLabel();
        barraResumen = new javax.swing.JPanel();
        lblVista14 = new javax.swing.JLabel();
        scrollEnvios = new javax.swing.JScrollPane();
        tablaEnvios = new javax.swing.JTable();
        lblVista15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Control de Mensajería");
        setMinimumSize(new java.awt.Dimension(1000, 660));
        setPreferredSize(new java.awt.Dimension(1120, 700));

        raiz.setBackground(new java.awt.Color(255, 255, 255));
        raiz.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        raiz.setLayout(new java.awt.BorderLayout());

        cabecera.setBackground(new java.awt.Color(255, 255, 255));
        cabecera.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(212, 220, 227)), javax.swing.BorderFactory.createEmptyBorder(12, 16, 12, 16)));
        cabecera.setLayout(new java.awt.BorderLayout(20, 0));

        lblTitulo.setForeground(new java.awt.Color(20, 33, 61));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitulo.setText("Control de mensajería");
        cabecera.add(lblTitulo, java.awt.BorderLayout.CENTER);

        lblUsuario.setForeground(new java.awt.Color(107, 115, 133));
        lblUsuario.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblUsuario.setText("Usuario conectado");
        cabecera.add(lblUsuario, java.awt.BorderLayout.EAST);

        raiz.add(cabecera, java.awt.BorderLayout.NORTH);

        modulos.setBackground(new java.awt.Color(240, 243, 246));
        modulos.setPreferredSize(new java.awt.Dimension(162, 0));
        modulos.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(212, 220, 227)), javax.swing.BorderFactory.createEmptyBorder(18, 10, 12, 10)));
        modulos.setAlignmentX(0.0F);
        modulos.setLayout(new javax.swing.BoxLayout(modulos, javax.swing.BoxLayout.Y_AXIS));

        lblVista1.setForeground(new java.awt.Color(107, 115, 133));
        lblVista1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblVista1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 12, 14, 0));
        lblVista1.setText("Navegación");
        modulos.add(lblVista1);

        btnInicio.setBackground(new java.awt.Color(223, 234, 243));
        btnInicio.setForeground(new java.awt.Color(36, 90, 129));
        btnInicio.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnInicio.setPreferredSize(new java.awt.Dimension(163, 38));
        btnInicio.setMaximumSize(new java.awt.Dimension(2147483647, 38));
        btnInicio.setText("Inicio");
        btnInicio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });
        modulos.add(btnInicio);

        btnEnvios.setBackground(new java.awt.Color(240, 243, 246));
        btnEnvios.setForeground(new java.awt.Color(30, 36, 51));
        btnEnvios.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnEnvios.setPreferredSize(new java.awt.Dimension(163, 38));
        btnEnvios.setMaximumSize(new java.awt.Dimension(2147483647, 38));
        btnEnvios.setText("Envíos");
        btnEnvios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEnvios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviosActionPerformed(evt);
            }
        });
        modulos.add(btnEnvios);

        btnClientes.setBackground(new java.awt.Color(240, 243, 246));
        btnClientes.setForeground(new java.awt.Color(30, 36, 51));
        btnClientes.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnClientes.setPreferredSize(new java.awt.Dimension(163, 38));
        btnClientes.setMaximumSize(new java.awt.Dimension(2147483647, 38));
        btnClientes.setText("Clientes");
        btnClientes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        modulos.add(btnClientes);

        btnMensajeros.setBackground(new java.awt.Color(240, 243, 246));
        btnMensajeros.setForeground(new java.awt.Color(30, 36, 51));
        btnMensajeros.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnMensajeros.setPreferredSize(new java.awt.Dimension(163, 38));
        btnMensajeros.setMaximumSize(new java.awt.Dimension(2147483647, 38));
        btnMensajeros.setText("Mensajeros");
        btnMensajeros.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMensajeros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMensajerosActionPerformed(evt);
            }
        });
        modulos.add(btnMensajeros);

        btnUsuarios.setBackground(new java.awt.Color(240, 243, 246));
        btnUsuarios.setForeground(new java.awt.Color(30, 36, 51));
        btnUsuarios.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnUsuarios.setPreferredSize(new java.awt.Dimension(163, 38));
        btnUsuarios.setMaximumSize(new java.awt.Dimension(2147483647, 38));
        btnUsuarios.setText("Usuarios");
        btnUsuarios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
            }
        });
        modulos.add(btnUsuarios);

        btnReportes.setBackground(new java.awt.Color(240, 243, 246));
        btnReportes.setForeground(new java.awt.Color(30, 36, 51));
        btnReportes.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnReportes.setPreferredSize(new java.awt.Dimension(163, 38));
        btnReportes.setMaximumSize(new java.awt.Dimension(2147483647, 38));
        btnReportes.setText("Reportes");
        btnReportes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });
        modulos.add(btnReportes);

        espacio2.setBackground(new java.awt.Color(240, 243, 246));
        espacio2.setMinimumSize(new java.awt.Dimension(0, 0));
        espacio2.setPreferredSize(new java.awt.Dimension(0, 0));
        espacio2.setMaximumSize(new java.awt.Dimension(0, 32767));
        espacio2.setOpaque(false);
        modulos.add(espacio2);

        separador3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        separador3.setMaximumSize(new java.awt.Dimension(2147483647, 1));
        separador3.setAlignmentX(0.0F);
        modulos.add(separador3);

        btnSalir.setBackground(new java.awt.Color(240, 243, 246));
        btnSalir.setForeground(new java.awt.Color(30, 36, 51));
        btnSalir.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnSalir.setPreferredSize(new java.awt.Dimension(163, 38));
        btnSalir.setMaximumSize(new java.awt.Dimension(2147483647, 38));
        btnSalir.setText("Cerrar sesión");
        btnSalir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        modulos.add(btnSalir);

        raiz.add(modulos, java.awt.BorderLayout.WEST);

        pie.setBackground(new java.awt.Color(255, 255, 255));
        pie.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 18, 12, 18));
        pie.setLayout(new java.awt.BorderLayout(0, 14));

        panelVista4.setPreferredSize(new java.awt.Dimension(0, 0));
        panelVista4.setOpaque(false);
        panelVista4.setLayout(new java.awt.BorderLayout(14, 0));

        panelVista5.setBackground(new java.awt.Color(243, 245, 247));
        panelVista5.setMinimumSize(new java.awt.Dimension(198, 0));
        panelVista5.setPreferredSize(new java.awt.Dimension(228, 0));
        panelVista5.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(212, 220, 227)), javax.swing.BorderFactory.createEmptyBorder(14, 12, 12, 12)));
        panelVista5.setLayout(new java.awt.BorderLayout(0, 12));

        panelVista6.setOpaque(false);
        panelVista6.setLayout(new java.awt.BorderLayout(0, 16));

        lblVista7.setForeground(new java.awt.Color(20, 33, 61));
        lblVista7.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblVista7.setText("Inicio");
        panelVista6.add(lblVista7, java.awt.BorderLayout.NORTH);

        panelVista8.setOpaque(false);
        panelVista8.setLayout(new java.awt.BorderLayout(0, 7));

        lblVista9.setForeground(new java.awt.Color(107, 115, 133));
        lblVista9.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblVista9.setText("Resumen de operación");
        panelVista8.add(lblVista9, java.awt.BorderLayout.NORTH);

        lblAyuda.setForeground(new java.awt.Color(20, 33, 61));
        lblAyuda.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblAyuda.setText("<html>Pendientes: 0<br>En tránsito: 0<br>Entregados: 0</html>");
        lblAyuda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        panelVista8.add(lblAyuda, java.awt.BorderLayout.CENTER);

        panelVista6.add(panelVista8, java.awt.BorderLayout.CENTER);

        panelVista5.add(panelVista6, java.awt.BorderLayout.NORTH);

        btnNuevoEnvio.setBackground(new java.awt.Color(245, 166, 35));
        btnNuevoEnvio.setForeground(new java.awt.Color(20, 33, 61));
        btnNuevoEnvio.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnNuevoEnvio.setPreferredSize(new java.awt.Dimension(194, 38));
        btnNuevoEnvio.setMaximumSize(new java.awt.Dimension(2147483647, 38));
        btnNuevoEnvio.setText("Nuevo envío");
        btnNuevoEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoEnvioActionPerformed(evt);
            }
        });
        panelVista5.add(btnNuevoEnvio, java.awt.BorderLayout.SOUTH);

        panelVista4.add(panelVista5, java.awt.BorderLayout.WEST);

        panelVista10.setOpaque(false);
        panelVista10.setLayout(new java.awt.BorderLayout(0, 10));

        panelVista11.setOpaque(false);
        panelVista11.setLayout(new java.awt.BorderLayout(0, 10));

        panelVista12.setOpaque(false);
        panelVista12.setLayout(new java.awt.BorderLayout(12, 0));

        lblVista13.setForeground(new java.awt.Color(20, 33, 61));
        lblVista13.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblVista13.setText("Envíos recientes");
        panelVista12.add(lblVista13, java.awt.BorderLayout.WEST);

        panelVista11.add(panelVista12, java.awt.BorderLayout.NORTH);

        barraResumen.setBackground(new java.awt.Color(255, 255, 255));
        barraResumen.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(212, 220, 227)), javax.swing.BorderFactory.createEmptyBorder(8, 0, 8, 0)));
        barraResumen.setLayout(new java.awt.BorderLayout(20, 0));

        lblVista14.setForeground(new java.awt.Color(107, 115, 133));
        lblVista14.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblVista14.setText("Últimos 10 registros · Todos los estados");
        barraResumen.add(lblVista14, java.awt.BorderLayout.EAST);

        panelVista11.add(barraResumen, java.awt.BorderLayout.SOUTH);

        panelVista10.add(panelVista11, java.awt.BorderLayout.NORTH);

        scrollEnvios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(212, 220, 227)));

        tablaEnvios.setForeground(new java.awt.Color(30, 36, 51));
        tablaEnvios.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tablaEnvios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Cliente", "Destinatario", "Mensajero", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaEnvios.setRowHeight(32);
        tablaEnvios.setFillsViewportHeight(true);
        tablaEnvios.setSelectionBackground(new java.awt.Color(223, 234, 243));
        tablaEnvios.setSelectionForeground(new java.awt.Color(20, 33, 61));
        tablaEnvios.setGridColor(new java.awt.Color(232, 237, 241));
        tablaEnvios.setShowHorizontalLines(true);
        scrollEnvios.setViewportView(tablaEnvios);

        panelVista10.add(scrollEnvios, java.awt.BorderLayout.CENTER);

        panelVista4.add(panelVista10, java.awt.BorderLayout.CENTER);

        pie.add(panelVista4, java.awt.BorderLayout.CENTER);

        lblVista15.setForeground(new java.awt.Color(107, 115, 133));
        lblVista15.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblVista15.setText("Para consultar o modificar un registro, abra Envíos.");
        pie.add(lblVista15, java.awt.BorderLayout.SOUTH);

        raiz.add(pie, java.awt.BorderLayout.CENTER);

        getContentPane().add(raiz, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        mostrarInicio();
        refrescarInicio();
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnNuevoEnvioActionPerformed(java.awt.event.ActionEvent evt) {
        if (Sesion.getUsuario() == null) return;
        if (nuevoEnvio != null && nuevoEnvio.isDisplayable()) {
            nuevoEnvio.setVisible(true);
            nuevoEnvio.toFront();
            return;
        }
        nuevoEnvio = crearNuevoEnvio();
        nuevoEnvio.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowClosed(java.awt.event.WindowEvent e) {
                NuevoEnvioFrame cerrada = (NuevoEnvioFrame) e.getWindow();
                if (nuevoEnvio == cerrada) nuevoEnvio = null;
                if (cerrada.fueCreado() && isDisplayable() && Sesion.getUsuario() != null) {
                    refrescarInicio();
                }
            }
        });
        nuevoEnvio.setVisible(true);
    }

    NuevoEnvioFrame crearNuevoEnvio() {
        return new NuevoEnvioFrame(this);
    }

    private void btnEnviosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviosActionPerformed
        mostrar("Envíos");
    }//GEN-LAST:event_btnEnviosActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        mostrar("Clientes");
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnMensajerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMensajerosActionPerformed
        mostrar("Mensajeros");
    }//GEN-LAST:event_btnMensajerosActionPerformed

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
        mostrar("Usuarios");
    }//GEN-LAST:event_btnUsuariosActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        mostrar("Reportes");
    }//GEN-LAST:event_btnReportesActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        cerrarSesion();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void refrescarInicio() {
        if (!isDisplayable() || Sesion.getUsuario() == null) return;
        modeloEnvios.setRowCount(0);
        Estilo.intentar(this, () -> {
            Map<String, Integer> conteo = envioDAO.contarPorEstado();
            int pendientes = conteo.getOrDefault(Envio.REGISTRADO, 0)
                    + conteo.getOrDefault(Envio.ASIGNADO, 0) + conteo.getOrDefault(Envio.EN_TRANSITO, 0);
            int transito = conteo.getOrDefault(Envio.EN_TRANSITO, 0);
            int entregados = conteo.getOrDefault(Envio.ENTREGADO, 0);
            lblAyuda.setText("<html>Pendientes: " + pendientes + "<br>En tránsito: " + transito
                    + "<br>Entregados: " + entregados + "</html>");
            List<Envio> recientes = envioDAO.listar("", null);
            for (Envio envio : recientes.stream().limit(10).toList()) {
                modeloEnvios.addRow(new Object[]{envio.id(), envio.clienteNombre(), envio.nombreDestinatario(),
                    envio.mensajeroNombre() == null ? "Sin asignar" : envio.mensajeroNombre(), envio.estado()});
            }
        });
    }

    public void mostrar(String nombre) {
        if ("Inicio".equals(nombre)) {
            mostrarInicio();
            return;
        }
        Supplier<JFrame> fabrica = fabricas.get(nombre);
        if (fabrica == null || Sesion.getUsuario() == null) return;
        JFrame ventana = ventanas.get(nombre);
        if (ventana == null || !ventana.isDisplayable()) {
            ventana = fabrica.get();
            if (!ventana.isDisplayable()) ventana.addNotify();
            ventanas.put(nombre, ventana);
            java.awt.Container vistaModulo = ventana.getContentPane();
            // Desvincular del JRootPane evita que su layout reserve el espacio de la barra de título.
            ventana.setContentPane(new JPanel());
            vistasModulos.put(nombre, vistaModulo);
            final JFrame modulo = ventana;
            ((javax.swing.JComponent) modulo.getContentPane()).putClientProperty("cerrarModulo", (Runnable) this::mostrarInicio);
        }
        raiz.remove(contenidoActivo);
        // Conserva un desplazamiento para el módulo completo: si el usuario
        // amplía la tabla, todavía puede bajar hasta el detalle del envío.
        javax.swing.JScrollPane vista = new javax.swing.JScrollPane(vistasModulos.get(nombre));
        vista.setBorder(null);
        vista.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        vista.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        vista.getVerticalScrollBar().setUnitIncrement(20);
        contenidoActivo = vista;
        raiz.add(vista, java.awt.BorderLayout.CENTER);
        seleccionarModulo(nombre);
        raiz.revalidate();
        raiz.repaint();
        ((VistaModulo) ventana).refrescar();
    }

    private void mostrarInicio() {
        if (contenidoActivo != pie) {
            raiz.remove(contenidoActivo);
            contenidoActivo = pie;
            raiz.add(pie, java.awt.BorderLayout.CENTER);
        }
        seleccionarModulo("Inicio");
        raiz.revalidate();
        raiz.repaint();
    }

    private void seleccionarModulo(String nombre) {
        for (javax.swing.JButton boton : new javax.swing.JButton[]{btnInicio, btnEnvios, btnClientes,
                btnMensajeros, btnUsuarios, btnReportes}) {
            boolean activo = nombre.equals(boton.getText());
            boton.setBackground(new java.awt.Color(activo ? 0xDFEAF3 : 0xF0F3F6));
            boton.setFont(boton.getFont().deriveFont(activo ? java.awt.Font.BOLD : java.awt.Font.PLAIN));
        }
    }

    private void cerrarSesion() {
        if (Estilo.confirmar(this, "¿Desea cerrar la sesión?")) {
            Sesion.cerrar();
            dispose();
            new LoginFrame().setVisible(true);
        }
    }

    @Override public void dispose() {
        if (nuevoEnvio != null) {
            NuevoEnvioFrame abierta = nuevoEnvio;
            nuevoEnvio = null;
            abierta.dispose();
        }
        for (JFrame ventana : ventanas.values()) ventana.dispose();
        ventanas.clear();
        vistasModulos.clear();
        super.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel barraResumen;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnEnvios;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnMensajeros;
    private javax.swing.JButton btnNuevoEnvio;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JPanel cabecera;
    private javax.swing.JPanel espacio2;
    private javax.swing.JLabel lblAyuda;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblVista1;
    private javax.swing.JLabel lblVista13;
    private javax.swing.JLabel lblVista14;
    private javax.swing.JLabel lblVista15;
    private javax.swing.JLabel lblVista7;
    private javax.swing.JLabel lblVista9;
    private javax.swing.JPanel modulos;
    private javax.swing.JPanel panelVista10;
    private javax.swing.JPanel panelVista11;
    private javax.swing.JPanel panelVista12;
    private javax.swing.JPanel panelVista4;
    private javax.swing.JPanel panelVista5;
    private javax.swing.JPanel panelVista6;
    private javax.swing.JPanel panelVista8;
    private javax.swing.JPanel pie;
    private javax.swing.JPanel raiz;
    private javax.swing.JScrollPane scrollEnvios;
    private javax.swing.JSeparator separador3;
    private javax.swing.JTable tablaEnvios;
    // End of variables declaration//GEN-END:variables
}


















