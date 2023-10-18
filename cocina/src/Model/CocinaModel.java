package Model;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.concurrent.Semaphore;
import javax.swing.JOptionPane;

import Client.ClientCocina;
import entidades.estructuras.interfaces.linkedlist.*;
import entidades.estructuras.interfaces.node.*;
import entidades.estructuras.queue.*;
import entidades.Pedido;
import entidades.Producto;
import entidades.UserClient;
import Interfaces.SkeletonCocina;
import entidades.Stove;

public class CocinaModel implements SkeletonCocina{
    private static QueueList<Pedido> ClientesNormales;
    private static QueueList<Pedido> ClientesVIP;
    static Stove[] stoves;
    protected static Object estadoLabel;
    private static int fogonNumero;

    public CocinaModel() {
        stoves = new Stove[16];
        ClientesNormales = new QueueList<>(); // Inicializa ClientesNormales
        ClientesVIP = new QueueList<>(); // Inicializa ClientesVIP

        for (int i = 0; i < 16; i++) {
            if (i < 4) {
                stoves[i] = new Stove("Cocción Lenta",(fogonNumero=i));
               
            } else {
                stoves[i] = new Stove("Cocción Rápida",fogonNumero=i);
        
            }
        }
    }
    @Override
    public void addOrder(Pedido order) throws RemoteException {
        ClientCocina clienteCocina= new ClientCocina("localhost", "5000", "serviceCocina");
        clienteCocina.addOrder(order);
    }

    public static Pedido getPedidoACocinar(){
        Pedido pedidoActual;
        if(!ClientesVIP.isEmpty()){
            pedidoActual=ClientesVIP.pop();
        }else{
            pedidoActual=ClientesNormales.pop();
        }
        return pedidoActual;
    }
    @Override
    public void CocinarPedido(Pedido order) throws RemoteException{
         ClientCocina clienteCocina= new ClientCocina("localhost", "5000", "serviceCocina");
        clienteCocina.CocinarPedido(order);
    }

    @Override
    public void asignarFogon( Producto producto) throws RemoteException{
         ClientCocina clienteCocina= new ClientCocina("localhost", "5000", "serviceCocina");
        clienteCocina.asignarFogon(producto);
    }

    @Override
    public void finishCooking(entidades.Stove stove) throws RemoteException {
         ClientCocina clienteCocina= new ClientCocina("localhost", "5000", "serviceCocina");
         clienteCocina.finishCooking(stove);
    }
        
    

    @Override
    public void prepararPedido(Producto producto) throws RemoteException {
         ClientCocina clienteCocina= new ClientCocina("localhost", "5000", "serviceCocina");
         clienteCocina.prepararPedido(producto);
    }
    @Override
    public void mostrarPedido(Stove stove) throws RemoteException {
        ClientCocina clienteCocina= new ClientCocina("localhost", "5000", "serviceCocina");
        clienteCocina.mostrarPedido(stove);
    }

    public Stove getStove(int fogonNumero){
        return CocinaModel.stoves[fogonNumero];
    }

}

