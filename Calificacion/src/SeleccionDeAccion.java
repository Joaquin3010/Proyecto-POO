import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Calificacion.SelecciondeCalificacionGUI;
import Reporte.ReporteUsuario;


public class SeleccionDeAccion extends JFrame {
    private JLabel displayLabel;

    public SeleccionDeAccion() {
        setTitle("Sistema de Calificación - Transporte UDLAP");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 10, 10));
        

        displayLabel = new JLabel("Seleccione la accion deseada:", SwingConstants.CENTER);
        panelPrincipal.add(displayLabel, BorderLayout.NORTH);


        JButton ReporteButton = new JButton("Reporte ");
        panelBotones.add(ReporteButton);


        JButton CalificacionButton = new JButton("Calificación");
        panelBotones.add(CalificacionButton);

        
        ReporteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                ReporteUsuario ReporteUsuario = new ReporteUsuario(SeleccionDeAccion.this);
                ReporteUsuario.setVisible(true);
            }
        });
        CalificacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                SelecciondeCalificacionGUI SelecciondeCalificacionGUI = new SelecciondeCalificacionGUI(SeleccionDeAccion.this);
                SelecciondeCalificacionGUI.setVisible(true);
            }
        });
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        add(panelPrincipal);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SeleccionDeAccion().setVisible(true);
            }
        });
    }
}