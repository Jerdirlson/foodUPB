package server;

import interfaces.SkeletonOperario;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import entidades.estructuras.nodes.DoubleLinkedNode;

import database.Conection;
import entidades.Pedido;
import entidades.Producto;
import entidades.User;
import entidades.UserClient;
public class ServiceOperario extends UnicastRemoteObject implements SkeletonOperario{

    public Pedido pedidoService = new Pedido();

    protected ServiceOperario() throws RemoteException {
        super();
        //TODO Auto-generated constructor stub
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

            Iterator iterator = pedidoService.getProductos().iterator();
            
            while (iterator.hasNext()){
                Producto producto = (Producto) ((DoubleLinkedNode) iterator.next()).getObject();
                System.out.println(producto.nombre_producto + " " + producto.precio_unitario);
            }

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

    
}
