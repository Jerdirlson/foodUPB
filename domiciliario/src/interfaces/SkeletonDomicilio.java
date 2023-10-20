package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import entidades.Pedido;
import entidades.Producto;
import entidades.UserClient;
import entidades.estructuras.nodes.DoubleLinkedNode;
import entidades.estructuras.priorityQueue.PriorityQueueList;

public interface SkeletonDomicilio  extends Remote{
    
    
    void addPedido(UserClient cliente) throws RemoteException;

    void addProductoToPedido(Producto producto, Pedido pedido) throws RemoteException;

    double calcularTotalPorPedido(Pedido pedido) throws RemoteException;

    void generarFactura(Pedido pedido) throws RemoteException;

    double getMontoTotal() throws RemoteException;

    public  int getCostoDomicilio(UserClient user)throws RemoteException;

    public UserClient getCurrentUser() throws RemoteException;

    void agregarPedido(Pedido pedido) throws RemoteException;

    void entregarPedidos() throws RemoteException;

    void entregarPedido(Pedido pedido);
    
}
