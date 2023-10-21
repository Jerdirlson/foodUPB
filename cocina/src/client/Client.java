package client;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Model.CocinaModel;
import entidades.Pedido;
import entidades.Producto;
import entidades.Stove;
import entidades.estructuras.queue.QueueArray;
import entidades.estructuras.queue.QueueList;
import interfaces.SkeletonCocina;

public class Client implements SkeletonCocina{

    private SkeletonCocina service;
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
    public void addOrder(Pedido order) throws RemoteException {

         try {
            service = (SkeletonCocina) Naming.lookup(url);
            service.addOrder(order);
        } catch (Exception e) {
            System.err.println("Error en enviar el pedido1");
            e.printStackTrace();
        }                                                     
        
    }

    @Override
    public void prepararPedido(Producto producto ) throws RemoteException {
        try {
            service = (SkeletonCocina) Naming.lookup(url);
            service.prepararPedido(producto);
        } catch (Exception e) {
            System.err.println("Error en enviar el pedido");
            e.printStackTrace();
        } 
    }

    @Override
    public void finishCooking(Stove stove) throws RemoteException {
        try {
            service = (SkeletonCocina) Naming.lookup(url);
            service.finishCooking(stove);
        } catch (Exception e) {
            System.err.println("Error en enviar el pedido");
            e.printStackTrace();
        } 
    }

    @Override
    public void CocinarPedido(Pedido order) throws RemoteException {
        try {
        System.out.println(url);
            System.out.println("LLEGO HASTA AQUI");
            service = (SkeletonCocina) Naming.lookup(url);
            service.CocinarPedido(order);
        } catch (Exception e) {
            System.err.println("Error en enviar el pedido");
            e.printStackTrace();
        } 
    }

    @Override
    public void asignarFogon(Producto producto) throws RemoteException {
        try {
            service = (SkeletonCocina) Naming.lookup(url);
            service.asignarFogon(producto);
        } catch (Exception e) {
            System.err.println("Error en enviar el pedido");
            e.printStackTrace();
        } 
    }

    public void mostrarPedido(entidades.Stove stove){
    try {
            service = (SkeletonCocina) Naming.lookup(url);
            service.mostrarPedido(stove);
        } catch (Exception e) {
            System.err.println("Error en enviar el pedido");
            e.printStackTrace();
        } 
    }

    public boolean isConnected() throws RemoteException{
        try{
            System.out.println(url);
            service = (SkeletonCocina) Naming.lookup(url);
            return true;
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public QueueList getClientesVip() throws RemoteException {
        try{
            service = (SkeletonCocina) Naming.lookup(url);
            return service.getClientesVip();
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            System.out.println("Error en getClientesVip() : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public QueueList getClientesNoVip() throws RemoteException {
        try{
            service = (SkeletonCocina) Naming.lookup(url);
            return service.getClientesNoVip();
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            System.out.println("Error en getClientesNoVip() : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


}
