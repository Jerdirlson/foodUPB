package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import entidades.Producto;
import entidades.User;

public interface SkeletonDomicilio extends Remote {
    String registrarPedido(String datos) throws RemoteException;
    String consultarEstadoPedido(int numeroPedido) throws RemoteException;
}