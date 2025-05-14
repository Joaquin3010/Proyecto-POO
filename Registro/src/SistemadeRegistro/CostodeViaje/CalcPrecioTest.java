package SistemadeRegistro.CostodeViaje;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CalcPrecioTest {
    
    private CalcPrecio calculadora;
    private final double DELTA = 0.001; // Para comparaciones de números decimales
    
    @Before
    public void setUp() {
        calculadora = new CalcPrecio(100.0, 15);
    }
    
    @Test
    public void testConstructorValoresValidos() {
        assertEquals(100.0, calculadora.getDistancia(), DELTA);
        assertEquals(15, calculadora.getRendimiento());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorDistanciaInvalida() {
        new CalcPrecio(0, 15);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorRendimientoInvalido() {
        new CalcPrecio(100.0, 0);
    }
    
    @Test
    public void testPrecioFijo() {
        double resultado = calculadora.precioFijo(100.0);
        assertEquals(2000.0, resultado, DELTA); // 100 km * 20 pesos/km
    }
    
    @Test
    public void testTarifaRendimiento() {
        double resultado = calculadora.tarifaRendimiento();
        // (25 pesos/litro) * (100 km / 15 km/l) = 166.666...
        assertEquals(166.666, resultado, DELTA);
    }
    
    @Test
    public void testCostoViaje() {
        double resultado = calculadora.costoViaje();
        // 2000 (precio fijo) + 166.666 (rendimiento) = 2166.666...
        assertEquals(2166.666, resultado, DELTA);
    }
    
    @Test
    public void testGetTotal() {
        double resultado = calculadora.getTotal();
        assertEquals(2166.666, resultado, DELTA);
    }
    
    @Test
    public void testSetDistanciaValida() {
        calculadora.setDistancia(150.0);
        assertEquals(150.0, calculadora.getDistancia(), DELTA);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetDistanciaInvalida() {
        calculadora.setDistancia(-10.0);
    }
    
    @Test
    public void testSetRendimientoValido() {
        calculadora.setRendimiento(20);
        assertEquals(20, calculadora.getRendimiento());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetRendimientoCero() {
        calculadora.setRendimiento(0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetRendimientoNegativo() {
        calculadora.setRendimiento(-5);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetRendimientoLimiteSuperior() {
        calculadora.setRendimiento(30); // Mayor que 25
    }
    
    @Test
    public void testSetRendimientoLimiteInferior() {
        calculadora.setRendimiento(1); // Valor mínimo válido
        assertEquals(1, calculadora.getRendimiento());
    }
    
    @Test
    public void testSetRendimientoLimiteSuperiorValido() {
        calculadora.setRendimiento(24); // Valor justo debajo del límite
        assertEquals(24, calculadora.getRendimiento());
    }
}