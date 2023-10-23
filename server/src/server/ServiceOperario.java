package server;

import interfaces.SkeletonOperario;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import database.Conection;
import entidades.Pedido;
import entidades.Producto;
import entidades.User;
import entidades.UserClient;
public class ServiceOperario extends UnicastRemoteObject implements SkeletonOperario{

    Pedido pedidoService = new Pedido();
    ServiceCocina cocinaService = new ServiceCocina();


    protected ServiceOperario() throws RemoteException {
        super();
    }

    @Override
    public User login(String email, String password) {
        User user = null;

        try {
            user = Conection.getUser(email, password);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

        return user;
    }

    @Override
    public UserClient getUserClient(String numCliente) throws RemoteException {
        UserClient userClient = null;

        try {
            userClient = Conection.getUserClient(numCliente);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

        return userClient;
    }

    @Override
    public Producto[] getProductos() throws RemoteException {
        Producto[] productos = null;
        try {
            productos = Conection.getProductos();
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return productos;
    }

    @Override
    public void enviarPedido(Pedido pedido) throws RemoteException {
        try {
            pedidoService = pedido;

            // ColaPrioridadCocina.cola.add(pedido.getCliente().vip ?1:0, pedido);

            System.out.println(pedidoService.getCliente().nombre_client);

            // Iterator iterator = pedidoService.getProductos().iterator();
            
            // while (iterator.hasNext()){
            //     Producto producto = (Producto) ((DoubleLinkedNode) iterator.next()).getObject();
            //     System.out.println(producto.nombre_producto + " " + producto.precio_unitario);
            // }

            cocinaService.addOrder(pedido);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insertarCliente(UserClient client) throws RemoteException {
        try {
            return Conection.insertarCliente(client);

        } catch (Exception e) {
            System.out.println("Error, no se pudo insertar el cliente en la base de datos" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean registrarPedido(Pedido pedido) throws RemoteException {
         try {
            return Conection.registrarPedido(pedido);

        } catch (Exception e) {
            System.out.println("Error, no se pudo registrar el pedido en la base de datos" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Producto[] getProductosRecientes(UserClient client) throws RemoteException {
       Producto[] productosRecientes = null;
        try {
            productosRecientes = Conection.getProductosRecientes(client);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return productosRecientes;
    }

    
}
