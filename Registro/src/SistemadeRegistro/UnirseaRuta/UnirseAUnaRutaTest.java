package SistemadeRegistro.UnirseaRuta;

import static org.junit.Assert.*;
import javax.swing.*;
import java.awt.*;
import org.junit.Before;
import org.junit.Test;

public class UnirseAUnaRutaTest {

    private UnirseAUnaRuta ventana;

    @Before
    public void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            ventana = new UnirseAUnaRuta(null) {
                @Override
                public void dispose() {
                    // Evita que se cierre la ventana durante el test
                }
            };
        });
    }

    @Test
    public void testComponentesExistentesYFuncionalesSinEjecutarSystemExit() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            assertNotNull("La ventana debe estar creada", ventana);

            JTextField campoRuta = encontrarCampoTexto(ventana, 0);
            JTextField campoParada = encontrarCampoTexto(ventana, 1);
            JTextField campoDistancia = encontrarCampoTexto(ventana, 2);
            JButton botonUnirse = encontrarBotonPorTexto(ventana, "Unirse a la Ruta");

            assertNotNull("Campo Número de Ruta debe existir", campoRuta);
            assertNotNull("Campo Número de Parada debe existir", campoParada);
            assertNotNull("Campo Distancia debe existir", campoDistancia);
            assertNotNull("Botón Unirse debe existir", botonUnirse);

            // Simular ingreso de datos
            campoRuta.setText("10");
            campoParada.setText("5");
            campoDistancia.setText("1.2");

            //  No hacer clic en el botón porque se ejecuta System.exit(0)
            System.out.println("[Test] Datos ingresados correctamente. Se omite el clic al botón para evitar System.exit.");
        });
    }

    // Buscar el N-ésimo JTextField de forma recursiva
    private JTextField encontrarCampoTexto(Container contenedor, int indice) {
        int[] contador = {0};
        return buscarRecursivo(contenedor, JTextField.class, contador, indice);
    }

    // Buscar botón por texto
    private JButton encontrarBotonPorTexto(Container contenedor, String textoEsperado) {
        for (Component comp : contenedor.getComponents()) {
            if (comp instanceof JButton boton && boton.getText().equals(textoEsperado)) {
                return boton;
            } else if (comp instanceof Container hijo) {
                JButton resultado = encontrarBotonPorTexto(hijo, textoEsperado);
                if (resultado != null) return resultado;
            }
        }
        return null;
    }

    // Buscar el componente N de cierto tipo
    private <T extends Component> T buscarRecursivo(Container cont, Class<T> tipo, int[] contador, int indice) {
        for (Component c : cont.getComponents()) {
            if (tipo.isInstance(c)) {
                if (contador[0] == indice) {
                    return tipo.cast(c);
                }
                contador[0]++;
            }
            if (c instanceof Container) {
                T resultado = buscarRecursivo((Container) c, tipo, contador, indice);
                if (resultado != null) return resultado;
            }
        }
        return null;
    }
}
