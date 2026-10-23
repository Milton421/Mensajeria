package com.mensajeria.ui;

import com.mensajeria.dao.EnvioDAO;
import com.mensajeria.model.Envio;
import com.mensajeria.util.Estilo;
import com.mensajeria.util.Sesion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/** Ventana de envios por estado y ultimos envios pendientes / INICIO */
public class InicioFrame extends javax.swing.JFrame implements VistaModulo {

    private final MainFrame ventana;
    private final EnvioDAO dao = new EnvioDAO();
    private final DefaultTableModel modelo = Estilo.modelo("No.", "Cliente", "Destinatario", "Mensajero", "Estado");


    public InicioFrame() { this(null); }

    public InicioFrame(MainFrame ventana) {
        this.ventana = ventana;
        initComponents();
        tabla.setModel(modelo);
        Estilo.configurarCabeceraTabla(tabla);
        tabla.getColumnModel().getColumn(0).setMaxWidth(70);
        Estilo.renderizarEstado(tabla, 4);
        if (Sesion.getUsuario() != null) lblSaludo.setText("Hola, " + Sesion.getUsuario().nombreUsuario());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        raiz = new javax.swing.JPanel();
        cabecera = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        contenido = new javax.swing.JPanel();
        resumen = new javax.swing.JPanel();
        lblSaludo = new javax.swing.JLabel();
        tarjetas = new javax.swing.JPanel();
        pendientes = new javax.swing.JPanel();
        lblPendientes = new javax.swing.JLabel();
        scrollTabla = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Inicio");
        setMinimumSize(new java.awt.Dimension(1000, 660));
        setPreferredSize(new java.awt.Dimension(1180, 740));

        raiz.setBackground(new java.awt.Color(255, 255, 255));
        raiz.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 22, 18, 22));
        raiz.setLayout(new java.awt.BorderLayout(16, 18));

        cabecera.setBackground(new java.awt.Color(255, 255, 255));
        cabecera.setLayout(new java.awt.BorderLayout(12, 12));

        lblTitulo.setForeground(new java.awt.Color(30, 36, 51));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitulo.setText("Inicio");
        cabecera.add(lblTitulo, java.awt.BorderLayout.CENTER);

        btnCerrar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(20, 33, 61));
        btnCerrar.setText("Cerrar ventana");
        btnCerrar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 226, 236)), javax.swing.BorderFactory.createEmptyBorder(7, 12, 7, 12)));
        btnCerrar.setPreferredSize(new java.awt.Dimension(150, 38));
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        cabecera.add(btnCerrar, java.awt.BorderLayout.EAST);

        raiz.add(cabecera, java.awt.BorderLayout.NORTH);

        contenido.setBackground(new java.awt.Color(255, 255, 255));
        contenido.setLayout(new java.awt.BorderLayout(0, 20));

        resumen.setBackground(new java.awt.Color(255, 255, 255));
        resumen.setLayout(new java.awt.BorderLayout(12, 12));

        lblSaludo.setForeground(new java.awt.Color(30, 36, 51));
        lblSaludo.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblSaludo.setText("Resumen de operación");
        resumen.add(lblSaludo, java.awt.BorderLayout.NORTH);

        tarjetas.setBackground(new java.awt.Color(255, 255, 255));
        tarjetas.setPreferredSize(new java.awt.Dimension(0, 48));
        tarjetas.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 6));
        resumen.add(tarjetas, java.awt.BorderLayout.CENTER);

        contenido.add(resumen, java.awt.BorderLayout.NORTH);

        pendientes.setBackground(new java.awt.Color(255, 255, 255));
        pendientes.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 226, 236)), javax.swing.BorderFactory.createEmptyBorder(12, 14, 12, 14)));
        pendientes.setLayout(new java.awt.BorderLayout(12, 12));

        lblPendientes.setForeground(new java.awt.Color(30, 36, 51));
        lblPendientes.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblPendientes.setText("Envíos pendientes recientes");
        pendientes.add(lblPendientes, java.awt.BorderLayout.NORTH);

        tabla.setForeground(new java.awt.Color(30, 36, 51));
        tabla.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla.setRowHeight(32);
        tabla.setFillsViewportHeight(true);
        tabla.setSelectionBackground(new java.awt.Color(223, 234, 243));
        tabla.setSelectionForeground(new java.awt.Color(20, 33, 61));
        tabla.setGridColor(new java.awt.Color(229, 234, 240));
        tabla.setShowHorizontalLines(true);
        scrollTabla.setViewportView(tabla);

        pendientes.add(scrollTabla, java.awt.BorderLayout.CENTER);

        contenido.add(pendientes, java.awt.BorderLayout.CENTER);

        raiz.add(contenido, java.awt.BorderLayout.CENTER);

        getContentPane().add(raiz, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        cerrarVista(this);
    }//GEN-LAST:event_btnCerrarActionPerformed

    @Override
    public void refrescar() {
        tarjetas.removeAll();
        modelo.setRowCount(0);
        Estilo.intentar(this, () -> {
            Map<String, Integer> conteo = dao.contarPorEstado();
            for (Map.Entry<String, Integer> en : conteo.entrySet()) {
                JLabel estado = new JLabel("▪  " + en.getKey() + ": " + en.getValue());
                estado.setFont(Estilo.F_NORMAL);
                estado.setForeground(Estilo.MARINO_CLARO);
                tarjetas.add(estado);
            }
            List<Envio> pendientes = dao.listar("", EnvioDAO.PENDIENTES);
            for (Envio e : pendientes.stream().limit(12).toList()) {
                modelo.addRow(new Object[]{e.id(), e.clienteNombre(), e.nombreDestinatario(),
                    e.mensajeroNombre() == null ? "Sin asignar" : e.mensajeroNombre(), e.estado()});
            }
        });
        tarjetas.revalidate();
        tarjetas.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JPanel cabecera;
    private javax.swing.JPanel contenido;
    private javax.swing.JLabel lblPendientes;
    private javax.swing.JLabel lblSaludo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pendientes;
    private javax.swing.JPanel raiz;
    private javax.swing.JPanel resumen;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JTable tabla;
    private javax.swing.JPanel tarjetas;
    // End of variables declaration//GEN-END:variables
}
