package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import entidades.Pedido;
import entidades.Producto;
import entidades.User;
import entidades.UserClient;

public interface SkeletonOperario extends Remote{
    
    public User login(String email, String password) throws RemoteException;

    public UserClient getUserClient(String numCliente) throws RemoteException;

    public Producto[] getProductos() throws RemoteException;

    public void enviarPedido(Pedido pedido) throws RemoteException;

    public boolean insertarCliente(UserClient client) throws RemoteException;
    
    public boolean registrarPedido(Pedido pedido) throws RemoteException;

    public Producto[] getProductosRecientes(UserClient client) throws RemoteException;
    
}
