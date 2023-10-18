package View;

import javax.swing.*;
import Model.CocinaModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaCocina extends JFrame {
    private CocinaModel cocinaModel;

    public VistaCocina() {
        // Configura la ventana principal
        setTitle("Cocina");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        cocinaModel = new CocinaModel();

        // Crea un panel principal con GridLayout
        JPanel panelPrincipal = new JPanel(new GridLayout(4, 4));

        // Crea y agrega los botones de fogones
        for (int i = 1; i <= 16; i++) {
            JPanel fogonPanel = new JPanel(new BorderLayout());
            JButton fogonButton = new JButton("Fogon " + i);
            JButton iniciarButton = new JButton("Iniciar");
            JButton terminarButton = new JButton("Terminar");
            JButton mostrarPedidoButton = new JButton("Mostrar Pedido"); // Botón para mostrar el pedido
            JLabel estadoLabel = new JLabel("Fogon " + i);

            fogonPanel.add(estadoLabel, BorderLayout.NORTH);
            fogonPanel.add(fogonButton, BorderLayout.CENTER);
            fogonPanel.add(iniciarButton, BorderLayout.WEST);
            fogonPanel.add(terminarButton, BorderLayout.EAST);
            fogonPanel.add(mostrarPedidoButton, BorderLayout.SOUTH); // Agrega el botón "Mostrar Pedido" en la parte inferior

            panelPrincipal.add(fogonPanel);

            // Agrega un ActionListener para los botones de inicio y finalización
            int fogonNumero = i;

            // ActionListener para el botón "Iniciar" en la vista
            iniciarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    estadoLabel.setText("Cocinando pedido");
                    cocinaModel.prepararPedido();
                }
            });

            // ActionListener para el botón "Terminar" en la vista
            terminarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    estadoLabel.setText("Fogon " + fogonNumero);
                    cocinaModel.finishCooking();
                }
            });

            // ActionListener para el botón "Mostrar Pedido" en la vista
           /*  mostrarPedidoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cocinaModel.imprimirPedido(order);
                    mostrarPedido(fogonNumero);
                }
            });*/
        }

        // Agrega el panel principal a la ventana
        add(panelPrincipal);

        // Hacer visible la ventana
        setVisible(true);
    }

    // Método para mostrar el pedido en un fogón específico
    private void mostrarPedido(int fogonNumero) {
        // Implementa la lógica para mostrar el pedido en el fogón deseado
        // Puedes usar fogonNumero para identificar el fogón y obtener el pedido correspondiente.
        // Por ejemplo, cocinaModel.mostrarPedido(fogonNumero);
        // Asegúrate de implementar la lógica adecuada en CocinaModel para mostrar el pedido en un fogón específico.
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
