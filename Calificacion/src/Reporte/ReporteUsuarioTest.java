package Reporte;

import static org.junit.Assert.*;

import java.awt.*;
import javax.swing.*;

import org.junit.Before;
import org.junit.Test;

public class ReporteUsuarioTest {

    private ReporteUsuario ventana;
    private JFrame frameInterno;
    private JButton choferButton;
    private JButton pasajeroButton;
    private JButton continuarButton;
    private JTextField rutaField;

    @Before
    public void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JFrame parentFrame = new JFrame(); // ventana padre ficticia
            ventana = new ReporteUsuario(parentFrame);

            // Encontrar la ventana creada
            for (Window w : Window.getWindows()) {
                if (w instanceof JFrame && w.isVisible() && ((JFrame) w).getTitle().equals("Reporte de Incidente")) {
                    frameInterno = (JFrame) w;
                    break;
                }
            }

            assertNotNull("No se encontró la ventana de reporte", frameInterno);

            choferButton = findButtonByText(frameInterno, "Chofer");
            pasajeroButton = findButtonByText(frameInterno, "Pasajero");
            continuarButton = findButtonByText(frameInterno, "Continuar");
            rutaField = findComponent(frameInterno, JTextField.class);
        });
    }

    @Test
    public void testComponentesExisten() {
        assertNotNull("Botón Chofer no encontrado", choferButton);
        assertNotNull("Botón Pasajero no encontrado", pasajeroButton);
        assertNotNull("Botón Continuar no encontrado", continuarButton);
        assertNotNull("Campo de ruta no encontrado", rutaField);
    }

    @Test
    public void testContinuarSinDatos() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            rutaField.setText("");
            continuarButton.doClick();
        });
        assertTrue("La ventana no debe cerrarse si faltan datos", frameInterno.isVisible());
    }

    @Test
    public void testContinuarConDatos() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            choferButton.doClick(); // selecciona tipo usuario
            rutaField.setText("Ruta 3");
            continuarButton.doClick();
        });

        assertFalse("La ventana debe cerrarse si se ingresaron todos los datos", frameInterno.isVisible());

        // Comprobamos que se haya abierto una nueva ventana llamada "REPORTE"
        boolean seAbrioSegundaVentana = false;
        for (Window w : Window.getWindows()) {
            if (w instanceof JFrame && ((JFrame) w).getTitle().equals("REPORTE") && w.isVisible()) {
                seAbrioSegundaVentana = true;
                break;
            }
        }
        assertTrue("Segunda ventana no se abrió tras continuar", seAbrioSegundaVentana);
    }

    // Métodos auxiliares

    private JButton findButtonByText(Container container, String text) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JButton && ((JButton) comp).getText().trim().equals(text)) {
                return (JButton) comp;
            }
            if (comp instanceof Container) {
                JButton button = findButtonByText((Container) comp, text);
                if (button != null) return button;
            }
        }
        return null;
    }

    private <T extends Component> T findComponent(Container container, Class<T> type) {
        for (Component comp : container.getComponents()) {
            if (type.isInstance(comp)) return type.cast(comp);
            if (comp instanceof Container) {
                T found = findComponent((Container) comp, type);
                if (found != null) return found;
            }
        }
        return null;
    }
}
