package SistemadeRegistro.SelecciondeUsuario;

import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JLabel;

public class PruebaSeleccionUsuarioGUI {

    @Test
    public void pruebaComponentesInterfaz() {
        SelecciondeUsuarioGUI gui = new SelecciondeUsuarioGUI();
        
        try {
            // Verificar propiedades de la ventana
            assertEquals("Título incorrecto", 
                        "Sistema de Registro - Transporte UDLAP", 
                        gui.getTitle());
            
            // Verificar componentes
            Component[] componentes = gui.getContentPane().getComponents();
            assertEquals("Número incorrecto de componentes", 4, componentes.length);
            
            // Verificar etiquetas
            assertTrue("Falta etiqueta principal", 
                     componentes[0] instanceof JLabel);
            assertEquals("Texto incorrecto en etiqueta",
                       "Seleccione si es Chofer o Estudiante",
                       ((JLabel)componentes[0]).getText());
            
            // Verificar botones
            assertTrue("Falta botón Chofer", 
                     componentes[2] instanceof JButton);
            assertTrue("Falta botón Estudiante", 
                     componentes[3] instanceof JButton);
            
        } finally {
            gui.dispose();
        }
    }

    @Test
    public void pruebaAccionBotonChofer() {
        SelecciondeUsuarioGUI gui = new SelecciondeUsuarioGUI();
        
        try {
            JButton botonChofer = obtenerBoton(gui, "Chofer");
            assertNotNull("No se encontró el botón Chofer", botonChofer);
            
            botonChofer.doClick();
            assertFalse("La ventana debe ocultarse al hacer clic", gui.isVisible());
            
        } finally {
            gui.dispose();
        }
    }

    private JButton obtenerBoton(SelecciondeUsuarioGUI gui, String texto) {
        for (Component comp : gui.getContentPane().getComponents()) {
            if (comp instanceof JButton && ((JButton)comp).getText().equals(texto)) {
                return (JButton)comp;
            }
        }
        return null;
    }
}