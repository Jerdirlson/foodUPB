package rmiserver;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SkeletonLogin extends Remote {

    public void inicializar() throws RemoteException;


}
