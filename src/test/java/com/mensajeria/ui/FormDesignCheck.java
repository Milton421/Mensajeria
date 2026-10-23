package com.mensajeria.ui;

import com.formdev.flatlaf.FlatLightLaf;
import com.mensajeria.model.Usuario;
import com.mensajeria.util.Sesion;
import java.awt.*;
import java.beans.Introspector;
import java.lang.reflect.Field;
import java.nio.file.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

/** Detecta diferencias entre el árbol de Design y los formularios ejecutables, sin Oracle. */
public final class FormDesignCheck {
    private static int controls;

    public static void main(String[] args) throws Exception {
        FlatLightLaf.setup();
        SwingUtilities.invokeAndWait(() -> {
            try {
                Sesion.iniciar(new Usuario(1, 1, "Administrador", "Prueba Design", "Activo"));
                JFrame[] forms = {new MainFrame(), new LoginFrame(), new InicioFrame(), new ClienteFrame(),
                    new MensajeroFrame(), new UsuarioFrame(), new EnvioFrame(), new NuevoEnvioFrame(), new ReporteFrame()};
                for (JFrame frame : forms) {
                    Path path = Path.of("src/main/java/com/mensajeria/ui", frame.getClass().getSimpleName() + ".form");
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
                    Element root = factory.newDocumentBuilder().parse(path.toFile()).getDocumentElement();
                    checkProperties(frame, child(root, "Properties"), "frame");
                    checkContainer(frame, frame.getContentPane(), root);
                }
                System.out.println("OK: 9 archivos .form; " + controls + " controles, jerarquías, layouts y propiedades coinciden.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                for (Window w : Window.getWindows()) w.dispose();
                Sesion.cerrar();
            }
        });
    }

    private static void checkContainer(JFrame frame, Container container, Element definition) throws Exception {
        Element layout = child(definition, "Layout");
        if (layout != null && !(container instanceof JScrollPane || container instanceof JSplitPane)) {
            String expected = layout.getAttribute("class");
            require(expected.endsWith("Design" + container.getLayout().getClass().getSimpleName()),
                    frame.getClass().getSimpleName() + ": layout de " + definition.getAttribute("name"));
            for (Element property : children(layout)) {
                String key = property.getAttribute("name");
                key = switch (key) { case "horizontalGap" -> "hgap"; case "verticalGap" -> "vgap"; default -> key; };
                checkValue(container.getLayout(), key, Integer.valueOf(property.getAttribute("value")), "layout");
            }
        }
        List<Element> definitions = children(child(definition, "SubComponents"));
        List<Component> actual;
        if (container instanceof JScrollPane scroll) actual = List.of(scroll.getViewport().getView());
        else if (container instanceof JSplitPane split) actual = List.of(split.getLeftComponent(), split.getRightComponent());
        else actual = Arrays.asList(container.getComponents());
        require(definitions.size() == actual.size(), "Cantidad de controles en " + definition.getAttribute("name"));
        for (int i = 0; i < definitions.size(); i++) {
            Element component = definitions.get(i);
            String name = component.getAttribute("name");
            Field field = frame.getClass().getDeclaredField(name);
            field.setAccessible(true);
            Component value = (Component) field.get(frame);
            require(value == actual.get(i), frame.getClass().getSimpleName() + ": padre/orden de " + name);
            require(value.getClass().getName().equals(component.getAttribute("class")), "Clase de " + name);
            checkProperties(value, child(component, "Properties"), name);
            Element constraints = child(child(component, "Constraints"), "Constraint");
            if (constraints != null && container.getLayout() instanceof BorderLayout border) {
                require(border.getConstraints(value).equals(child(constraints, "BorderConstraints").getAttribute("direction")), "BorderLayout de " + name);
            }
            if (constraints != null && container.getLayout() instanceof GridBagLayout grid) {
                Element xml = child(constraints, "GridBagConstraints");
                GridBagConstraints a = grid.getConstraints(value);
                require(a.gridx == number(xml, "gridX") && a.gridy == number(xml, "gridY")
                        && a.gridwidth == number(xml, "gridWidth") && a.gridheight == number(xml, "gridHeight")
                        && a.fill == number(xml, "fill") && a.anchor == number(xml, "anchor")
                        && a.insets.equals(new Insets(number(xml,"insetsTop"), number(xml,"insetsLeft"), number(xml,"insetsBottom"), number(xml,"insetsRight"))),
                        "GridBagLayout de " + name);
            }
            if (component.getTagName().equals("Container")) checkContainer(frame, (Container) value, component);
            for (Element event : children(child(component, "Events"))) {
                frame.getClass().getDeclaredMethod(event.getAttribute("handler"), Class.forName(event.getAttribute("parameters")));
                if (value instanceof JButton button) require(button.getActionListeners().length == 1, "Evento de " + name);
            }
            controls++;
        }
    }

    private static void checkProperties(Object bean, Element properties, String name) throws Exception {
        for (Element p : children(properties)) {
            String key = p.getAttribute("name");
            // Text and models are populated by the active session/database; borders have nested editors.
            if (Set.of("text", "model", "border", "dividerLocation").contains(key)) continue;
            Object expected;
            switch (p.getAttribute("type")) {
                case "int" -> expected = Integer.valueOf(p.getAttribute("value"));
                case "boolean" -> expected = Boolean.valueOf(p.getAttribute("value"));
                case "float" -> expected = Float.valueOf(p.getAttribute("value"));
                case "double" -> expected = Double.valueOf(p.getAttribute("value"));
                case "java.lang.String" -> expected = p.getAttribute("value");
                case "java.awt.Dimension" -> {
                    String[] size = child(p, "Dimension").getAttribute("value").replaceAll("[\\[\\] ]", "").split(",");
                    expected = new Dimension(Integer.parseInt(size[0]), Integer.parseInt(size[1]));
                }
                case "java.awt.Color" -> {
                    Element c = child(p,"Color");
                    expected = new Color(Integer.parseInt(c.getAttribute("red"),16), Integer.parseInt(c.getAttribute("green"),16), Integer.parseInt(c.getAttribute("blue"),16));
                }
                case "java.awt.Font" -> {
                    Element f = child(p,"Font");
                    expected = new Font(f.getAttribute("name"), number(f,"style"), number(f,"size"));
                }
                default -> throw new AssertionError("Propiedad sin verificar: " + key);
            }
            checkValue(bean, key, expected, name);
        }
    }
    private static void checkValue(Object bean, String key, Object expected, String name) throws Exception {
        for (var property : Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors()) {
            if (property.getName().equals(key)) {
                Object actual = property.getReadMethod().invoke(bean);
                require(expected.equals(actual), name + "." + key + ": Design=" + expected + "; ejecución=" + actual);
                return;
            }
        }
        throw new AssertionError("Propiedad inexistente: " + name + "." + key);
    }
    private static int number(Element e, String key) { return Integer.parseInt(e.getAttribute(key)); }
    private static Element child(Element parent, String tag) {
        return children(parent).stream().filter(e -> e.getTagName().equals(tag)).findFirst().orElse(null);
    }
    private static List<Element> children(Element parent) {
        List<Element> result = new ArrayList<>();
        if (parent != null) for (Node n = parent.getFirstChild(); n != null; n = n.getNextSibling()) if (n instanceof Element e) result.add(e);
        return result;
    }
    private static void require(boolean ok, String message) { if (!ok) throw new AssertionError(message); }
}
