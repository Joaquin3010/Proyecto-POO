package SistemadeRegistro.RegistrodeChofer;

import static org.junit.Assert.*;
import javax.swing.*;
import java.awt.*;
import org.junit.Test;
import java.lang.reflect.InvocationTargetException;

public class ChoferLogRegTest {

    @Test
    public void testInterfazInicial() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            ChoferLogReg frame = new ChoferLogReg();
            
            try {
                // 1. Verificar configuración básica del frame
                assertEquals("Registro / Login de Chofer - Transporte UDLAP", frame.getTitle());
                assertEquals(400, frame.getWidth());
                assertEquals(200, frame.getHeight());
                
                // 2. Obtener el panel principal
                Component[] components = frame.getContentPane().getComponents();
                assertEquals(1, components.length);
                assertTrue(components[0] instanceof JPanel);
                
                JPanel mainPanel = (JPanel) components[0];
                
                // 3. Verificar componentes internos
                Component[] panelComponents = mainPanel.getComponents();
                assertEquals(3, panelComponents.length); // Título + 2 botones
                
                // Verificar etiqueta de título
                assertTrue(panelComponents[0] instanceof JLabel);
                JLabel titulo = (JLabel) panelComponents[0];
                assertEquals("¿Qué deseas hacer?", titulo.getText());
                assertEquals(Font.BOLD, titulo.getFont().getStyle());
                assertEquals(16, titulo.getFont().getSize());
                
                // Verificar botones
                assertButton((JButton) panelComponents[1], "Registrar Nuevo Chofer", new Color(46, 204, 113)); // Verde
                assertButton((JButton) panelComponents[2], "Iniciar Sesion Chofer", new Color(243, 156, 18)); // Naranja
                
            } finally {
                frame.dispose();
            }
        });
    }

    @Test
    public void testBotonRegistro() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            ChoferLogReg frame = new ChoferLogReg();
            
            try {
                JPanel mainPanel = (JPanel) frame.getContentPane().getComponent(0);
                JButton btnRegistrar = (JButton) mainPanel.getComponent(1); // Segundo componente
                
                // Simular clic
                btnRegistrar.doClick();
                
                // Verificar que la ventana se cerró
                assertFalse(frame.isVisible());
                
            } finally {
                frame.dispose();
            }
        });
    }

    @Test
    public void testBotonLogin() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            ChoferLogReg frame = new ChoferLogReg();
            
            try {
                JPanel mainPanel = (JPanel) frame.getContentPane().getComponent(0);
                JButton btnLogin = (JButton) mainPanel.getComponent(2); // Tercer componente
                
                // Simular clic
                btnLogin.doClick();
                
                // Verificar que la ventana se cerró
                assertFalse(frame.isVisible());
                
            } finally {
                frame.dispose();
            }
        });
    }

    // Método auxiliar para verificar botones
    private void assertButton(JButton button, String expectedText, Color expectedBgColor) {
        assertNotNull(button);
        assertEquals(expectedText, button.getText());
        assertEquals(expectedBgColor, button.getBackground());
        assertEquals(Color.WHITE, button.getForeground());
        assertEquals("Arial", button.getFont().getName());
        assertEquals(Font.PLAIN, button.getFont().getStyle());
        assertEquals(14, button.getFont().getSize());
    }
}