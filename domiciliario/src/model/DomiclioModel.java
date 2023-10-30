package model;

import java.rmi.RemoteException;

import app.ConfigLoader;
import client.ClienteDomicilio;
import entidades.Pedido;
import entidades.UserClient;
import entidades.estructuras.doublee.linked.DoubleLinkedList;
import entidades.estructuras.nodes.DoubleLinkedNode;
import entidades.estructuras.queue.QueueList;
import interfaces.SkeletonDomicilio;
import controller.ControllerFactura;

public class DomiclioModel implements SkeletonDomicilio {
    private Persona cliente;
    private String numeroFactura;
    private DoubleLinkedList<Pedido> productos;
    private QueueList<Pedido> pedidosParaLlevar;
    private Pedido currentPedido;

    private double impuestoFijo = 0.08;

    public static String IP = ConfigLoader.getProperty("IP");
    public static String PORT = ConfigLoader.getProperty("PORT_DOMICILIO");
    public static String SERVICENAMEDOMICILIO = ConfigLoader.getProperty("SERVICENAMEDOMICILIO");

    public DomiclioModel() {
        productos = new DoubleLinkedList<>();
    }

    @Override
    public void addPedido(UserClient cliente) throws RemoteException {
        Pedido nuevoPedido = new Pedido();
        productos.add(nuevoPedido);
    }

    @Override
    public void addProductoToPedido(entidades.Producto producto, Pedido pedido) throws RemoteException {
        ClienteDomicilio clienteCocina= new ClienteDomicilio("localhost", "5000", "ServiceDomicilio");
        clienteCocina.addProductoToPedido(producto,pedido);

        for (DoubleLinkedNode<Pedido> current = productos.getHead(); current != null; current = current.getNext()) {
            if (current.getObject() == pedido) {
                Pedido pedidoEncontrado = current.getObject();
                DoubleLinkedList<entidades.Producto> productosPedido = pedidoEncontrado.getProductos();
                productosPedido.add(producto);

                return;
            }
        }

    }

    @Override
    public double calcularTotalPorPedido(Pedido pedido) throws RemoteException {
         ClienteDomicilio clienteCocina= new ClienteDomicilio("localhost", "5000", "ServiceDomicilio");
        clienteCocina.calcularTotalPorPedido(pedido);

        DoubleLinkedList<entidades.Producto> productosPedido = pedido.getProductos();

        double total = 0.0;

        // Recorre la lista de productos del pedido y suma los precios
        for (DoubleLinkedNode<entidades.Producto> current = productosPedido
                .getHead(); current != null; current = current.getNext()) {
            entidades.Producto producto = current.getObject();
            total += producto.getPrecio_unitario() * impuestoFijo;
        }

        return total;
    }

    @Override
    public void generarFactura(Pedido pedido) throws RemoteException {
         ClienteDomicilio clienteCocina= new ClienteDomicilio("localhost", "5000", "ServiceDomicilio");
        clienteCocina.generarFactura(pedido);

        DoubleLinkedList<entidades.Producto> productosPedido = pedido.getProductos();

        StringBuilder factura = new StringBuilder();

        factura.append("Factura de Compra\n\n");
        factura.append("Cliente: ").append(pedido.getCliente().getNombre_client()).append("\n");
        factura.append("Dirección: ").append(pedido.getCliente().getMunicipio()).append("\n");
        factura.append("Número de Factura: ").append(pedido.getCliente()).append("\n\n");

        factura.append("Productos Comprados:\n");

        for (DoubleLinkedNode<entidades.Producto> current = productosPedido
                .getHead(); current != null; current = current.getNext()) {
            entidades.Producto producto = current.getObject();
            factura.append(producto.getNombre_producto()).append(" - $").append(producto.getPrecio_unitario())
                    .append(" x ");
        }

        double subtotal = calcularTotalPorPedido(pedido);
        double impuesto = subtotal * impuestoFijo;
        double total = subtotal + impuesto;

        factura.append("\nSubtotal: $").append(String.format("%.2f", subtotal)).append("\n");
        factura.append("Impuesto (8%): $").append(String.format("%.2f", impuesto)).append("\n");
        factura.append("Total: $").append(String.format("%.2f", total)).append("\n");

        // Puedes imprimir la factura en la consola o guardarla en un archivo
        System.out.println(factura.toString());
    }

    @Override
    public double getMontoTotal() throws RemoteException {
        ClienteDomicilio clienteCocina= new ClienteDomicilio("localhost", "5000", "ServiceDomicilio");
        clienteCocina.getMontoTotal();
        double montoTotal = 0.0;
        for (DoubleLinkedNode<Pedido> current = productos.getHead(); current != null; current = current.getNext()) {
            Pedido pedido = current.getObject();
            double totalPedido = calcularTotalPorPedido(pedido);
            montoTotal += totalPedido;
        }

        return montoTotal;
    }

    @Override
    public int getCostoDomicilio(UserClient user) throws RemoteException {

        int costoDomicilio = 0;

        boolean esVip = user.getVip();

        String barrio = user.getBarrio();
        String municipio = user.getMunicipio();

        if (esVip) {
            costoDomicilio = 0;
        } else {
            if (municipio.equalsIgnoreCase("Piedecuesta")) {
                costoDomicilio = 15000;
            } else if (municipio.equalsIgnoreCase("Bucaramanga")) {
                costoDomicilio = 13000;
            } else if (municipio.equalsIgnoreCase("Florida")) {
                costoDomicilio = 9000;
            } else if (municipio.equalsIgnoreCase("Girón")) {
                costoDomicilio = 10000;
            } else {
                costoDomicilio = -1; 
            }

        }

        return costoDomicilio;
    }

    @Override
    public UserClient getCurrentUser() throws RemoteException {
        ClienteDomicilio cliente = new ClienteDomicilio("localhost", "5000", "ServiceDomicilio");
        cliente.getCurrentUser();
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentUser'");
    }

    @Override
    public void agregarPedido(Pedido pedido) throws RemoteException {
        pedidosParaLlevar.push(pedido);
    }

    @Override
    public void entregarPedidos() throws RemoteException {
        pedidosParaLlevar.clear();
    }

    // @Override
    public void entregarPedido(Pedido pedido) {
        if (pedido != null) {
            try {
                
                System.out.println("Pedido entregado: " + pedido);
                
                currentPedido = null;
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public QueueList generarPedido() throws RemoteException {
        QueueList pedidos = null;
        ClienteDomicilio cliente = new ClienteDomicilio(IP,PORT,SERVICENAMEDOMICILIO);

        try {
            pedidos = cliente.generarPedido();
        } catch (Exception e) {
            System.out.println("Error en generarPedido MODEL ");
        }
        return pedidos;
    }


    

}
