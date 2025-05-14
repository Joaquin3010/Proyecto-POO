package Reporte;

import static org.junit.Assert.*;

import java.awt.*;
import javax.swing.*;

import org.junit.Before;
import org.junit.Test;

public class SegundaVentanaTest {

    private JFrame parentFrame;
    private SegundaVentana ventana;
    private JFrame innerFrame;
    private JTextArea textArea;
    private JButton enviarButton;

    @Before
    public void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            parentFrame = new JFrame(); // no visible
            ventana = new SegundaVentana(parentFrame);

            // Obtener la instancia del JFrame interno
            for (Window w : Window.getWindows()) {
                if (w instanceof JFrame && w.isVisible() && ((JFrame) w).getTitle().equals("REPORTE")) {
                    innerFrame = (JFrame) w;
                    break;
                }
            }

            assertNotNull("No se encontró la ventana secundaria", innerFrame);

            // Buscar componentes dentro del innerFrame
            textArea = findComponent(innerFrame, JTextArea.class);
            enviarButton = findButtonByText(innerFrame, "Enviar Reporte");
        });
    }

    @Test
    public void testComponentesExisten() {
        assertNotNull("Área de texto no encontrada", textArea);
        assertNotNull("Botón de enviar no encontrado", enviarButton);
    }

    @Test
    public void testEnviarSinTexto() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            textArea.setText("");
            enviarButton.doClick();
        });

        // No se puede verificar JOptionPane directamente sin mocking, pero el frame no debe cerrarse
        assertTrue("La ventana no debe cerrarse si la queja está vacía", innerFrame.isVisible());
    }

    @Test
    public void testEnviarConTexto() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            textArea.setText("Esta es una queja de prueba.");
            enviarButton.doClick();
        });

        // Esperamos que la ventana se cierre tras enviar el mensaje
        assertFalse("La ventana debe cerrarse si la queja fue enviada", innerFrame.isVisible());
    }

    // Métodos auxiliares

    private JButton findButtonByText(Container container, String text) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JButton) {
                if (((JButton) comp).getText().trim().equals(text)) {
                    return (JButton) comp;
                }
            }
            if (comp instanceof Container) {
                JButton btn = findButtonByText((Container) comp, text);
                if (btn != null) return btn;
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
