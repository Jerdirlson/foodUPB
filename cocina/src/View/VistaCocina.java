package View;

import javax.swing.*;
import Model.CocinaModel;
import entidades.Pedido;
import Controller.CocinaController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Iterator;

public class VistaCocina extends JFrame {
    private CocinaModel cocinaModel;
    private CocinaController controller;

    private JButton[] iniciarButtons;
    private JButton[] terminarButtons;
    private JButton[] mostrarPedidoButtons;
    private JLabel[] estadoLabels;
    private JButton cocinarPedidoButton;
    private JButton mostrarPedidosPendientesButton;

    public VistaCocina() {
        setVentanaIcono();
        iniciarButtons = new JButton[16];
        terminarButtons = new JButton[16];
        mostrarPedidoButtons = new JButton[16];
        estadoLabels = new JLabel[16];
        cocinarPedidoButton = new JButton("Cocinar Pedido");
        add(cocinarPedidoButton, BorderLayout.SOUTH);
        mostrarPedidosPendientesButton = new JButton("Mostrar Pedidos Pendientes");
        add(mostrarPedidosPendientesButton, BorderLayout.NORTH);

        cocinaModel = new CocinaModel();

        setTitle("Cocina");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panelPrincipal = new JPanel(new GridLayout(4, 4));

        for (int i = 0; i < 16; i++) {
            JPanel fogonPanel = new JPanel(new BorderLayout());
            iniciarButtons[i] = new JButton("Iniciar");
            terminarButtons[i] = new JButton("Terminar");
            mostrarPedidoButtons[i] = new JButton("Mostrar Pedido");
            estadoLabels[i] = new JLabel("Fogon " + (i + 1));

            fogonPanel.add(estadoLabels[i], BorderLayout.NORTH);

            // Crea un JLabel para mostrar las imágenes de "Fogón encendido" y "Fogón apagado"
            final JLabel fogonLabel = new JLabel(new ImageIcon("src\\View\\Imagenes\\FogonApagado.png"));
            fogonPanel.add(fogonLabel, BorderLayout.CENTER);

            fogonPanel.add(iniciarButtons[i], BorderLayout.WEST);
            fogonPanel.add(terminarButtons[i], BorderLayout.EAST);
            fogonPanel.add(mostrarPedidoButtons[i], BorderLayout.SOUTH);

            mostrarPedidoButtons[i].setBackground(Color.RED);

            panelPrincipal.add(fogonPanel);

            final int fogonNumero = i + 1;

            iniciarButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        // Muestra la imagen de "Fogón encendido" al hacer clic en "Iniciar"
                        fogonLabel.setIcon(new ImageIcon("src\\View\\Imagenes\\FogonEncendido.png"));
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
                        // Muestra la imagen de "Fogón apagado" al hacer clic en "Terminar"
                        fogonLabel.setIcon(new ImageIcon("src\\View\\Imagenes\\FogonApagado.png"));
                        controller.terminarPedido(fogonNumero);
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }

        add(panelPrincipal);
        setVisible(true);

        controller = new CocinaController(cocinaModel, this, iniciarButtons, terminarButtons, mostrarPedidoButtons);

        cocinarPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cocinaModel.getClientesVip();
                    cocinaModel.getClientesNoVip();
                    cocinaModel.CocinarPedido(CocinaModel.getPedidoACocinar());
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
        });

        mostrarPedidosPendientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cocinaModel.getClientesVip();
                cocinaModel.getClientesNoVip();
                cocinaModel.mostrarPedidosPendientes();
            }
        });

        for (int i = 0; i < 16; i++) {
            final int fogonNumero = i + 1;

            mostrarPedidoButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        controller.mostrarPedido(fogonNumero);
                    } catch (RemoteException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            });
        }
    }

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

    private void setVentanaIcono() {
        URL resource = getClass().getResource("/view/Imagenes/Frame3.png");

        if (resource != null) {
            ImageIcon icono = new ImageIcon(resource);

            setIconImage(icono.getImage());
        } else {
            System.err.println("No se pudo cargar la imagen del ícono.");
        }
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
