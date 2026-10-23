package com.mensajeria;

import com.formdev.flatlaf.FlatLightLaf;
import com.mensajeria.ui.LoginFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
        FlatLightLaf.setup();
        UIManager.put("Component.arc", 10);
        UIManager.put("Button.arc", 12);
        UIManager.put("TextComponent.arc", 10);
        UIManager.put("Component.focusColor", new java.awt.Color(0xF5A623));
        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.width", 10);
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
