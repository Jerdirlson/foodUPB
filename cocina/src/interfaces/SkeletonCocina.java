package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;

import entidades.Pedido;
import entidades.Producto;
import entidades.Stove;
import entidades.estructuras.queue.QueueList;    


public interface SkeletonCocina extends Remote{
    
    public void addOrder(Pedido order) throws RemoteException;
    void prepararPedido(Producto producto) throws RemoteException;
    void finishCooking(Stove fogon) throws RemoteException;
    public void CocinarPedido(Pedido order) throws RemoteException;
    public void asignarFogon( Producto producto) throws RemoteException;
    public void mostrarPedido(entidades.Stove stove) throws RemoteException;
    public QueueList getClientesVip() throws RemoteException;
    public QueueList getClientesNoVip() throws RemoteException;
}
