package SistemadeRegistro.AgendarCita;

import SistemadeRegistro.RegistrodeChofer.Chofer;
import java.lang.reflect.*;
import javax.swing.*;
import java.awt.*;
import java.time.*;
import javax.swing.SwingUtilities;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AgendarCitaGUITest {
    
    private AgendarCitaGUI gui;
    private Chofer chofer;

    @Before
    public void setUp() throws Exception {
        // Crear instancia básica de Chofer
        chofer = createBasicChofer();
        
        SwingUtilities.invokeAndWait(() -> {
            gui = new AgendarCitaGUI(chofer);
        });
    }

    private Chofer createBasicChofer() throws Exception {
        // Obtener cualquier constructor disponible
        Constructor<?>[] constructors = Chofer.class.getDeclaredConstructors();
        if (constructors.length == 0) {
            throw new RuntimeException("No se encontraron constructores en la clase Chofer");
        }
        
        Constructor<?> constructor = constructors[0];
        constructor.setAccessible(true);
        
        // Crear parámetros con valores por defecto
        Object[] params = new Object[constructor.getParameterCount()];
        for (int i = 0; i < params.length; i++) {
            params[i] = getDefaultValue(constructor.getParameterTypes()[i]);
        }
        
        return (Chofer) constructor.newInstance(params);
    }

    private Object getDefaultValue(Class<?> type) {
        if (type == boolean.class) return false;
        if (type == byte.class) return (byte) 0;
        if (type == short.class) return (short) 0;
        if (type == int.class) return 0;
        if (type == long.class) return 0L;
        if (type == float.class) return 0.0f;
        if (type == double.class) return 0.0;
        if (type == char.class) return '\0';
        if (type == String.class) return "";
        return null;
    }

    @Test
    public void testGUIInicializacion() {
        assertNotNull("La GUI no se inicializó correctamente", gui);
    }

    // Método auxiliar genérico para obtener componentes
    @SuppressWarnings("unchecked")
    private <T> T getComponent(String fieldName) {
        try {
            Field field = gui.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(gui);
        } catch (Exception e) {
            throw new RuntimeException("Error al acceder al componente " + fieldName, e);
        }
    }

    @Test
    public void testComponentesGUI() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JTextField correoField = getComponent("txtCorreo");
            assertNotNull("Campo de correo no encontrado", correoField);
            
            JComboBox<Integer> comboDia = getComponent("comboDia");
            assertNotNull("Combo día no encontrado", comboDia);
            
            // Verificar otros componentes según sea necesario
        });
    }
}