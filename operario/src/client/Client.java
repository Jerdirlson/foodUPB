package client;

import java.io.Serializable;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import entidades.Producto;
import entidades.User;
import entidades.UserClient;
import interfaces.SkeletonOperario;

public class Client implements SkeletonOperario{

    private SkeletonOperario service;
    private String ip;
    private String port;
    private String serviceName;
    private String url;

    public Client(String ip, String port, String serviceName){
        this.service = null;
        this.ip = ip;
        this.port = port;
        this.serviceName = serviceName;
        this.url = "rmi://" + ip + ":" + port + "/" + serviceName;
    }

    @Override
    public User login(String email, String password) throws RemoteException {
        User user = new User();
        try{
            service = (SkeletonOperario) Naming.lookup(url);
            return service.login( email, password);
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
            return user;
        }
    }

        @Override
    public UserClient getUserClient(String numCliente) throws RemoteException {
        UserClient userClient = new UserClient();
        try{
            service = (SkeletonOperario) Naming.lookup(url);
            return service.getUserClient(numCliente);
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
            return userClient;
        }
    }

    public boolean isConnected() throws RemoteException{
        try{
            System.out.println(url);
            service = (SkeletonOperario) Naming.lookup(url);
            return true;
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Producto[] getProductos() throws RemoteException {
        try{
            service = (SkeletonOperario) Naming.lookup(url);
            return service.getProductos();
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
