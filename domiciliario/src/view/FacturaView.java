package view;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.ControllerFactura;
import entidades.Pedido;
import entidades.Producto;
import entidades.UserClient;
import entidades.estructuras.doublee.linked.DoubleLinkedList;

public class FacturaView extends JFrame {
    private ControllerFactura controller;
    private UserClient selectedClient;
    private DefaultListModel<Producto> facturaListModel;
    private JList<Producto> facturaList;

    public FacturaView() {
    }

    public void inicializar(){
        this.selectedClient = null;

        setTitle("Factura de Compra");
        int anchoVentana = 1920;
        int altoVentana = 1080;
        setSize(anchoVentana, altoVentana);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel tituloPanel = new JPanel();
        JLabel tituloLabel = new JLabel("Factura de Compra");
        tituloLabel.setFont(new Font("ADLaM Display", Font.BOLD, 24));
        tituloLabel.setForeground(Color.RED);
        tituloPanel.add(tituloLabel);

        facturaListModel = new DefaultListModel<>();
        facturaList = new JList<>(facturaListModel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        tituloPanel.setBackground(Color.BLACK);
        panelPrincipal.add(tituloPanel, gbc);

        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        panelPrincipal.add(new JScrollPane(facturaList), gbc);

        JButton agregarPedidoButton = new JButton("Agregar Pedido");
        agregarPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    selectedClient = controller.getCurrentUser();
                    if (selectedClient != null) {
                        Pedido pedido = new Pedido();
                        controller.addPedido(selectedClient);
                        // refreshFactura();
                    } else {
                        JOptionPane.showMessageDialog(FacturaView.this, "No se ha seleccionado un cliente válido.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton agregarProductoButton = new JButton("Agregar Producto a Pedido");
        agregarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedClient != null) {
                    DoubleLinkedList<Producto> selectedProducts = controller.getSelectedProducts();
                    if (selectedProducts != null) {
                        Pedido pedido = new Pedido();
                        pedido.setProductos(selectedProducts);
                        // refreshFactura();
                    } else {
                        JOptionPane.showMessageDialog(FacturaView.this, "Por favor, seleccione productos.");
                    }
                }
            }
        });

        JButton entregarPedidosButton = new JButton("Entregar Pedidos");
        entregarPedidosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.deliverPedido();
                    JOptionPane.showMessageDialog(FacturaView.this, "Los pedidos han sido entregados.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        gbc.gridy = 2;
        panelPrincipal.add(agregarPedidoButton, gbc);
        gbc.gridy = 3;
        panelPrincipal.add(agregarProductoButton, gbc);
        gbc.gridy = 4;
        panelPrincipal.add(entregarPedidosButton, gbc);

        setContentPane(panelPrincipal);
    }

    // private void refreshFactura() {
    //     facturaListModel.clear();
    //     if (selectedClient != null) {
    //         DoubleLinkedList<Producto> productos = pedido.getProductos();
    //         if (productos != null) {
    //             for (Producto producto : productos) {
    //                 facturaListModel.addElement(producto);
    //             }
    //         }
    //     }
    // }
}
