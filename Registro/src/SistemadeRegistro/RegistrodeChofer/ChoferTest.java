package SistemadeRegistro.RegistrodeChofer;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ChoferTest {
    
    private Chofer chofer;
    private final String NOMBRE_VALIDO = "Juan Pérez";
    private final String CORREO_VALIDO = "juan@udlap.mx";
    private final int TELEFONO_VALIDO = 1234567890;
    private final String CURP_VALIDO = "PERJ920101HDFLRN01";
    
    @Before
    public void setUp() {
        chofer = new Chofer(NOMBRE_VALIDO, CORREO_VALIDO, TELEFONO_VALIDO, CURP_VALIDO);
    }
    
    // Pruebas de creación básica (se mantienen igual)
    @Test
    public void testCreacionChoferValido() {
        assertEquals(NOMBRE_VALIDO, chofer.getNombre());
        assertEquals(CORREO_VALIDO, chofer.getCorreo());
        assertEquals(TELEFONO_VALIDO, chofer.getTelefono());
        assertEquals(CURP_VALIDO, chofer.getCurp());
        assertEquals("Pendiente", chofer.getEstado());
    }
    
    // Pruebas de setters (se mantienen igual)
    @Test
    public void testSettersValidos() {
        chofer.setNombre("María García");
        chofer.setCorreo("maria@udlap.mx");
        chofer.setTelefono(987654321);
        chofer.setCurp("GAMM950505MDFRRR02");
        chofer.setEstado("Aprobado");
        
        assertEquals("María García", chofer.getNombre());
        assertEquals("maria@udlap.mx", chofer.getCorreo());
        assertEquals(987654321, chofer.getTelefono());
        assertEquals("GAMM950505MDFRRR02", chofer.getCurp());
        assertEquals("Aprobado", chofer.getEstado());
    }
    
    // Pruebas de teléfono (si no hay validación, se cambian)
    @Test
    public void testTelefonoNegativo() {
        // Si Chofer no valida, debería aceptar negativos
        Chofer choferNegativo = new Chofer(NOMBRE_VALIDO, CORREO_VALIDO, -1234567890, CURP_VALIDO);
        assertEquals(-1234567890, choferNegativo.getTelefono());
    }
    
    @Test
    public void testTelefonoCero() {
        Chofer choferCero = new Chofer(NOMBRE_VALIDO, CORREO_VALIDO, 0, CURP_VALIDO);
        assertEquals(0, choferCero.getTelefono());
    }
    
    // Pruebas de nombre (si no hay validación, se cambian)
    @Test
    public void testNombreNull() {
        Chofer choferNull = new Chofer(null, CORREO_VALIDO, TELEFONO_VALIDO, CURP_VALIDO);
        assertNull(choferNull.getNombre());
    }
    
    @Test
    public void testNombreVacio() {
        Chofer choferVacio = new Chofer("", CORREO_VALIDO, TELEFONO_VALIDO, CURP_VALIDO);
        assertEquals("", choferVacio.getNombre());
    }
    
    @Test
    public void testNombreSoloEspacios() {
        Chofer choferEspacios = new Chofer("   ", CORREO_VALIDO, TELEFONO_VALIDO, CURP_VALIDO);
        assertEquals("   ", choferEspacios.getNombre());
    }
    
    // Pruebas de CURP (si no hay validación, se cambian)
    @Test
    public void testCurpNull() {
        Chofer choferCurpNull = new Chofer(NOMBRE_VALIDO, CORREO_VALIDO, TELEFONO_VALIDO, null);
        assertNull(choferCurpNull.getCurp());
    }
    
    @Test
    public void testCurpVacio() {
        Chofer choferCurpVacio = new Chofer(NOMBRE_VALIDO, CORREO_VALIDO, TELEFONO_VALIDO, "");
        assertEquals("", choferCurpVacio.getCurp());
    }
    
    @Test
    public void testCurpCorto() {
        Chofer choferCurpCorto = new Chofer(NOMBRE_VALIDO, CORREO_VALIDO, TELEFONO_VALIDO, "ABC123");
        assertEquals("ABC123", choferCurpCorto.getCurp());
    }
    
    // Pruebas de estado (se mantienen igual)
    @Test
    public void testEstadosValidos() {
        chofer.setEstado("Pendiente");
        assertEquals("Pendiente", chofer.getEstado());
        
        chofer.setEstado("Aprobado");
        assertEquals("Aprobado", chofer.getEstado());
        
        chofer.setEstado("Rechazado");
        assertEquals("Rechazado", chofer.getEstado());
    }
    
    @Test
    public void testEstadoCaseInsensitive() {
        chofer.setEstado("APROBADO");
        assertEquals("APROBADO", chofer.getEstado());
        
        chofer.setEstado("pendiente");
        assertEquals("pendiente", chofer.getEstado());
    }
    
    // Prueba de correo (se mantiene igual)
    @Test
    public void testCorreoNull() {
        Chofer choferSinCorreo = new Chofer(NOMBRE_VALIDO, null, TELEFONO_VALIDO, CURP_VALIDO);
        assertNull(choferSinCorreo.getCorreo());
    }
    
    @Test
    public void testCorreoVacio() {
        Chofer choferCorreoVacio = new Chofer(NOMBRE_VALIDO, "", TELEFONO_VALIDO, CURP_VALIDO);
        assertEquals("", choferCorreoVacio.getCorreo());
    }
}