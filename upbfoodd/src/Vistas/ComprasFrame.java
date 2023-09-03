
package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ComprasFrame extends JFrame {

    private ArrayList<CompraProducto> listaProductos;

    public ComprasFrame(ArrayList<CompraProducto> listaProductos) {
        this.listaProductos = listaProductos;
        setTitle("Compras Realizadas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.BLACK);

        mostrarCompras();

        JButton pasarPedidoButton = new JButton("Pasar pedido a la cocina");
        pasarPedidoButton.setBackground(Color.RED);
        pasarPedidoButton.setForeground(Color.WHITE);
        pasarPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Pedido enviado a la cocina", "Pedido", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(pasarPedidoButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void mostrarCompras() {
        StringBuilder mensaje = new StringBuilder("Productos comprados:\n");
        double recibo = 0;
        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        for (CompraProducto compraProducto : listaProductos) {
            double subtotal = compraProducto.getPrecio() * compraProducto.getCantidad();
            mensaje.append("- ").append(compraProducto.getNombre()).append(" (")
                    .append(compraProducto.getCantidad()).append(" Unidad) - Precio:")
                    .append(decimalFormat.format(subtotal)).append("\n");
            recibo += subtotal;
        }

        mensaje.append("Recibo: $").append(decimalFormat.format(recibo));

        JTextArea textArea = new JTextArea(mensaje.toString());
        textArea.setEditable(false);
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(Color.BLACK);

        // Agregamos un JScrollPane para que la factura sea desplazable
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(800, 500)); // Tamaño preferido

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }
}
