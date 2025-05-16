package SistemadeRegistro.CostodeViaje;

import org.junit.*;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class CalcPrecioGUITest {
    private CalcPrecioGUI gui;

    @Before
    public void setUp() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> {
            gui = new CalcPrecioGUI(null);
        });
    }

    @Test
    public void testCalculoConDatosValidos() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> {
            JTextField distanciaField = null;
            JTextField rendimientoField = null;
            JButton calcularButton = null;
            JLabel resultadoLabel = null;

            // Buscar los componentes necesarios
            for (Component comp : gui.getContentPane().getComponents()) {
                if (comp instanceof JTextField) {
                    if (distanciaField == null) {
                        distanciaField = (JTextField) comp;
                    } else {
                        rendimientoField = (JTextField) comp;
                    }
                } else if (comp instanceof JButton) {
                    calcularButton = (JButton) comp;
                } else if (comp instanceof JLabel) {
                    JLabel lbl = (JLabel) comp;
                    if (lbl.getText().toLowerCase().contains("precio") || lbl.getText().contains("$")) {
                        resultadoLabel = lbl;
                    }
                }
            }

            // Ingresar datos válidos
            assertNotNull("Campo distancia no encontrado", distanciaField);
            assertNotNull("Campo rendimiento no encontrado", rendimientoField);
            distanciaField.setText("100");
            rendimientoField.setText("10");

            // Simular clic
            assertNotNull("Botón calcular no encontrado", calcularButton);
            calcularButton.doClick();

            // Verificar que el resultado sea correcto
            assertNotNull("Etiqueta de resultado no encontrada", resultadoLabel);
            assertTrue("El resultado debe contener '100'", resultadoLabel.getText().contains("100"));
        });
    }

    @After
    public void tearDown() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> gui.dispose());
    }
}
