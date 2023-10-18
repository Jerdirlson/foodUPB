package View;

import javax.swing.*;
import Model.CocinaModel;
import Controller.CocinaController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class VistaCocina extends JFrame {
    private CocinaModel cocinaModel;
    private CocinaController controller;

    // Agregar botones y etiquetas necesarios para la interfaz 
    private JButton[] iniciarButtons;
    private JButton[] terminarButtons;
    private JButton[] mostrarPedidoButtons;
    private JLabel[] estadoLabels;

    public VistaCocina() {
        // Inicializa los arreglos antes de crear el controlador
        iniciarButtons = new JButton[16];
        terminarButtons = new JButton[16];
        mostrarPedidoButtons = new JButton[16];
        estadoLabels = new JLabel[16];

        cocinaModel = new CocinaModel();

        setTitle("Cocina");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Configuración del panel principal con GridLayout
        JPanel panelPrincipal = new JPanel(new GridLayout(4, 4));

        for (int i = 0; i < 16; i++) {
            JPanel fogonPanel = new JPanel(new BorderLayout());
            iniciarButtons[i] = new JButton("Iniciar");
            terminarButtons[i] = new JButton("Terminar");
            mostrarPedidoButtons[i] = new JButton("Mostrar Pedido");
            estadoLabels[i] = new JLabel("Fogon " + (i + 1));

            fogonPanel.add(estadoLabels[i], BorderLayout.NORTH);
            fogonPanel.add(iniciarButtons[i], BorderLayout.WEST);
            fogonPanel.add(terminarButtons[i], BorderLayout.EAST);
            fogonPanel.add(mostrarPedidoButtons[i], BorderLayout.SOUTH);

            panelPrincipal.add(fogonPanel);
        }

        // Agrega el panel principal a la ventana
        add(panelPrincipal);
        setVisible(true);

        // Crea el controlador con los arreglos
        controller = new CocinaController(cocinaModel, this, iniciarButtons, terminarButtons, mostrarPedidoButtons);

        // Asigna controladores de eventos
        for (int i = 0; i < 16; i++) {
            final int fogonNumero = i + 1;

            iniciarButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        controller.iniciarPedido(fogonNumero);
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                }
            });

            terminarButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        controller.terminarPedido(fogonNumero);
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                }
            });

            mostrarPedidoButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.mostrarPedido(fogonNumero);
                }
            });
        }
    
        // Agrega el panel principal a la ventana
        add(panelPrincipal);
        setVisible(true);
    }

    // Métodos para acceder a botones y etiquetas desde el controlador
    public JButton getIniciarButton(int fogonNumero) {
        return iniciarButtons[fogonNumero - 1];
    }

    public JButton getTerminarButton(int fogonNumero) {
        return terminarButtons[fogonNumero - 1];
    }

    public JButton getMostrarPedidoButton(int fogonNumero) {
        return mostrarPedidoButtons[fogonNumero - 1];
    }

    public void setEstadoLabel(int fogonNumero, String text) {
        estadoLabels[fogonNumero - 1].setText(text);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VistaCocina();
            }
        });
    }
}