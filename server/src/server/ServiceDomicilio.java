package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import entidades.Pedido;
import entidades.Producto;
import entidades.UserClient;
import entidades.estructuras.doublee.linked.DoubleLinkedList;
import entidades.estructuras.nodes.DoubleLinkedNode;
import entidades.estructuras.queue.QueueList;
import interfaces.SkeletonDomicilio;

public class ServiceDomicilio extends UnicastRemoteObject implements SkeletonDomicilio {
    private QueueList<Pedido> pedidos = new QueueList<>();
    private double totalMonto = 0.0;

    public ServiceDomicilio() throws RemoteException {
        super();
    }

    public double calcularTotalPorPedido(Pedido pedido) throws RemoteException {
        DoubleLinkedList<Producto> productos = pedido.getProductos();
        double totalPedido = 0.0;
        for (DoubleLinkedNode<Producto> node = productos.getHead(); node != null; node = node.getNext()) {
            Producto producto = node.getObject();
            totalPedido += producto.getPrecio_unitario() ;
        }

        return totalPedido;
    }

    public void generarFactura(Pedido pedido) throws RemoteException {
        double totalPedido = calcularTotalPorPedido(pedido);
        System.out.println("=== Factura ===");
        System.out.println("Total: " + totalPedido);
    }

    @Override
    public void addPedido(UserClient cliente) throws RemoteException {
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedidos.push(pedido);
        double costoPedido = calcularTotalPorPedido(pedido);
        totalMonto += costoPedido;
        System.out.println("Pedido agregado para el cliente: " + cliente.getNombre_client());
        System.out.println("Costo del pedido: " + costoPedido);
        System.out.println("Monto total actual: " + totalMonto);
    }

    @Override
    public void addProductoToPedido(Producto producto, Pedido pedido) throws RemoteException {
        pedido.getProductos().add(producto);
        totalMonto += producto.getPrecio_unitario();
        System.out.println("Producto agregado al pedido: " + producto.getNombre_producto());
        System.out.println("Monto total actual: " + totalMonto);
    }

    @Override
    public double getMontoTotal() throws RemoteException {
        return totalMonto;
    }

    

    @Override
    public int getCostoDomicilio(UserClient user) throws RemoteException {
        String municipio = user.getMunicipio();

        if (municipio.equalsIgnoreCase("Piedecuesta")) {
            return 15000;
        } else if (municipio.equalsIgnoreCase("Bucaramanga")) {
            return 13000;
        } else if (municipio.equalsIgnoreCase("Florida")) {
            return 9000;
        } else if (municipio.equalsIgnoreCase("Girón")) {
            return 10000;
        }
        return -1;
    }


    @Override
    public UserClient getCurrentUser() throws RemoteException {
        return getCurrentUser();
    }


    

}