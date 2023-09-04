/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author 57314
 */
public class Fogones extends JFrame {

    private List<Boolean> fogonesDisponibles;
    private JPanel fogonesPanel;
    private Timer timer;
    private List<Integer> pedidosEnFogones;

    public Fogones(int numFogones) {
        setTitle("Fogones");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializa la lista de fogones disponibles
        fogonesDisponibles = new ArrayList<>(numFogones);
        for (int i = 0; i < numFogones; i++) {
            fogonesDisponibles.add(true); // Inicialmente, todos los fogones están disponibles
        }

        // Inicializa la lista de pedidos en fogones
        pedidosEnFogones = new ArrayList<>(numFogones);
        for (int i = 0; i < numFogones; i++) {
            pedidosEnFogones.add(0); // Inicialmente, no hay pedidos en los fogones
        }

        // Crea un panel para mostrar los fogones
        fogonesPanel = new JPanel(new GridLayout(1, numFogones, 10, 10));
        fogonesPanel.setBackground(Color.BLACK); // Fondo negro

        // Agrega fogones al panel
        for (int i = 1; i <= numFogones; i++) {
            JPanel fogon = new JPanel();
            fogon.setLayout(new BorderLayout());
            fogon.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Borde blanco

            JLabel label = new JLabel("Fogón " + i);
            label.setForeground(Color.WHITE); // Texto blanco
            label.setHorizontalAlignment(JLabel.CENTER);
            fogon.add(label, BorderLayout.CENTER);

            fogonesPanel.add(fogon);
        }

        add(fogonesPanel);

        // Configura el temporizador para actualizar el estado de los fogones
        timer = new Timer(2000, new ActionListener() { // 2000 ms = 2 segundos
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarFogones();
            }
        });
        timer.start();
    }

    public void liberarFogon(int numeroFogon) {
        if (numeroFogon >= 1 && numeroFogon <= fogonesDisponibles.size()) {
            fogonesDisponibles.set(numeroFogon - 1, true); // Marca el fogón como disponible
            actualizarFogones(); // Actualiza visualmente el estado de los fogones
            int pedidoActual = pedidosEnFogones.get(numeroFogon - 1);
            if (pedidoActual > 0) {
                mostrarPedidoListo(numeroFogon, pedidoActual);
                pedidosEnFogones.set(numeroFogon - 1, 0); // Marca que no hay pedido en ese fogón
            }
        }
    }

    // Actualiza el estado de disponibilidad de los fogones
    public void actualizarFogones() {
        for (int i = 0; i < fogonesDisponibles.size(); i++) {
            JPanel fogon = (JPanel) fogonesPanel.getComponent(i);
            if (fogonesDisponibles.get(i)) {
                fogon.setBackground(Color.GREEN); // Fogón disponible (fondo verde)
            } else {
                fogon.setBackground(Color.RED); // Fogón no disponible (fondo rojo)
            }
        }
    }

public int asignarFogonDisponible() {
    for (int i = 0; i < fogonesDisponibles.size(); i++) {
        if (fogonesDisponibles.get(i)) {
            fogonesDisponibles.set(i, false);
            System.out.println("Pedido asignado al fogón " + (i + 1));
            return i + 1;
        }
    }

    for (int i = 0; i < fogonesDisponibles.size(); i++) {
        if (!fogonesDisponibles.get(i)) {
            fogonesDisponibles.set(i, false);
            System.out.println("Pedido asignado al fogón " + (i + 1));
            return i + 1;
        }
    }

    try {
        Thread.sleep(120000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    return asignarFogonDisponible(); // Recursión para asignar después de un tiempo de espera
}

    private void mostrarPedidoListo(int numeroFogon, int numeroPedido) {
        JFrame frame = new JFrame("Pedido Listo");
        frame.setSize(300, 100);
        frame.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("Pedido " + numeroPedido + " en Fogón " + numeroFogon + " está listo.");
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label, BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Fogones fogonesFrame = new Fogones(4);
                fogonesFrame.setVisible(true);
            }
        });
    }
}

