package SistemadeRegistro.Rutas;

import org.junit.Test;
import static org.junit.Assert.*;

public class PruebaRuta {

    @Test
    public void pruebaConstructorPorDefecto() {
        Ruta ruta = new Ruta();
        
        assertEquals("Inicio por defecto incorrecto", "Desconocido", ruta.getInicio());
        assertEquals("Fin por defecto incorrecto", "Desconocido", ruta.getFin());
        assertEquals("Número de paradas por defecto incorrecto", 0, ruta.getNumParadas());
        assertEquals("Distancia por defecto incorrecta", 0.0, ruta.getDistancia(), 0.001);
    }

    @Test
    public void pruebaConstructorConParametros() {
        Ruta ruta = new Ruta("UDLAP", "Puebla", 3, 15.5);
        
        assertEquals("Inicio incorrecto", "UDLAP", ruta.getInicio());
        assertEquals("Fin incorrecto", "Puebla", ruta.getFin());
        assertEquals("Número de paradas incorrecto", 3, ruta.getNumParadas());
        assertEquals("Distancia incorrecta", 15.5, ruta.getDistancia(), 0.001);
    }

    @Test
    public void pruebaSettersConValoresValidos() {
        Ruta ruta = new Ruta();
        
        ruta.setInicio("Cholula");
        ruta.setFin("San Pedro");
        ruta.setNumParadas(2);
        ruta.setDistancia(10.3);
        
        assertEquals("Inicio no se estableció correctamente", "Cholula", ruta.getInicio());
        assertEquals("Fin no se estableció correctamente", "San Pedro", ruta.getFin());
        assertEquals("Número de paradas no se estableció correctamente", 2, ruta.getNumParadas());
        assertEquals("Distancia no se estableció correctamente", 10.3, ruta.getDistancia(), 0.001);
    }

    @Test
    public void pruebaSettersConValoresInvalidos() {
        Ruta ruta = new Ruta("A", "B", 1, 1.0);
        
        ruta.setInicio("");
        ruta.setFin(null);
        ruta.setNumParadas(-1);
        ruta.setDistancia(-5.0);
        
        assertEquals("Inicio no debería aceptar cadena vacía", "Desconocido", ruta.getInicio());
        assertEquals("Fin no debería aceptar null", "Desconocido", ruta.getFin());
        assertEquals("Número de paradas no debería aceptar valores negativos", 0, ruta.getNumParadas());
        assertEquals("Distancia no debería aceptar valores negativos", 0.0, ruta.getDistancia(), 0.001);
    }

    @Test
    public void pruebaToString() {
        Ruta ruta = new Ruta("UDLAP", "Angelópolis", 2, 8.5);
        String esperado = "Ruta de UDLAP a Angelópolis, con 2 paradas. Distancia: 8.50 km.";
        assertEquals("Formato toString incorrecto", esperado, ruta.toString());
    }
}
