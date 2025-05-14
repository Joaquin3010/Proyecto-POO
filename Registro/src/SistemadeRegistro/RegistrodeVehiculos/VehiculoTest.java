package SistemadeRegistro.RegistrodeVehiculos;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class VehiculoTest {

    private Vehiculo vehiculo;

    @Before
    public void setUp() {
        vehiculo = new Vehiculo();
    }

    @Test
    public void testConstructorPorDefecto() {
        assertEquals("Toyota", vehiculo.getMarca());
        assertEquals("Corolla", vehiculo.getModelo());
        assertEquals(2020, vehiculo.getAño());
        assertEquals(20000, vehiculo.getKm());
    }

    @Test
    public void testSetGetMarca() {
        vehiculo.setMarca("Honda");
        assertEquals("Honda", vehiculo.getMarca());
    }

    @Test
    public void testSetGetModelo() {
        vehiculo.setModelo("Civic");
        assertEquals("Civic", vehiculo.getModelo());
    }

    @Test
    public void testSetGetAño() {
        vehiculo.setAño(2022);
        assertEquals(2022, vehiculo.getAño());
    }

    @Test
    public void testSetGetKm() {
        vehiculo.setKm(30000);
        assertEquals(30000, vehiculo.getKm());
    }

    @Test
    public void testSetGetRendimiento() {
        vehiculo.setRendimiento(15);
        assertEquals(15, vehiculo.getRendimiento());
    }

    @Test
    public void testSetGetColor() {
        vehiculo.setColor("Rojo");
        assertEquals("Rojo", vehiculo.getColor());
    }

    @Test
    public void testSetGetPlacas() {
        vehiculo.setPlacas("ABC123");
        assertEquals("ABC123", vehiculo.getPlacas());
    }

    @Test
    public void testSetGetPoliza() {
        vehiculo.setPoliza("POL123");
        assertEquals("POL123", vehiculo.getPoliza());
    }

    @Test
    public void testCheckKm() {
        // Establecer el año a 2020 (supongamos que el año actual es 2025)
        vehiculo.setAño(2020);
        vehiculo.setKm(50000); // Vehículo con 50,000 km

        // El cálculo esperado sería: añosUso = 2025 - 2020 = 5 años
        // kmEsperado = 5 * 20000 = 100000 km
        // Dado que el vehículo tiene 50,000 km, el test debería fallar si no supera los 100,000 km
        assertFalse(vehiculo.checkKm());
    }

    @Test
    public void testCheckKmNoNecesitaRevision() {
        // Establecer el año a 2023 (supongamos que el año actual es 2025)
        vehiculo.setAño(2023);
        vehiculo.setKm(10000); // Vehículo con 10,000 km

        // El cálculo esperado sería: añosUso = 2025 - 2023 = 2 años
        // kmEsperado = 2 * 20000 = 40000 km
        // El vehículo no debería necesitar revisión si tiene menos de 40,000 km
        assertFalse(vehiculo.checkKm());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetAñoFueraDeRango() {
        vehiculo.setAño(2026); // Año fuera del rango permitido
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetKmNegativo() {
        vehiculo.setKm(-1); // Kilometraje negativo no permitido
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetRendimientoInvalido() {
        vehiculo.setRendimiento(30); // Rendimiento mayor al límite de 25
    }
}
