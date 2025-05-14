package SistemadeRegistro.Parada;

import org.junit.Test;
import static org.junit.Assert.*;

public class RegistroParadaTest {

    // Simula el comportamiento esperado si se llenan todos los campos
    @Test
    public void testCamposCompletos() {
        String distancia = "10";
        String cantidad = "3";
        String ubicacion = "Puerta A";

        boolean resultado = !distancia.isEmpty() && !cantidad.isEmpty() && !ubicacion.isEmpty();
        assertTrue(resultado);
    }

    // Simula campos vacíos
    @Test
    public void testDistanciaVacia() {
        String distancia = "";
        String cantidad = "3";
        String ubicacion = "Puerta A";

        boolean resultado = !distancia.isEmpty() && !cantidad.isEmpty() && !ubicacion.isEmpty();
        assertFalse(resultado);
    }

    @Test
    public void testCantidadVacia() {
        String distancia = "10";
        String cantidad = "";
        String ubicacion = "Puerta A";

        boolean resultado = !distancia.isEmpty() && !cantidad.isEmpty() && !ubicacion.isEmpty();
        assertFalse(resultado);
    }

    @Test
    public void testUbicacionVacia() {
        String distancia = "10";
        String cantidad = "3";
        String ubicacion = "";

        boolean resultado = !distancia.isEmpty() && !cantidad.isEmpty() && !ubicacion.isEmpty();
        assertFalse(resultado);
    }
}
