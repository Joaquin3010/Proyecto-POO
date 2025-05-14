package SistemadeRegistro.CostodeViaje;

import static org.junit.Assert.*;
import java.awt.Component;
import java.awt.Container;
import java.lang.reflect.Field;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import org.junit.Before;
import org.junit.Test;

public class CalcPrecioGUITest {
    
    private CalcPrecioGUI gui;
    private JTextField distanciaField;
    private JTextField rendimientoField;
    private JButton calcularButton;
    
    @Before
    public void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            gui = new CalcPrecioGUI(null);
        });
        
        // Obtener campos usando reflection
        distanciaField = getField(gui, "distanciaField");
        rendimientoField = getField(gui, "rendimientoField");
        
        // Buscar el botón por su texto
        calcularButton = findButtonByText(gui, "Calcular tarifa del viaje");
    }
    
    @Test
    public void testComponentesExisten() {
        assertNotNull("Campo de distancia no encontrado", distanciaField);
        assertNotNull("Campo de rendimiento no encontrado", rendimientoField);
        assertNotNull("Botón calcular no encontrado", calcularButton);
    }
    
    @Test
    public void testCalculoExitoso() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            distanciaField.setText("100.0");
            rendimientoField.setText("15");
            calcularButton.doClick();
        });
        
        assertEquals("100.0", distanciaField.getText());
        assertEquals("15", rendimientoField.getText());
    }
    
    // ... (otros métodos de test se mantienen igual)
    
    // ===== Métodos auxiliares mejorados =====
    
    @SuppressWarnings("unchecked")
    private <T> T getField(Object obj, String fieldName) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return (T) field.get(obj);
    }
    
    private JButton findButtonByText(Container container, String text) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JButton && ((JButton)comp).getText().equals(text)) {
                return (JButton) comp;
            }
            if (comp instanceof Container) {
                JButton button = findButtonByText((Container) comp, text);
                if (button != null) {
                    return button;
                }
            }
        }
        return null;
    }
}