package server;

import interfaces.SkeletonOperario;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import database.Conection;
import entidades.Producto;
import entidades.User;
import entidades.UserClient;;
public class ServiceOperario extends UnicastRemoteObject implements SkeletonOperario{

    protected ServiceOperario() throws RemoteException {
        super();
        //TODO Auto-generated constructor stub
    }

    @Override
    public User login(String email, String password) {
        User user = null;

        try {
            user = Conection.getUser(email, password);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

        return user;
    }

    @Override
    public UserClient getUserClient(String numCliente) throws RemoteException {
        UserClient userClient = null;

        try {
            userClient = Conection.getUserClient(numCliente);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

        return userClient;
    }

    @Override
    public Producto[] getProductos() throws RemoteException {
        Producto[] productos = null;
        try {
            productos = Conection.getProductos();
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return productos;
    }

    
}
