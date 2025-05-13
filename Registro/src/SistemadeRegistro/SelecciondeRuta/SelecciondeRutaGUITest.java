package SistemadeRegistro.SelecciondeRuta;

import static org.junit.Assert.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.junit.Test;
import java.lang.reflect.InvocationTargetException;

public class SelecciondeRutaGUITest {

    @Test
    public void testCreacionVentana() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            SelecciondeRutaGUI frame = new SelecciondeRutaGUI(new JFrame());
            
            // Verificar configuración básica de la ventana
            assertEquals("Sistema de Registro - Transporte UDLAP", frame.getTitle());
            assertEquals(500, frame.getWidth());
            assertEquals(200, frame.getHeight());
            assertTrue(frame.isVisible());
            
            // Verificar componentes
            Component[] components = frame.getContentPane().getComponents();
            assertEquals(4, components.length);
            
            // Verificar etiquetas
            assertTrue(components[0] instanceof JLabel);
            assertEquals("Seleccione si desea crear o unirse a una ruta", 
                      ((JLabel)components[0]).getText());
            
            // Verificar botones
            assertTrue(components[2] instanceof JButton);
            assertEquals("Crear", ((JButton)components[2]).getText());
            
            assertTrue(components[3] instanceof JButton);
            assertEquals("Unirse", ((JButton)components[3]).getText());
            
            frame.dispose();
        });
    }

    @Test
    public void testBotonCrear() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            SelecciondeRutaGUI frame = new SelecciondeRutaGUI(new JFrame());
            
            // Encontrar y hacer clic en el botón Crear
            for (Component c : frame.getContentPane().getComponents()) {
                if (c instanceof JButton && ((JButton)c).getText().equals("Crear")) {
                    ((JButton)c).doClick();
                    break;
                }
            }
            
            // Verificar que la ventana actual se ocultó
            assertFalse(frame.isVisible());
            
            // Verificar que se abrió ControlFrameRuta
            boolean found = false;
            for (Window w : Window.getWindows()) {
                if (w.isVisible() && w instanceof JFrame && 
                    "Registro de Rutas".equals(((JFrame)w).getTitle())) {
                    found = true;
                    w.dispose();
                }
            }
            assertTrue("Debería haberse abierto ControlFrameRuta", found);
            
            frame.dispose();
        });
    }

    @Test
    public void testBotonUnirse() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            SelecciondeRutaGUI frame = new SelecciondeRutaGUI(new JFrame());
            
            // Encontrar y hacer clic en el botón Unirse
            for (Component c : frame.getContentPane().getComponents()) {
                if (c instanceof JButton && ((JButton)c).getText().equals("Unirse")) {
                    ((JButton)c).doClick();
                    break;
                }
            }
            
            // Verificar que la ventana actual se ocultó
            assertFalse(frame.isVisible());
            
            // Verificar que se abrió UnirseAUnaRuta
            boolean found = false;
            for (Window w : Window.getWindows()) {
                if (w.isVisible() && w instanceof JFrame) {
                    found = true;
                    w.dispose();
                }
            }
            assertTrue("Debería haberse abierto UnirseAUnaRuta", found);
            
            frame.dispose();
        });
    }
     }