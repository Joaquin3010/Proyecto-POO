package SistemadeRegistro.SelecciondeUsuario;

import static org.junit.Assert.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.junit.*;

public class SelecciondeUsuarioGUITest {
    
    private SelecciondeUsuarioGUI gui;
    
    @Before
    public void setUp() {
        gui = new SelecciondeUsuarioGUI();
    }
    
    @After
    public void tearDown() {
        gui.dispose();
    }
    
    @Test
    public void testComponentesExisten() {
        Component[] components = gui.getContentPane().getComponents();
        boolean hasDisplayLabel = false;
        boolean hasChoferButton = false;
        boolean hasEstudianteButton = false;
        
        for (Component comp : components) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                if (label.getText().contains("Seleccione si es Chofer o Estudiante")) {
                    hasDisplayLabel = true;
                }
            } else if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                if (button.getText().equals("Chofer")) {
                    hasChoferButton = true;
                } else if (button.getText().equals("Estudiante")) {
                    hasEstudianteButton = true;
                }
            }
        }
        
        assertTrue("Falta el JLabel de display", hasDisplayLabel);
        assertTrue("Falta el botón Chofer", hasChoferButton);
        assertTrue("Falta el botón Estudiante", hasEstudianteButton);
    }
    
    @Test
    public void testTituloVentana() {
        assertEquals("El título de la ventana no coincide", 
                    "Sistema de Registro - Transporte UDLAP", 
                    gui.getTitle());
    }
    
    @Test
    public void testLayout() {
        LayoutManager layout = gui.getContentPane().getLayout();
        assertTrue("El layout del content pane no es GridLayout", 
                  layout instanceof GridLayout);
        
        if (layout instanceof GridLayout) {
            GridLayout grid = (GridLayout) layout;
            assertEquals("Número de filas incorrecto", 2, grid.getRows());
            assertEquals("Número de columnas incorrecto", 2, grid.getColumns());
            assertEquals("Espaciado horizontal incorrecto", 5, grid.getHgap());
            assertEquals("Espaciado vertical incorrecto", 5, grid.getVgap());
        }
    }
    
    @Test
    public void testBotonesPropiedades() {
        Component[] components = gui.getContentPane().getComponents();
        JButton choferButton = null;
        JButton estudianteButton = null;
        
        for (Component comp : components) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                if (button.getText().equals("Chofer")) {
                    choferButton = button;
                } else if (button.getText().equals("Estudiante")) {
                    estudianteButton = button;
                }
            }
        }
        
        assertNotNull("No se encontró el botón Chofer", choferButton);
        assertNotNull("No se encontró el botón Estudiante", estudianteButton);
        
        assertEquals("Color de fondo incorrecto para Chofer", 
                   new Color(46, 204, 113), 
                   choferButton.getBackground());
        assertEquals("Color de texto incorrecto para Chofer", 
                   Color.WHITE, 
                   choferButton.getForeground());
        assertEquals("Fuente incorrecta para Chofer", 
                   new Font("Arial", Font.PLAIN, 14), 
                   choferButton.getFont());
        
        assertEquals("Color de fondo incorrecto para Estudiante", 
                   new Color(243, 156, 18), 
                   estudianteButton.getBackground());
        assertEquals("Color de texto incorrecto para Estudiante", 
                   Color.WHITE, 
                   estudianteButton.getForeground());
        assertEquals("Fuente incorrecta para Estudiante", 
                   new Font("Arial", Font.PLAIN, 14), 
                   estudianteButton.getFont());
    }
    
    @Test
    public void testActionListeners() {
        Component[] components = gui.getContentPane().getComponents();
        JButton choferButton = null;
        JButton estudianteButton = null;
        
        for (Component comp : components) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                if (button.getText().equals("Chofer")) {
                    choferButton = button;
                } else if (button.getText().equals("Estudiante")) {
                    estudianteButton = button;
                }
            }
        }
        
        assertNotNull("No se encontró el botón Chofer", choferButton);
        assertNotNull("No se encontró el botón Estudiante", estudianteButton);
        
        assertTrue("El botón Chofer no tiene ActionListener", 
                 choferButton.getActionListeners().length > 0);
        assertTrue("El botón Estudiante no tiene ActionListener", 
                 estudianteButton.getActionListeners().length > 0);
    }
    
    @Test
    public void testVentanaVisible() {
        assertTrue("La ventana debería estar visible", gui.isVisible());
    }
    
    @Test
    public void testCierreVentana() {
        assertEquals("La operación de cierre no es EXIT_ON_CLOSE", 
                   JFrame.EXIT_ON_CLOSE, 
                   gui.getDefaultCloseOperation());
    }
    
    
    @Test
    public void testTamañoVentana() {
        Dimension size = gui.getSize();
        assertEquals("Ancho incorrecto de la ventana", 500, size.width);
        assertEquals("Alto incorrecto de la ventana", 170, size.height);
    }
}