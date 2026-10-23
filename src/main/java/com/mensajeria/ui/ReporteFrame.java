package com.mensajeria.ui;

import com.mensajeria.dao.EnvioDAO;
import com.mensajeria.model.Envio;
import com.mensajeria.util.Estilo;
import com.mensajeria.util.ReportePDF;
import com.mensajeria.util.Sesion;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/** Vista previa y generacion de reportes PDF. */
public class ReporteFrame extends javax.swing.JFrame implements VistaModulo {

    private static final String[] REPORTES = {
        "Envíos registrados (todos)", "Entregas pendientes", "Paquetes entregados",
        "Entregas fallidas", "Envíos cancelados"};

    private static final DateTimeFormatter FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final EnvioDAO dao = new EnvioDAO();
    private List<Envio> datos = new ArrayList<>();
    private final DefaultTableModel modelo = Estilo.modelo(
            "No.", "Fecha", "Cliente", "Descripción", "Destinatario", "Mensajero", "Estado", "Entrega");


    public ReporteFrame() {
        initComponents();
        tabla.setModel(modelo);
        Estilo.configurarCabeceraTabla(tabla);
        tabla.getColumnModel().getColumn(0).setMaxWidth(70);
        Estilo.renderizarEstado(tabla, 6);
        cmbReporte.addActionListener(e -> cargar());
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        int[] anchos = {62, 100, 175, 220, 170, 150, 150, 105};
        for (int i = 0; i < anchos.length; i++) tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        raiz = new javax.swing.JPanel();
        cabecera = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        contenido = new javax.swing.JPanel();
        filtros = new javax.swing.JPanel();
        lblTipo = new javax.swing.JLabel();
        cmbReporte = new javax.swing.JComboBox<>();
        scrollTabla = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        panelVista1 = new javax.swing.JPanel();
        lblTotal = new javax.swing.JLabel();
        btnPdf = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reportes");
        setMinimumSize(new java.awt.Dimension(1000, 660));
        setPreferredSize(new java.awt.Dimension(1180, 740));

        raiz.setBackground(new java.awt.Color(243, 245, 247));
        raiz.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 28, 24, 28));
        raiz.setLayout(new java.awt.BorderLayout(16, 18));

        cabecera.setBackground(new java.awt.Color(243, 245, 247));
        cabecera.setLayout(new java.awt.BorderLayout(12, 12));

        lblTitulo.setForeground(new java.awt.Color(30, 36, 51));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitulo.setText("Reportes");
        cabecera.add(lblTitulo, java.awt.BorderLayout.CENTER);

        raiz.add(cabecera, java.awt.BorderLayout.NORTH);

        contenido.setBackground(new java.awt.Color(243, 245, 247));
        contenido.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 16, 12, 16));
        contenido.setLayout(new java.awt.BorderLayout(12, 12));

        filtros.setBackground(new java.awt.Color(243, 245, 247));
        filtros.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(221, 226, 236)), javax.swing.BorderFactory.createEmptyBorder(0, 0, 12, 0)));
        filtros.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 0));

        lblTipo.setForeground(new java.awt.Color(30, 36, 51));
        lblTipo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblTipo.setText("Tipo de reporte:");
        filtros.add(lblTipo);

        cmbReporte.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbReporte.setPreferredSize(new java.awt.Dimension(320, 36));
        cmbReporte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Envíos registrados (todos)", "Entregas pendientes", "Paquetes entregados", "Entregas fallidas", "Envíos cancelados" }));
        filtros.add(cmbReporte);

        contenido.add(filtros, java.awt.BorderLayout.NORTH);

        tabla.setForeground(new java.awt.Color(30, 36, 51));
        tabla.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Fecha", "Cliente", "Descripción", "Destinatario", "Mensajero", "Estado", "Entrega"
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
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        scrollTabla.setViewportView(tabla);

        contenido.add(scrollTabla, java.awt.BorderLayout.CENTER);

        panelVista1.setBackground(new java.awt.Color(255, 255, 255));
        panelVista1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(221, 226, 236)));
        panelVista1.setLayout(new java.awt.BorderLayout(12, 0));

        lblTotal.setForeground(new java.awt.Color(30, 36, 51));
        lblTotal.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblTotal.setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 0, 0, 0));
        lblTotal.setText("0 registros en este reporte");
        panelVista1.add(lblTotal, java.awt.BorderLayout.WEST);

        btnPdf.setBackground(new java.awt.Color(36, 90, 129));
        btnPdf.setForeground(new java.awt.Color(255, 255, 255));
        btnPdf.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnPdf.setPreferredSize(new java.awt.Dimension(145, 36));
        btnPdf.setText("Generar PDF");
        btnPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPdfActionPerformed(evt);
            }
        });
        panelVista1.add(btnPdf, java.awt.BorderLayout.EAST);

        contenido.add(panelVista1, java.awt.BorderLayout.SOUTH);

        raiz.add(contenido, java.awt.BorderLayout.CENTER);

        getContentPane().add(raiz, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPdfActionPerformed
        generarPdf();
    }//GEN-LAST:event_btnPdfActionPerformed

    @Override
    public void refrescar() {
        cargar();
    }

    private String estadoDelReporte() {
        switch (cmbReporte.getSelectedIndex()) {
            case 1:
                return EnvioDAO.PENDIENTES;
            case 2:
                return Envio.ENTREGADO;
            case 3:
                return Envio.FALLIDA;
            case 4:
                return Envio.CANCELADO;
            default:
                return null;
        }
    }

    private void cargar() {
        Estilo.intentar(this, () -> {
            datos = dao.listar("", estadoDelReporte());
            modelo.setRowCount(0);
            for (Envio e : datos) {
                modelo.addRow(new Object[]{e.id(), e.fechaEnvio().format(FECHA), e.clienteNombre(),
                    e.descripcionPaquete(), e.nombreDestinatario(),
                    e.mensajeroNombre() == null ? "Sin asignar" : e.mensajeroNombre(), e.estado(),
                    e.fechaEntrega() == null ? "" : e.fechaEntrega().format(FECHA)});
            }
            lblTotal.setText(datos.size() + " registro(s) en este reporte");
        });
    }

    private void generarPdf() {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Guardar reporte PDF");
        fc.setFileFilter(new FileNameExtensionFilter("Documento PDF (*.pdf)", "pdf"));
        fc.setSelectedFile(new File("reporte_"
                + REPORTES[cmbReporte.getSelectedIndex()].toLowerCase()
                .replaceAll("[^a-záéíóúñ]+", "_").replaceAll("^_|_$", "") + ".pdf"));
        if (fc.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File f = fc.getSelectedFile();
        if (!f.getName().toLowerCase().endsWith(".pdf")) {
            f = new File(f.getParentFile(), f.getName() + ".pdf");
        }
        try {
            ReportePDF.generar(f, (String) cmbReporte.getSelectedItem(), datos,
                    Sesion.getUsuario().nombreUsuario());
            if (Estilo.confirmar(this, "Reporte generado en:\n" + f.getAbsolutePath() + "\n\n¿Desea abrirlo ahora?")
                    && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(f);
            }
        } catch (IOException e) {
            Estilo.error(this, "No se pudo generar el PDF:\n" + e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPdf;
    private javax.swing.JPanel cabecera;
    private javax.swing.JComboBox<String> cmbReporte;
    private javax.swing.JPanel contenido;
    private javax.swing.JPanel filtros;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel panelVista1;
    private javax.swing.JPanel raiz;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
