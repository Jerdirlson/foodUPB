package Model;

import java.rmi.RemoteException;

import client.Client;
import entidades.estructuras.queue.*;
import interfaces.SkeletonCocina;
import entidades.Pedido;
import entidades.Producto;
import entidades.Stove;

import app.ConfigLoader;

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

    String IP = ConfigLoader.getProperty("IP_COCINA");
    String PORT = ConfigLoader.getProperty("PORT_COCINA");
    String SERVICENAMECOCINA = ConfigLoader.getProperty("SERVICENAMECOCINA");

    @Override
    public void addOrder(Pedido order) throws RemoteException {
        Client clienteCocina= new Client(IP, PORT, SERVICENAMECOCINA);
        clienteCocina.addOrder(order);
    }

    public QueueList getClientesVip(){
        try {
            Client clienteCocina= new Client(IP, PORT, SERVICENAMECOCINA);
            ClientesVIP = clienteCocina.getClientesVip();
            System.out.println("Esto es lo que trae getClientesVIP" + ClientesVIP.pop().getCliente().nombre_client);
            return ClientesVIP;
        } catch (Exception e) {
            System.out.println("Error en el getClientesVip " + e.getMessage());
            return null;
        }
    }

    public QueueList getClientesNoVip(){
        try {
            Client clienteCocina= new Client(IP, PORT, SERVICENAMECOCINA);
            ClientesNormales = clienteCocina.getClientesNoVip();
            return clienteCocina.getClientesNoVip();
        } catch (Exception e) {
            System.out.println("Error en el getClientesNoVip " + e.getMessage());
            return null;
        }
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
         Client clienteCocina= new Client(IP, PORT, SERVICENAMECOCINA);
        clienteCocina.CocinarPedido(order);
    }

    @Override
    public void asignarFogon( Producto producto) throws RemoteException{
         Client clienteCocina= new Client(IP, PORT, SERVICENAMECOCINA);
        clienteCocina.asignarFogon(producto);
    }

    @Override
    public void finishCooking(entidades.Stove stove) throws RemoteException {
         Client clienteCocina= new Client(IP, PORT, SERVICENAMECOCINA);
         clienteCocina.finishCooking(stove);
    }
        
    

    @Override
    public void prepararPedido(Producto producto) throws RemoteException {
         Client clienteCocina= new Client(IP, PORT, SERVICENAMECOCINA);
         clienteCocina.prepararPedido(producto);
    }
    @Override
    public void mostrarPedido(Stove stove) throws RemoteException {
        Client clienteCocina= new Client(IP, PORT, SERVICENAMECOCINA);
        clienteCocina.mostrarPedido(stove);
    }

    public Stove getStove(int fogonNumero){
        return CocinaModel.stoves[fogonNumero];
    }

    public void mostrarPedidosPendientes() {
        
        ClientesVIP.iterateStack();
        ClientesNormales.iterateStack();
    }

}

