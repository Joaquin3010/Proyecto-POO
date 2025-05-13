package SistemadeRegistro.RegistrodeEstudiantes;

import org.junit.Test;
import static org.junit.Assert.*;

public class PruebaEstudiante {

    @Test
    public void pruebaConstructorPorDefecto() {
        Estudiante estudiante = new Estudiante();
        
        assertEquals("ID por defecto incorrecto", "Desconocido", estudiante.getId());
        assertEquals("Contraseña por defecto incorrecta", 
                   "sin_contrasena", 
                   estudiante.getContrasena());
        assertEquals("Nombre por defecto incorrecto", 
                   "Desconocido", 
                   estudiante.getNombre());
    }

    @Test
    public void pruebaConstructorConParametros() {
        Estudiante estudiante = new Estudiante("A12345", "ClaveSegura123", "María García");
        
        assertEquals("ID incorrecto", "A12345", estudiante.getId());
        assertEquals("Contraseña incorrecta", "ClaveSegura123", estudiante.getContrasena());
        assertEquals("Nombre incorrecto", "María García", estudiante.getNombre());
    }

    @Test
    public void pruebaValidacionContrasena() {
        Estudiante estudiante = new Estudiante();
        
        // Contraseña demasiado corta
        estudiante.setContrasena("corta");
        assertEquals("Debería rechazar contraseña corta", 
                   "sin_contrasena", 
                   estudiante.getContrasena());
        
        // Contraseña válida
        estudiante.setContrasena("Valida123");
        assertEquals("Debería aceptar contraseña válida", 
                   "Valida123", 
                   estudiante.getContrasena());
    }

    @Test
    public void pruebaFormatoToString() {
        Estudiante estudiante = new Estudiante("B67890", "OtraClave123", "Juan Pérez");
        assertEquals("El formato toString() es incorrecto",
                    "ID: B67890, Nombre: Juan Pérez",
                    estudiante.toString());
    }
}