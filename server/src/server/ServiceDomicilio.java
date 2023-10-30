package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;

import entidades.Pedido;
import entidades.Producto;
import entidades.UserClient;
import entidades.estructuras.doublee.linked.DoubleLinkedList;
import entidades.estructuras.nodes.DoubleLinkedNode;
import entidades.estructuras.queue.QueueArray;
import entidades.estructuras.queue.QueueList;
import interfaces.SkeletonDomicilio;
public class ServiceDomicilio extends UnicastRemoteObject implements SkeletonDomicilio {
    public static QueueList productosParaEntregar = new QueueList<>();
    private static QueueList pedidos = new QueueList<>();
    private double totalMonto = 0.0;

    public ServiceDomicilio() throws RemoteException {
        super();
    }



    public static void pushProducto(Producto producto) throws RemoteException {
        try {
            productosParaEntregar.push(producto);
            System.out.println(productosParaEntregar.size());
            if (productosParaEntregar.size() > 7) {
                // Se debe hacer todo lo que se tenga que hacer a la hora de entregar el domicilio al domiciliario
                
                ServiceDomicilio service = new ServiceDomicilio(); // Crea una instancia de ServiceDomicilio
                service.generarPedido();
            }
            
            System.out.println("Tama;o de la cola " +productosParaEntregar.size());
        } catch (Exception e) {
            System.out.println("Error en pushProducto : " + e.getMessage());
        }
        
    }
    @Override
    public QueueList generarPedido() throws RemoteException {
        System.out.println("Entra aqui");
        
        try {

            System.out.println("Entra a generar pedidos");
        
            boolean isEmpty = true;
            Iterator iterador = productosParaEntregar.list.iterator();
        
            while (iterador.hasNext()){
                DoubleLinkedNode<Producto> productoNode = (DoubleLinkedNode<Producto>) iterador.next();
                Producto currentProducto = productoNode.getObject();
                System.out.println("Produycto con dueño que llega " + currentProducto.getNombre_producto() + " " + currentProducto.getUsuarioCliente().getNombre_client());
                Iterator iteradorPedidos = pedidos.list.iterator();
                boolean encontro = false;
                if (pedidos.isEmpty()) {                    
                    //Solo entra aqui siempre y cuando la lista de pedidos este vacia, osea este sera el primer pedido
                    Pedido pedido = new Pedido();
                    pedido.getProductos().add(currentProducto);
                    pedido.setCliente(currentProducto.getUsuarioCliente());
                    productosParaEntregar.pop();
                    pedidos.push(pedido);
                    encontro = true;
                    isEmpty = false;
                }
                while (iteradorPedidos.hasNext()){
                    DoubleLinkedNode<Pedido> pedidoNode = (DoubleLinkedNode<Pedido>) iteradorPedidos.next();
                    Pedido currentNodePedidos = pedidoNode.getObject();
                    if (currentProducto.getUsuarioCliente().getUserId().equals(currentNodePedidos.getCliente().getUserId())) {
                        currentNodePedidos.getProductos().add(currentProducto);
                        productosParaEntregar.pop();
                        encontro = true;
                        break;
                    }else{
                        System.out.println("primer condicion " + currentProducto.getUsuarioCliente().getUserId());
                        System.out.println("segunda condicion " + currentNodePedidos.getCliente().getUserId());
                        System.out.println(currentProducto.getUsuarioCliente().getUserId() == currentNodePedidos.getCliente().getUserId());
                    }
                }

                if (!encontro ^ isEmpty) {
                        Pedido nuevoPedido = new Pedido();
                        nuevoPedido.getProductos().add(currentProducto);
                        nuevoPedido.setCliente(currentProducto.getUsuarioCliente());
                        productosParaEntregar.pop();
                        pedidos.push(nuevoPedido);
                }
            }
        } catch (Exception e) {
            System.out.println("Error en generar Pedidos: " + e.getMessage());
        }

        pedidos.iterateStack();

        return pedidos;

    }

    public double calcularTotalPorPedido(Pedido pedido) throws RemoteException {
        DoubleLinkedList<Producto> productos = pedido.getProductos();
        double totalPedido = 0.0;
        for (DoubleLinkedNode<Producto> node = productos.getHead(); node != null; node = node.getNext()) {
            Producto producto = node.getObject();
            totalPedido += producto.getPrecio_unitario();
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



    @Override
    public void agregarPedido(Pedido pedido) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarPedido'");
    }



    @Override
    public void entregarPedidos() throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'entregarPedidos'");
    }



    // @Override
    // public void entregarPedido(Pedido pedido) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'entregarPedido'");
    // }    

}