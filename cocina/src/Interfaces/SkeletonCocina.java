package Interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import entidades.Stove;

import entidades.Pedido;
import entidades.Producto;
    


public interface SkeletonCocina extends Remote{
    
    void addOrder(Pedido order) throws RemoteException;
    void prepararPedido(Producto producto) throws RemoteException;
    void finishCooking(Stove stove) throws RemoteException;
    public void CocinarPedido(Pedido order ) throws RemoteException;
    public void asignarFogon( Producto producto) throws RemoteException;
    public void mostrarPedido(entidades.Stove stove) throws RemoteException;

    
}

    

