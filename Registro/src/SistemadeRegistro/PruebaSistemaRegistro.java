package SistemadeRegistro;

import org.junit.Test;
import static org.junit.Assert.*;
import SistemadeRegistro.SelecciondeUsuario.SelecciondeUsuarioGUI;

public class PruebaSistemaRegistro {

    @Test
    public void pruebaInicioAplicacion() {
        // Verifica que el método main se ejecute sin errores
        try {
            SistemadeRegistro.main(new String[]{});
            assertTrue("La aplicación debe iniciar correctamente", true);
        } catch (Exception e) {
            fail("El método main generó una excepción: " + e.getMessage());
        }
    }
    
    @Test
    public void pruebaCreacionVentanaPrincipal() {
        try {
            SelecciondeUsuarioGUI ventana = new SelecciondeUsuarioGUI();
            assertNotNull("La ventana no debe ser nula", ventana);
            assertEquals("El título debe coincidir", 
                        "Sistema de Registro - Transporte UDLAP", 
                        ventana.getTitle());
            ventana.dispose();
        } catch (Exception e) {
            fail("Error al crear ventana principal: " + e.getMessage());
        }
    }
}