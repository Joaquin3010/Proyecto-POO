package SistemadeRegistro.RegistrodeVehiculos;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.*;

public class VehiculoGUITest {

    private VehiculoGUI gui;

    @Before
    public void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> gui = new VehiculoGUI(null, null));
        Thread.sleep(500); // pequeño retardo para asegurar que la ventana se cargue
    }

    private JButton findRegistrarButton(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JButton) {
                JButton btn = (JButton) comp;
                if ("Registrar Vehiculo".equals(btn.getText())) {
                    return btn;
                }
            } else if (comp instanceof Container) {
                JButton btn = findRegistrarButton((Container) comp);
                if (btn != null) return btn;
            }
        }
        return null;
    }

    private JTextField findTextField(Container container, String label) {
        Component[] components = container.getComponents();
        for (int i = 0; i < components.length - 1; i++) {
            if (components[i] instanceof JLabel && ((JLabel) components[i]).getText().contains(label)) {
                if (components[i + 1] instanceof JTextField) {
                    return (JTextField) components[i + 1];
                }
            }
        }
        return null;
    }

    @Test
    public void testRegistrarVehiculoConCamposVacios() throws Exception {
        JTextField marcaField = findTextField(gui.getContentPane(), "Marca");
        JTextField modeloField = findTextField(gui.getContentPane(), "Modelo");

        assertNotNull(marcaField);
        assertNotNull(modeloField);

        // Dejar vacíos los campos requeridos
        SwingUtilities.invokeAndWait(() -> {
            marcaField.setText("");
            modeloField.setText("");
        });

        JButton registrarBtn = findRegistrarButton(gui.getContentPane());
        assertNotNull("No se encontró el botón de registrar", registrarBtn);

        // Simular clic
        SwingUtilities.invokeAndWait(registrarBtn::doClick);

        // El test pasa si no hay excepción (ya que la GUI muestra un JOptionPane de advertencia)
        assertEquals("", marcaField.getText());
        assertEquals("", modeloField.getText());
    }
}
