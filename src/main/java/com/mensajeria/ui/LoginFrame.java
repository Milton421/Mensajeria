package com.mensajeria.ui;

import com.mensajeria.dao.UsuarioDAO;
import com.mensajeria.model.Usuario;
import com.mensajeria.util.Estilo;
import com.mensajeria.util.Sesion;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.SwingWorker;

public class LoginFrame extends JFrame {



    public LoginFrame() {
        initComponents();
        getRootPane().setDefaultButton(btnIngresar);
        txtClave.putClientProperty("JPasswordField.showRevealButton", true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        raiz = new javax.swing.JPanel();
        marca = new javax.swing.JPanel();
        lblMarca = new javax.swing.JLabel();
        formulario = new javax.swing.JPanel();
        campos = new javax.swing.JPanel();
        lblBienvenido = new javax.swing.JLabel();
        espacio1 = new javax.swing.JPanel();
        lblInstruccion = new javax.swing.JLabel();
        espacio2 = new javax.swing.JPanel();
        filaUsuario = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        filaClave = new javax.swing.JPanel();
        lblClave = new javax.swing.JLabel();
        txtClave = new javax.swing.JPasswordField();
        espacio3 = new javax.swing.JPanel();
        btnIngresar = new javax.swing.JButton();
        espacio4 = new javax.swing.JPanel();
        lblEstado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Acceso · Sistema de Control de Mensajería");
        setMinimumSize(new java.awt.Dimension(700, 480));
        setPreferredSize(new java.awt.Dimension(760, 500));

        raiz.setBackground(new java.awt.Color(255, 255, 255));
        raiz.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(212, 220, 227)));
        raiz.setLayout(new java.awt.BorderLayout());

        marca.setBackground(new java.awt.Color(36, 90, 129));
        marca.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 18, 24, 18));
        marca.setPreferredSize(new java.awt.Dimension(190, 0));
        marca.setLayout(new java.awt.BorderLayout(0, 12));

        lblMarca.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        lblMarca.setForeground(new java.awt.Color(255, 255, 255));
        lblMarca.setText("<html>Control de<br>Mensajería</html>");
        marca.add(lblMarca, java.awt.BorderLayout.NORTH);

        raiz.add(marca, java.awt.BorderLayout.WEST);

        formulario.setBackground(new java.awt.Color(255, 255, 255));
        formulario.setBorder(javax.swing.BorderFactory.createEmptyBorder(38, 42, 34, 42));
        formulario.setLayout(new java.awt.GridBagLayout());

        campos.setBackground(new java.awt.Color(255, 255, 255));
        campos.setAlignmentX(0.37617555F);
        campos.setLayout(new javax.swing.BoxLayout(campos, javax.swing.BoxLayout.Y_AXIS));

        lblBienvenido.setForeground(new java.awt.Color(20, 33, 61));
        lblBienvenido.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblBienvenido.setText("Iniciar sesión");
        campos.add(lblBienvenido);

        espacio1.setBackground(new java.awt.Color(255, 255, 255));
        espacio1.setMinimumSize(new java.awt.Dimension(0, 5));
        espacio1.setPreferredSize(new java.awt.Dimension(0, 5));
        espacio1.setMaximumSize(new java.awt.Dimension(32767, 5));
        espacio1.setOpaque(false);
        campos.add(espacio1);

        lblInstruccion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblInstruccion.setForeground(new java.awt.Color(107, 115, 133));
        lblInstruccion.setText("Ingrese con su usuario autorizado");
        campos.add(lblInstruccion);

        espacio2.setBackground(new java.awt.Color(255, 255, 255));
        espacio2.setMinimumSize(new java.awt.Dimension(0, 24));
        espacio2.setPreferredSize(new java.awt.Dimension(0, 24));
        espacio2.setMaximumSize(new java.awt.Dimension(32767, 24));
        espacio2.setOpaque(false);
        campos.add(espacio2);

        filaUsuario.setBackground(new java.awt.Color(255, 255, 255));
        filaUsuario.setPreferredSize(new java.awt.Dimension(390, 62));
        filaUsuario.setMaximumSize(new java.awt.Dimension(2147483647, 62));
        filaUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        filaUsuario.setAlignmentX(0.0F);
        filaUsuario.setLayout(new java.awt.BorderLayout(0, 6));

        lblUsuario.setForeground(new java.awt.Color(30, 36, 51));
        lblUsuario.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblUsuario.setText("Usuario");
        filaUsuario.add(lblUsuario, java.awt.BorderLayout.NORTH);

        txtUsuario.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtUsuario.setMinimumSize(new java.awt.Dimension(240, 34));
        txtUsuario.setPreferredSize(new java.awt.Dimension(390, 36));
        filaUsuario.add(txtUsuario, java.awt.BorderLayout.CENTER);

        campos.add(filaUsuario);

        filaClave.setBackground(new java.awt.Color(255, 255, 255));
        filaClave.setPreferredSize(new java.awt.Dimension(390, 62));
        filaClave.setMaximumSize(new java.awt.Dimension(2147483647, 62));
        filaClave.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        filaClave.setAlignmentX(0.0F);
        filaClave.setLayout(new java.awt.BorderLayout(0, 6));

        lblClave.setForeground(new java.awt.Color(30, 36, 51));
        lblClave.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblClave.setText("Contraseña");
        filaClave.add(lblClave, java.awt.BorderLayout.NORTH);

        txtClave.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtClave.setMinimumSize(new java.awt.Dimension(240, 34));
        txtClave.setPreferredSize(new java.awt.Dimension(390, 36));
        filaClave.add(txtClave, java.awt.BorderLayout.CENTER);

        campos.add(filaClave);

        espacio3.setBackground(new java.awt.Color(255, 255, 255));
        espacio3.setMinimumSize(new java.awt.Dimension(0, 8));
        espacio3.setPreferredSize(new java.awt.Dimension(0, 8));
        espacio3.setMaximumSize(new java.awt.Dimension(32767, 8));
        espacio3.setOpaque(false);
        campos.add(espacio3);

        btnIngresar.setBackground(new java.awt.Color(36, 90, 129));
        btnIngresar.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnIngresar.setPreferredSize(new java.awt.Dimension(148, 38));
        btnIngresar.setMaximumSize(new java.awt.Dimension(148, 38));
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        campos.add(btnIngresar);

        espacio4.setBackground(new java.awt.Color(255, 255, 255));
        espacio4.setMinimumSize(new java.awt.Dimension(0, 10));
        espacio4.setPreferredSize(new java.awt.Dimension(0, 10));
        espacio4.setMaximumSize(new java.awt.Dimension(32767, 10));
        espacio4.setOpaque(false);
        campos.add(espacio4);

        lblEstado.setForeground(new java.awt.Color(20, 33, 61));
        lblEstado.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblEstado.setPreferredSize(new java.awt.Dimension(390, 36));
        lblEstado.setText(" ");
        campos.add(lblEstado);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        formulario.add(campos, gridBagConstraints);

        raiz.add(formulario, java.awt.BorderLayout.CENTER);

        getContentPane().add(raiz, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        ingresar();
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void ingresar() {
        final String usuario = txtUsuario.getText().trim();
        final String clave = new String(txtClave.getPassword());
        if (usuario.isEmpty() || clave.isEmpty()) {
            lblEstado.setText("Escriba su usuario y contraseña.");
            return;
        }
        btnIngresar.setEnabled(false);
        lblEstado.setForeground(Estilo.TEXTO_SUAVE);
        lblEstado.setText("Conectando con la base de datos...");

        new SwingWorker<Usuario, Void>() {
            @Override
            protected Usuario doInBackground() throws Exception {
                UsuarioDAO dao = new UsuarioDAO();
                dao.asegurarAdminInicial();
                return dao.autenticar(usuario, clave);
            }

            @Override
            protected void done() {
                btnIngresar.setEnabled(true);
                try {
                    Usuario u = get();
                    if (u == null) {
                        lblEstado.setForeground(Estilo.ROJO);
                        lblEstado.setText("Usuario o contraseña incorrectos.");
                        txtClave.setText("");
                        return;
                    }
                    Sesion.iniciar(u);
                    new MainFrame().setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    Throwable causa = ex.getCause() != null ? ex.getCause() : ex;
                    lblEstado.setForeground(Estilo.ROJO);
                    lblEstado.setText("Sin conexión con Oracle. Revise db.properties.");
                    Estilo.error(LoginFrame.this, "No se pudo conectar o consultar la base de datos:\n"
                            + causa.getMessage());
                }
            }
        }.execute();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JPanel campos;
    private javax.swing.JPanel espacio1;
    private javax.swing.JPanel espacio2;
    private javax.swing.JPanel espacio3;
    private javax.swing.JPanel espacio4;
    private javax.swing.JPanel filaClave;
    private javax.swing.JPanel filaUsuario;
    private javax.swing.JPanel formulario;
    private javax.swing.JLabel lblBienvenido;
    private javax.swing.JLabel lblClave;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblInstruccion;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel marca;
    private javax.swing.JPanel raiz;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
