package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import entidades.Pedido;
import entidades.Producto;
import entidades.UserClient;
import interfaces.SkeletonDomicilio;

public class ClienteDomicilio implements SkeletonDomicilio {

    private SkeletonDomicilio service;
    private String ip;
    private String port;
    private String serviceName;
    private String url;

    public ClienteDomicilio(String ip, String port, String serviceName) {
        this.service = null;
        this.ip = ip;
        this.port = port;
        this.serviceName = serviceName;
        this.url = "rmi://" + ip + ":" + port + "/" + serviceName;
    }

    @Override
    public void addPedido(UserClient cliente) throws RemoteException {
        try {
            service = (SkeletonDomicilio) Naming.lookup(url);
            service.addPedido(cliente);
        } catch (Exception e) {
            System.err.println("Error en enviar el pedido");
            e.printStackTrace();
        }
    }

    @Override

    public void addProductoToPedido(Producto producto, Pedido pedido) throws RemoteException {
        try {
            service = (SkeletonDomicilio) Naming.lookup(url);
            service.addProductoToPedido(producto, pedido);
        } catch (Exception e) {
            System.err.println("Error en enviar el pedido");
            e.printStackTrace();
        }
    }

    @Override
    public double calcularTotalPorPedido(Pedido pedido) throws RemoteException {

        try {
            service = (SkeletonDomicilio) Naming.lookup(url);
            service.calcularTotalPorPedido(pedido);
        } catch (Exception e) {
            System.err.println("Error en enviar el pedido");
            e.printStackTrace();
        }
        return 0;
    }

    @Override

    public void generarFactura(Pedido pedido) throws RemoteException {

        try {
            service = (SkeletonDomicilio) Naming.lookup(url);
            service.generarFactura(pedido);
        } catch (Exception e) {
            System.err.println("Error en enviar el pedido");
            e.printStackTrace();
        }
    }

    @Override
    public double getMontoTotal() throws RemoteException {
        try {
            service = (SkeletonDomicilio) Naming.lookup(url);
            service.getMontoTotal();
        } catch (Exception e) {
            System.err.println("Error en enviar el pedido");
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getCostoDomicilio(UserClient user) throws RemoteException {

        try {
            service = (SkeletonDomicilio) Naming.lookup(url);
            service.getCostoDomicilio(user);
        } catch (Exception e) {
            System.err.println("Error en enviar el pedido");
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public UserClient getCurrentUser() throws RemoteException {
        try {
            service = (SkeletonDomicilio) Naming.lookup(url);
            service.getCurrentUser();
        } catch (Exception e) {
            System.err.println("Error en enviar el pedido");
            e.printStackTrace();
        }
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentUser'");
    }

}
