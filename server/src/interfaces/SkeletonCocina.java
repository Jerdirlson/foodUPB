package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import entidades.Pedido;
import entidades.Stove;
import entidades.Producto;
    


public interface SkeletonCocina extends Remote{
    
    void addOrder(Pedido order) throws RemoteException;
    void prepararPedido(Producto producto) throws RemoteException;
    void finishCooking(Producto producto) throws RemoteException;
    public void CocinarPedido(Pedido order) throws RemoteException;
    public void asignarFogon( Producto producto) throws RemoteException;
    public void mostrarPedido(entidades.Stove stove) throws RemoteException;
    

    
}
