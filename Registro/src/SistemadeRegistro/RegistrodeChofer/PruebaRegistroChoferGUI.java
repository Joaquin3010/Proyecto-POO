package SistemadeRegistro.RegistrodeChofer;

import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.Component;
import javax.swing.*;

public class PruebaRegistroChoferGUI {

    @Test
    public void pruebaEstructuraInterfaz() {
        RegistroChoferGUI gui = new RegistroChoferGUI();
        
        try {
            // Verificar ventana
            assertEquals("Título incorrecto", 
                        "Registro de Nuevo Chofer", 
                        gui.getTitle());
            
            // Verificar componentes
            Component panel = gui.getContentPane().getComponent(0);
            Component[] componentes = ((java.awt.Container)panel).getComponents();
            
            // Verificar campos de texto
            verificarCampo(componentes[1], "Nombre Completo:");
            verificarCampo(componentes[3], "Correo:");
            verificarCampo(componentes[5], "Telefono:");
            verificarCampo(componentes[7], "CURP:");
            
            // Verificar botón
            assertTrue("Falta botón Registrar", 
                     componentes[9] instanceof JButton);
            assertEquals("Texto incorrecto en botón",
                       "Registrar",
                       ((JButton)componentes[9]).getText());
            
        } finally {
            gui.dispose();
        }
    }

    private void verificarCampo(Component componente, String etiquetaEsperada) {
        assertTrue("Falta campo para " + etiquetaEsperada, 
                 componente instanceof JTextField);
    }

    @Test
    public void pruebaRegistroInvalido() {
        RegistroChoferGUI gui = new RegistroChoferGUI();
        
        try {
            JButton botonRegistrar = obtenerBotonRegistrar(gui);
            assertNotNull("No se encontró el botón Registrar", botonRegistrar);
            
            // Intentar registro con campos vacíos
            botonRegistrar.doClick();
            assertTrue("La ventana debe permanecer abierta con datos inválidos", 
                      gui.isVisible());
            
        } finally {
            gui.dispose();
        }
    }

    private JButton obtenerBotonRegistrar(RegistroChoferGUI gui) {
        Component panel = gui.getContentPane().getComponent(0);
        for (Component comp : ((java.awt.Container)panel).getComponents()) {
            if (comp instanceof JButton && ((JButton)comp).getText().equals("Registrar")) {
                return (JButton)comp;
            }
        }
        return null;
    }
}