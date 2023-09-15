package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import entidades.User;

public interface SkeletonOperario extends Remote{
    
    public User login(String email, String password) throws RemoteException;
    
}
