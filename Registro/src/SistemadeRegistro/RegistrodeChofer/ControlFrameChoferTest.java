package SistemadeRegistro.RegistrodeChofer;

import static org.junit.Assert.*;
import javax.swing.*;
import java.awt.*;
import org.junit.Test;
import java.lang.reflect.InvocationTargetException;

public class ControlFrameChoferTest {

    @Test
    public void testInterfazControlChofer() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            Chofer chofer = new Chofer("Juan Perez", "juan@udlap.mx", 1234567890, "TEST123456ABCDEF12");
            chofer.setEstado("Aprobado");
            
            ControlFrameChofer frame = new ControlFrameChofer(chofer);
            
            try {
                // 1. Verificar configuración básica del frame
                assertEquals("Panel de Chofer - Transporte UDLAP", frame.getTitle());
                assertEquals(400, frame.getWidth());
                assertEquals(300, frame.getHeight());
                
                // 2. Obtener el panel principal
                Component[] components = frame.getContentPane().getComponents();
                assertEquals(1, components.length); // Debe tener solo un panel
                assertTrue(components[0] instanceof JPanel);
                
                JPanel mainPanel = (JPanel) components[0];
                
                // 3. Verificar los botones
                Component[] buttons = mainPanel.getComponents();
                assertEquals(5, buttons.length); // 5 botones esperados
                
                // Verificar cada botón
                assertButton((JButton) buttons[0], "Registrar Vehículo");
                assertButton((JButton) buttons[1], "Crear Ruta Nueva");
                assertButton((JButton) buttons[2], "Unirse a Ruta Existente");
                assertButton((JButton) buttons[3], "Eliminar Ruta");
                assertButton((JButton) buttons[4], "Cerrar Sesión");
                
            } finally {
                frame.dispose(); // Asegurar limpieza
            }
        });
    }

    @Test
    public void testChoferNoAutorizado() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            Chofer chofer = new Chofer("Maria Garcia", "maria@udlap.mx", 987654321, "TEST987654XYZWVU98");
            chofer.setEstado("Pendiente"); // Estado pendiente no debería permitir acciones
            
            ControlFrameChofer frame = new ControlFrameChofer(chofer);
            
            try {
                JPanel mainPanel = (JPanel) frame.getContentPane().getComponent(0);
                JButton firstButton = (JButton) mainPanel.getComponent(0); // Botón Registrar Vehículo
                
                // Simular click
                firstButton.doClick();
                
                // Verificar que el frame sigue visible (no se cerró)
                assertTrue(frame.isVisible());
                
                // Verificar que muestra mensaje de no autorizado
                // (Esto requeriría mock de JOptionPane para verificar completamente)
                
            } finally {
                frame.dispose();
            }
        });
    }

    // Método auxiliar para verificar botones
    private void assertButton(JButton button, String expectedText) {
        assertNotNull(button);
        assertEquals(expectedText, button.getText());
        assertEquals(new Color(243, 156, 18), button.getBackground()); // Color naranja
        assertEquals(Color.WHITE, button.getForeground());
        assertEquals("Arial", button.getFont().getName());
        assertEquals(Font.PLAIN, button.getFont().getStyle());
        assertEquals(14, button.getFont().getSize());
    }
}