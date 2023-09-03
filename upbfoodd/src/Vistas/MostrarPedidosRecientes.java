package Vistas;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MostrarPedidosRecientes extends JFrame {
    private final JPanel contentPane;
    private final String phoneNumber;

    public MostrarPedidosRecientes(String phoneNumber) {
        this.phoneNumber = phoneNumber;

        // Configurar el título del frame
        setTitle("Pedidos Recientes para " + phoneNumber);

        // Fondo negro
        contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.BLACK);
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un letrero grande en la parte superior izquierda dentro del frame rojo
        JLabel titleLabel = new JLabel("Pedidos Recientes para " + phoneNumber);
        titleLabel.setForeground(Color.RED); // Texto en rojo
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 30)); // Tipo de letra y tamaño
        titleLabel.setHorizontalAlignment(JLabel.LEFT); // Alineación a la izquierda

        // Agregar el letrero al panel de contenido en la parte superior
        contentPane.add(titleLabel, BorderLayout.NORTH);

        // Simula una lista de pedidos recientes para el cliente
        List<String> pedidosRecientes = obtenerPedidosRecientesDelCliente(phoneNumber);

        // Crear un JTextArea para mostrar la lista de pedidos
        JTextArea pedidosTextArea = new JTextArea(20, 40);
        pedidosTextArea.setEditable(false);
        pedidosTextArea.setFont(new Font("Arial", Font.PLAIN, 20)); // Tipo de letra y tamaño

        // Agregar los pedidos recientes al JTextArea
        for (String pedido : pedidosRecientes) {
            pedidosTextArea.append(pedido + "\n");
        }

        // Agregar el JTextArea a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(pedidosTextArea);

        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Botón "Realizar Pedido" en rojo
        JButton realizarPedidoButton = new JButton("Realizar Pedido");
        realizarPedidoButton.setBackground(Color.RED);
        realizarPedidoButton.setForeground(Color.WHITE);
        realizarPedidoButton.setFont(new Font("Arial", Font.PLAIN, 20));
        realizarPedidoButton.setPreferredSize(new Dimension(200, 40)); // Tamaño personalizado

        // Agregar el botón al panel de contenido en la parte inferior
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(realizarPedidoButton);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        realizarPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuApp menuApp = new MenuApp();
                menuApp.setVisible(true);
                dispose(); // Cierra la ventana actual
            }
        });

        setContentPane(contentPane);
    }

    private List<String> obtenerPedidosRecientesDelCliente(String phoneNumber) {
        // Aquí puedes implementar la lógica para obtener los pedidos recientes del cliente
        // Esto podría incluir consultas a una base de datos o la obtención de datos de algún otro origen.
        // Por ahora, simplemente devolvemos una lista de pedidos ficticios.
        List<String> pedidos = new ArrayList<>();
        pedidos.add("Pedido 1: Pizza");
        pedidos.add("Pedido 2: Hamburguesa");
        pedidos.add("Pedido 3: Ensalada");
        return pedidos;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Crear y mostrar la ventana
            MostrarPedidosRecientes ventana = new MostrarPedidosRecientes("Número de Teléfono");
            ventana.setVisible(true);
        });
    }
}

