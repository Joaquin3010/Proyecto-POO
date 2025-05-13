package SistemadeRegistro.Rutas;
import javax.swing.*;
import java.awt.Window; // Para la clase Window
import org.junit.Test;
import static org.junit.Assert.*;
import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class PruebaControlFrameRuta { // Corregido el nombre de la clase

    @Test
    public void pruebaCreacionVentana() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            ControlFrameRuta frame = new ControlFrameRuta(new JFrame());
            
            // Obtener todos los JFrame visibles
            for (Window window : Window.getWindows()) {
                if (window.isVisible() && window instanceof JFrame) {
                    assertEquals("Registro de Rutas", ((JFrame)window).getTitle());
                    window.dispose();
                    return;
                }
            }
            fail("No se encontró el JFrame esperado");
        });
    }
}