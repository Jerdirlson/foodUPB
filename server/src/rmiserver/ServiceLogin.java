package rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceLogin extends UnicastRemoteObject implements SkeletonLogin{

    private static final long serialVersionUID = 123L;

    protected ServiceLogin() throws RemoteException {
        super();
        //TODO Auto-generated constructor stub
    }

    @Override
    public void inicializar() throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inicializar'");
    }
    
}
