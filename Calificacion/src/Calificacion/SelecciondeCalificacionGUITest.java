package Calificacion;

import static org.junit.Assert.*;

import java.awt.*;
import javax.swing.*;

import org.junit.Before;
import org.junit.Test;

public class SelecciondeCalificacionGUITest {

    private SelecciondeCalificacionGUI ventana;
    private JFrame frameInterno;
    private JButton choferButton;
    private JButton estudianteButton;
    private JLabel displayLabel;

    @Before
    public void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JFrame parent = new JFrame();  // Ventana ficticia como padre
            ventana = new SelecciondeCalificacionGUI(parent);

            for (Window w : Window.getWindows()) {
                if (w instanceof JFrame && w.isVisible() && ((JFrame) w).getTitle().equals("Sistema de Calificación - Transporte UDLAP")) {
                    frameInterno = (JFrame) w;
                    break;
                }
            }

            assertNotNull("No se encontró la ventana", frameInterno);

            choferButton = findButtonByText(frameInterno, "Chofer");
            estudianteButton = findButtonByText(frameInterno, "Estudiantes");
            displayLabel = findComponent(frameInterno, JLabel.class);
        });
    }

    @Test
    public void testComponentesExisten() {
        assertNotNull("Botón Chofer no encontrado", choferButton);
        assertNotNull("Botón Estudiantes no encontrado", estudianteButton);
        assertNotNull("Etiqueta display no encontrada", displayLabel);
    }

    @Test
    public void testTextoInicial() {
        assertEquals("Texto incorrecto en la etiqueta",
                     "Seleccione a quien le quiere dar una calificación:",
                     displayLabel.getText());
    }

    @Test
    public void testClickChoferOcultaVentana() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            choferButton.doClick();
        });

        assertFalse("La ventana debe estar oculta después de hacer clic en Chofer", frameInterno.isVisible());
    }

    @Test
    public void testClickEstudiantesOcultaVentana() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            estudianteButton.doClick();
        });

        assertFalse("La ventana debe estar oculta después de hacer clic en Estudiantes", frameInterno.isVisible());
    }

    // Métodos auxiliares reutilizables

    private JButton findButtonByText(Container container, String text) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JButton && ((JButton) comp).getText().trim().equals(text)) {
                return (JButton) comp;
            }
            if (comp instanceof Container) {
                JButton btn = findButtonByText((Container) comp, text);
                if (btn != null) return btn;
            }
        }
        return null;
    }

    private <T extends Component> T findComponent(Container container, Class<T> clazz) {
        for (Component comp : container.getComponents()) {
            if (clazz.isInstance(comp)) return clazz.cast(comp);
            if (comp instanceof Container) {
                T found = findComponent((Container) comp, clazz);
                if (found != null) return found;
            }
        }
        return null;
    }
}
