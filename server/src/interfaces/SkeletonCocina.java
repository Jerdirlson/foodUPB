package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;

import entidades.Pedido;
import entidades.Producto;
import entidades.Stove;
import entidades.estructuras.queue.QueueList;    
import entidades.estructuras.queue.QueueArray;   


public interface SkeletonCocina extends Remote{
    
    public void addOrder(Pedido order) throws RemoteException;
    void prepararPedido(Producto producto) throws RemoteException;
    void finishCooking(int numeroFogonDondeSeEstaCocinando) throws RemoteException;
    public void CocinarPedido(Producto order) throws RemoteException;
    public boolean asignarFogon( Producto producto) throws RemoteException;
    public void mostrarPedido(entidades.Stove stove) throws RemoteException;
    public QueueList getClientesVip() throws RemoteException;
    public QueueList getClientesNoVip() throws RemoteException;
    public Producto getPedidosPreparandose( int fogonNumero) throws RemoteException;
    public Stove[] getStoves() throws RemoteException;

}