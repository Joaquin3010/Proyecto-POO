import static org.junit.Assert.*;
import java.awt.*;
import javax.swing.*;
import org.junit.Before;
import org.junit.Test;

public class SeleccionDeAccionTest {

    private SeleccionDeAccion frame;
    private JButton reporteButton;
    private JButton calificacionButton;
    private JLabel displayLabel;

    @Before
    public void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            frame = new SeleccionDeAccion();

            // Buscar los componentes dentro del hilo EDT
            displayLabel = findComponent(frame, JLabel.class);
            reporteButton = findButtonByText(frame, "Reporte");
            calificacionButton = findButtonByText(frame, "Calificación");
        });
    }

    @Test
    public void testComponentesExisten() {
        assertNotNull("Frame no creado correctamente", frame);
        assertNotNull("Etiqueta display no encontrada", displayLabel);
        assertNotNull("Botón de Reporte no encontrado", reporteButton);
        assertNotNull("Botón de Calificación no encontrado", calificacionButton);
    }

    @Test
    public void testTextoInicial() {
        assertEquals("Texto inicial incorrecto",
                     "Seleccione la accion deseada:",
                     displayLabel.getText());
    }

    @Test
    public void testAccionBotonReporte() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            reporteButton.doClick();
        });
        assertFalse("El frame principal debería estar oculto", frame.isVisible());
    }

    @Test
    public void testAccionBotonCalificacion() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            calificacionButton.doClick();
        });
        assertFalse("El frame principal debería estar oculto", frame.isVisible());
    }

    // Métodos auxiliares

    private JButton findButtonByText(Container container, String text) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JButton) {
                String buttonText = ((JButton) comp).getText().trim(); // trim para evitar errores por espacios
                if (buttonText.equals(text)) {
                    return (JButton) comp;
                }
            }
            if (comp instanceof Container) {
                JButton button = findButtonByText((Container) comp, text);
                if (button != null) {
                    return button;
                }
            }
        }
        return null; // No lanza excepción aquí
    }

    private <T extends Component> T findComponent(Container container, Class<T> componentType) {
        for (Component comp : container.getComponents()) {
            if (componentType.isInstance(comp)) {
                return componentType.cast(comp);
            }
            if (comp instanceof Container) {
                T found = findComponent((Container) comp, componentType);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }
}
