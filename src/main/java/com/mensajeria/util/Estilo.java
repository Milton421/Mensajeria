package com.mensajeria.util;

import com.mensajeria.model.Envio;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/** Colores, fuentes y utilidades visuales compartidas por toda la aplicacion. */
public final class Estilo {

    public static final Color MARINO = new Color(0x14213D);
    public static final Color MARINO_CLARO = new Color(0x1F3157);
    public static final Color ACENTO = new Color(0xF5A623);
    public static final Color FONDO = new Color(0xF4F6FA);
    public static final Color BLANCO = Color.WHITE;
    public static final Color TEXTO = new Color(0x1E2433);
    public static final Color TEXTO_SUAVE = new Color(0x6B7385);
    public static final Color BORDE = new Color(0xDDE2EC);
    public static final Color VERDE = new Color(0x2E9E6B);
    public static final Color ROJO = new Color(0xD64545);

    public static final Font F_TITULO = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font F_SUBTITULO = new Font("Segoe UI", Font.BOLD, 15);
    public static final Font F_NORMAL = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font F_NEGRITA = new Font("Segoe UI", Font.BOLD, 13);

    private Estilo() {
    }

    /** Accion de base de datos que puede lanzar SQLException. */
    @FunctionalInterface
    public interface AccionSql {
        void ejecutar() throws SQLException;
    }

    public static Color colorEstado(String estado) {
        if (estado == null) {
            return TEXTO_SUAVE;
        }
        switch (estado) {
            case Envio.REGISTRADO:
                return new Color(0x6C7A93);
            case Envio.ASIGNADO:
                return new Color(0x3B82F6);
            case Envio.EN_TRANSITO:
                return new Color(0xF59E0B);
            case Envio.ENTREGADO:
                return new Color(0x2E9E6B);
            case Envio.FALLIDA:
                return new Color(0xE0592A);
            case Envio.CANCELADO:
                return new Color(0xB4B8C2);
            default:
                return TEXTO_SUAVE;
        }
    }

    public static JLabel titulo(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(F_TITULO);
        l.setForeground(MARINO);
        return l;
    }

    public static JLabel etiqueta(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(F_NEGRITA);
        l.setForeground(TEXTO_SUAVE);
        return l;
    }

    public static JButton boton(String texto, Color fondo) {
        JButton b = new JButton(texto);
        b.setBackground(fondo);
        b.setForeground(fondo.equals(ACENTO) ? MARINO : Color.WHITE);
        b.setFont(F_NEGRITA);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(9, 16, 9, 16));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.putClientProperty("JButton.buttonType", "roundRect");
        return b;
    }

    public static JButton botonSuave(String texto) {
        JButton b = new JButton(texto);
        b.setBackground(Color.WHITE);
        b.setForeground(MARINO);
        b.setFont(F_NEGRITA);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE), BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.putClientProperty("JButton.buttonType", "roundRect");
        return b;
    }

    /** Modelo de tabla de solo lectura. */
    public static DefaultTableModel modelo(String... columnas) {
        return new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    public static void estilizarTabla(JTable t) {
        t.setRowHeight(32);
        t.setFont(F_NORMAL);
        t.setForeground(TEXTO);
        t.setShowVerticalLines(false);
        t.setShowHorizontalLines(true);
        t.setGridColor(new Color(0xE5EAF0));
        t.setIntercellSpacing(new Dimension(0, 1));
        t.setSelectionBackground(new Color(0xDFEAF3));
        t.setSelectionForeground(MARINO);
        t.setFillsViewportHeight(true);
        configurarCabeceraTabla(t);
    }

    /** El cuerpo de la tabla se configura en Design; el encabezado usa su renderer. */
    public static void configurarCabeceraTabla(JTable t) {
        JTableHeader h = t.getTableHeader();
        h.setFont(F_NEGRITA);
        h.setBackground(new Color(0xE9EEF3));
        h.setForeground(MARINO);
        h.setPreferredSize(new Dimension(0, 34));
        h.setReorderingAllowed(false);
        // Se conserva el renderer del Look and Feel para que tome el azul grisáceo
        // de la cabecera, igual que la tabla de Inicio.
        javax.swing.table.TableCellRenderer original = h.getDefaultRenderer();
        h.setDefaultRenderer((table, value, selected, focus, row, column) -> {
            Component c = original.getTableCellRendererComponent(
                    table, value, selected, focus, row, column);
            JLabel label = (JLabel) c;
            label.setHorizontalAlignment(SwingConstants.LEFT);
            label.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(0xD4DCE3)),
                    BorderFactory.createEmptyBorder(0, 10, 0, 10)));
            return label;
        });
    }

    /** Acabado de escritorio compartido; conserva disposición, acciones y permisos. */
    public static void estilizarVentana(JFrame ventana) {
        Color superficie = new Color(0xF2F4F6);
        ventana.getContentPane().setBackground(superficie);
        for (Component componente : ventana.getContentPane().getComponents()) {
            estilizarComponente(componente, superficie);
        }
    }

    private static void estilizarComponente(Component componente, Color superficie) {
        if (componente instanceof JTable tabla && tabla.getModel().getColumnCount() > 0) {
            // La ventana pudo instalar un renderer específico para estados después del estilo base.
            return;
        }
        if (componente instanceof JLabel etiqueta) {
            etiqueta.setForeground(TEXTO);
        }
        if (componente instanceof JTextField campo) {
            campo.setFont(F_NORMAL);
            campo.setPreferredSize(new Dimension(Math.max(180, campo.getPreferredSize().width), 34));
            campo.setMinimumSize(new Dimension(140, 34));
        }
        if (componente instanceof JButton boton) {
            boton.setFont(F_NEGRITA);
            boton.setFocusPainted(true);
            boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            boton.putClientProperty("FlatLaf.style", "arc: 4; focusColor: #245A81");
            if (!ACENTO.equals(boton.getBackground())) {
                boton.setBackground(Color.WHITE);
                boton.setForeground(MARINO);
                boton.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDE), BorderFactory.createEmptyBorder(7, 12, 7, 12)));
            }
        }
        if (componente instanceof JPanel panel) {
            if (panel.isOpaque() && !MARINO.equals(panel.getBackground())
                    && !MARINO_CLARO.equals(panel.getBackground())) {
                panel.setBackground(superficie.equals(panel.getBackground()) ? superficie : Color.WHITE);
            }
        }
        if (componente instanceof java.awt.Container contenedor) {
            for (Component hijo : contenedor.getComponents()) {
                estilizarComponente(hijo, superficie);
            }
        }
    }

    /** Indica el estado con texto legible y un marcador sencillo. */
    public static void renderizarEstado(JTable t, int columna) {
        t.getColumnModel().getColumn(columna).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tb, Object v, boolean sel,
                    boolean foco, int fila, int col) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(tb, "", sel, foco, fila, col);
                String estado = v == null ? "" : v.toString();
                l.setText(estado.isEmpty() ? "" : "▪  " + estado);
                l.setHorizontalAlignment(SwingConstants.LEFT);
                l.setForeground(sel ? tb.getSelectionForeground() : MARINO_CLARO);
                l.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 6));
                l.setToolTipText(estado);
                return l;
            }
        });
    }

    public static void info(Component padre, String mensaje) {
        JOptionPane.showMessageDialog(padre, mensaje, "Informacion", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void advertencia(Component padre, String mensaje) {
        JOptionPane.showMessageDialog(padre, mensaje, "Revise los datos", JOptionPane.WARNING_MESSAGE);
    }

    public static void error(Component padre, String mensaje) {
        JOptionPane.showMessageDialog(padre, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static boolean confirmar(Component padre, String mensaje) {
        return JOptionPane.showConfirmDialog(padre, mensaje, "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
    }

    /** Ejecuta una accion de BD y muestra el error si falla. Devuelve true si todo salio bien. */
    public static boolean intentar(Component padre, AccionSql accion) {
        try {
            accion.ejecutar();
            return true;
        } catch (SQLException e) {
            error(padre, "No se pudo completar la operacion:\n" + e.getMessage());
            return false;
        }
    }
}
