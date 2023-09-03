package view;
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
    private final MenuApp menuApp; // Referencia a la instancia de MenuApp

    public MostrarPedidosRecientes(String phoneNumber, MenuApp menuApp) { // Recibe una instancia de MenuApp
        this.phoneNumber = phoneNumber;
        this.menuApp = menuApp;

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
                menuApp.setVisible(true);
                dispose(); // Cierra la ventana actual
            }
        });

        setContentPane(contentPane);
    }

    private List<String> obtenerPedidosRecientesDelCliente(String phoneNumber) {
        List<String> pedidos = new ArrayList<>();
        pedidos.add("Pedido 1: Pizza");
        pedidos.add("Pedido 2: Hamburguesa");
        pedidos.add("Pedido 3: Ensalada");
        return pedidos;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Fogones fogones = new Fogones(4); // Crear una instancia de Fogones con 4 fogones
            MenuApp tiendaFrame = new MenuApp(fogones);

            // Agregar una persona de prueba al sistema
            Persona personaPrueba = new Persona("Juan Perez", "1234", "Calle 123, Barrio ABC");
            tiendaFrame.getListaPersonasRegistradas().add(personaPrueba);

            // Crear y mostrar la ventana de MostrarPedidosRecientes
            MostrarPedidosRecientes ventana = new MostrarPedidosRecientes("Número de Teléfono", tiendaFrame);
            ventana.setVisible(true);
        });
    }
}
