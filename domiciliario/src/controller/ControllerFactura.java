package controller;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import entidades.Pedido;
import entidades.Producto;
import entidades.UserClient;
import entidades.estructuras.doublee.linked.DoubleLinkedList;
import interfaces.SkeletonDomicilio;

public class ControllerFactura {
    private SkeletonDomicilio skeletonDomicilio;
    private DefaultListModel<Producto> facturaListModel;
    private JList<Producto> facturaList;
    private Pedido currentPedido;

    public ControllerFactura(String serverIP, int serverPort, String serviceName,
            DefaultListModel<Producto> facturaListModel, JList<Producto> facturaList) {
        try {
            Registry registry = LocateRegistry.getRegistry(serverIP, serverPort);
            skeletonDomicilio = (SkeletonDomicilio) registry.lookup(serviceName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.facturaListModel = facturaListModel;
        this.facturaList = facturaList;
    }

    public UserClient getCurrentUser() {
        UserClient currentUser = new UserClient();
        currentUser.setNombre_client("Nombre del Cliente");
        currentUser.setMunicipio("Municipio del Cliente");

        return currentUser;
    }

    public void addPedido(UserClient cliente) {
        try {
            skeletonDomicilio.addPedido(cliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProductoToPedido(Producto producto, Pedido pedido) {
        try {
            skeletonDomicilio.addProductoToPedido(producto, pedido);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getCostoDomicilio(UserClient user) {
        try {
            return skeletonDomicilio.getCostoDomicilio(user);
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public void deliverPedido() {
        if (currentPedido != null) {
            // Agrega la lógica para entregar el pedido, por ejemplo, a través de RMI
            try {
                skeletonDomicilio.entregarPedido(currentPedido);
                currentPedido = null; // Marca el pedido como entregado
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public DoubleLinkedList<Producto> getSelectedProducts() {
        DoubleLinkedList<Producto> productList = new DoubleLinkedList<>();

        int[] selectedIndices = facturaList.getSelectedIndices();

        for (int index : selectedIndices) {
            if (index >= 0 && index < facturaListModel.size()) {
                Producto selectedProduct = facturaListModel.getElementAt(index);
                productList.add(selectedProduct);
            }
        }

        return productList;
    }

    public DoubleLinkedList<Producto> getProductList() {
        DoubleLinkedList<Producto> productList = new DoubleLinkedList<>();
        return productList;
    }

    public Pedido getCurrentPedido() {
        return currentPedido;
    }

}