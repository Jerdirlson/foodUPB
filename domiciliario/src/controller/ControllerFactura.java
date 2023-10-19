package controller;

import model.Persona;
import model.Producto;
import view.FacturaView;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.SwingUtilities;

import entidades.Pedido;
import entidades.UserClient;
import entidades.estructuras.doublee.circular.DoubleCircularList;
import interfaces.SkeletonDomicilio;

public class ControllerFactura implements SkeletonDomicilio {
    private SkeletonDomicilio service;
    private String ip;
    private String port;
    private String serviceName;
    private String url;
    private UserClient currentUser;

    public ControllerFactura(String ip, String port, String serviceName) {
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
    public void addProductoToPedido(entidades.Producto producto, Pedido pedido) throws RemoteException {
        try {
            service = (SkeletonDomicilio) Naming.lookup(url);
            service.addProductoToPedido(producto, pedido);
        } catch (Exception e) {
            System.err.println("Error en enviar el pedido");
            e.printStackTrace();
            throw new UnsupportedOperationException("Unimplemented method 'addProductoToPedido'");
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
        } // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularTotalPorPedido'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generarFactura'");
    }

    @Override
    public double getMontoTotal() throws RemoteException {
        try {
            service = (SkeletonDomicilio) Naming.lookup(url);
            service.getMontoTotal();
        } catch (Exception e) {
            System.err.println("Error en enviar el pedido");
            e.printStackTrace();
        } // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMontoTotal'");
    }

    @Override
    public int getCostoDomicilio(UserClient user) throws RemoteException {
        try {
            service = (SkeletonDomicilio) Naming.lookup(url);
            service.getCostoDomicilio(user);
        } catch (Exception e) {
            System.err.println("Error en enviar el pedido");
            e.printStackTrace();
        } // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCostoDomicilio'");
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
        {
            return getCurrentUser();
        }
    }
}
