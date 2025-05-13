package SistemadeRegistro.RegistrodeChofer;

import static org.junit.Assert.*;
import javax.swing.*;
import java.awt.*;
import org.junit.Test;
import java.lang.reflect.InvocationTargetException;

public class LoginChoferGUITest {

    @Test
    public void testInterfazLogin() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            LoginChoferGUI login = new LoginChoferGUI();
            
            // Verificar configuración básica
            assertEquals("Iniciar Sesión Chofer", login.getTitle());
            assertEquals(400, login.getWidth());
            assertEquals(150, login.getHeight());
            
            // Obtener componentes directamente
            JPanel panel = (JPanel)login.getContentPane().getComponent(0);
            JLabel lblCurp = (JLabel)panel.getComponent(0);
            JTextField txtCurp = (JTextField)panel.getComponent(1);
            JButton btnLogin = (JButton)panel.getComponent(3);
            
            // Verificaciones
            assertEquals("CURP:", lblCurp.getText());
            assertNotNull(txtCurp);
            assertEquals("Iniciar Sesión", btnLogin.getText());
            
            login.dispose();
        });
    }

    @Test
    public void testLoginExitoso() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            LoginChoferGUI login = new LoginChoferGUI();
            
            // Obtener componentes directamente
            JPanel panel = (JPanel)login.getContentPane().getComponent(0);
            JTextField txtCurp = (JTextField)panel.getComponent(1);
            JButton btnLogin = (JButton)panel.getComponent(3);
            
            // Simular entrada y clic
            txtCurp.setText("TEST123456ABCDEF12");
            btnLogin.doClick();
            
            // Verificar que se cerró
            assertFalse(login.isVisible());
            
            login.dispose();
        });
    }
}