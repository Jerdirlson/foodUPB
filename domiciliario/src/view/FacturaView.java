package view;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.rmi.RemoteException;
import controller.ControllerFactura;
import entidades.Producto;
import entidades.UserClient;
import entidades.estructuras.doublee.linked.DoubleLinkedList;
import entidades.estructuras.nodes.DoubleLinkedNode;

public class FacturaView extends JFrame {
    private ControllerFactura controller;
    private DoubleLinkedList<Producto> facturaProductos;
    private JList<String> facturaList;

    public FacturaView(ControllerFactura controller) {
        this.controller = controller;
        // Constructor de FacturaView

        setTitle("Factura de Compra");
        int anchoVentana = 800;
        int altoVentana = 600;
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

        facturaProductos = new DoubleLinkedList<>();
        facturaList = new JList<>();

        Font fuentePersonalizada = cargarFuentePersonalizada("ruta_a_fuente/rainyhearts.ttf", 18.0f);
        aplicarFuentePersonalizada(facturaList, fuentePersonalizada);

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

        setContentPane(panelPrincipal);
    }

    public void agregarProductoALaFactura(Producto producto) {
        facturaProductos.add(producto);
        actualizarFactura();
    }

    public void actualizarFactura() {
        DefaultListModel<String> facturaListModel = new DefaultListModel<>();

        for (DoubleLinkedNode<Producto> current = facturaProductos.getHead(); current != null; current = current.getNext()) {
            Producto producto = current.getObject();
            facturaListModel.addElement(producto.getNombre_producto() + " - $" + producto.getPrecio_unitario());
        }

        double subtotal = calcularSubtotal();
        double impuestoFijo = 0.08;
        double impuesto = subtotal * impuestoFijo;
        double costoDomicilio = calcularCostoDomicilio();
        double total = subtotal + impuesto + costoDomicilio;

        facturaListModel.addElement("Subtotal: $" + String.format("%.2f", subtotal));
        facturaListModel.addElement("Impuesto (8%): $" + String.format("%.2f", impuesto));
        facturaListModel.addElement("Costo de Domicilio: $" + String.format("%.2f", costoDomicilio));
        facturaListModel.addElement("Total: $" + String.format("%.2f", total));

        facturaList.setModel(facturaListModel);
    }

    private double calcularSubtotal() {
        double subtotal = 0.0;

        for (DoubleLinkedNode<Producto> current = facturaProductos.getHead(); current != null; current = current.getNext()) {
            Producto producto = current.getObject();
            subtotal += producto.getPrecio_unitario();
        }

        return subtotal;
    }

    private double calcularCostoDomicilio() {
        double costoDomicilio = 0.0;

        try {
            UserClient user = controller.getCurrentUser();
            costoDomicilio = controller.getCostoDomicilio(user);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return costoDomicilio;
    }

    private Font cargarFuentePersonalizada(String rutaFuente, float tamano) {
        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(rutaFuente)).deriveFont(tamano);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customFont;
    }

    private void aplicarFuentePersonalizada(JComponent componente, Font fuente) {
        componente.setFont(fuente);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ControllerFactura controller = new ControllerFactura("ip", "port", "serviceName"); 
            new FacturaView(controller);
        });
    }
}
