package Model;

import java.rmi.RemoteException;
import java.util.Iterator;

import javax.swing.JOptionPane;
import client.Client;
import entidades.estructuras.interfaces.node.NodeInterface;
import entidades.estructuras.queue.*;
import interfaces.SkeletonCocina;
import entidades.Pedido;
import entidades.Producto;
import entidades.Stove;

import app.ConfigLoader;

public class CocinaModel implements SkeletonCocina{
    private static QueueList<Producto> ClientesNormales;
    private static QueueList<Producto> ClientesVIP;
    public static Stove[] stoves;
    protected static Object estadoLabel;
    private static int fogonNumero;
    

    public CocinaModel() {
        stoves = new Stove[17];
        ClientesNormales = new QueueList<>(); // Inicializa ClientesNormales
        ClientesVIP = new QueueList<>(); // Inicializa ClientesVIP

        for (int i = 0; i < 17; i++) {
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
            //System.out.println("Esto es lo que trae getClientesVIP" + ClientesVIP.pop().getCliente().nombre_client);
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

    public static Producto getPedidoACocinar(){
        Producto pedidoActual;
        if(!ClientesVIP.isEmpty()){
            pedidoActual=ClientesVIP.pop();
        }else{
            pedidoActual=ClientesNormales.pop();
        }
        return pedidoActual;
    }
    @Override
    public void CocinarPedido(Producto order) throws RemoteException{
        
        String clientName = order.getUsuarioCliente().getNombre_client();
        System.out.println(order.getUsuarioCliente().getNombre_client());
        Client clienteCocina1= new Client(IP, PORT, SERVICENAMECOCINA);
       
        if(clienteCocina1.asignarFogon(order)==true){
            clienteCocina1.CocinarPedido(order);
            JOptionPane.showMessageDialog(null,"Cocinando pedido de: "+order.getUsuarioCliente().getNombre_client()+"'\n"+order.getNombre_producto());
        }else{
            JOptionPane.showMessageDialog(null, "Todos los fogones estan ocupados, por favor espere para poder cocinar el pedido"+ order.getNombre_producto()+" de "+order.getUsuarioCliente());
        }

        System.out.println("    El pedido llego hasta aqui" + order.getUsuarioCliente().getNombre_client()+order.getNombre_producto());
    }

    @Override
    public boolean asignarFogon( Producto producto) throws RemoteException{
        try{
         Client clienteCocina= new Client(IP, PORT, SERVICENAMECOCINA);
        clienteCocina.asignarFogon(producto);
        return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public void finishCooking(int numeroFogonDondeSeEstaCocinando) throws RemoteException {
         Client clienteCocina= new Client(IP, PORT, SERVICENAMECOCINA);
         clienteCocina.finishCooking(numeroFogonDondeSeEstaCocinando);
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
    

  public void mostrarPedidosPendientes(){

        StringBuilder string1= new StringBuilder();
        StringBuilder string2= new StringBuilder();

        
        //ClientesVIP.iterateStack();
        //ClientesNormales.iterateStack();


        JOptionPane.showMessageDialog(null, "Clientes VIP pendientes: " + ClientesVIP.size() +
                                            "\nClientes normales pendientes: " + ClientesNormales.size());
    }

    @Override
    public Producto getPedidosPreparandose(int fogonNumero){
        try {
            Client clienteCocina= new Client(IP, PORT, SERVICENAMECOCINA);
             stoves[fogonNumero].setPedidosPreparandose(clienteCocina.getPedidosPreparandose(fogonNumero));
             System.out.println(" ahora si se puso bien el pedido ");
             return clienteCocina.getPedidosPreparandose(fogonNumero);
        } catch (Exception e) {
            System.out.println("Error en el getPedidosPreparandose" + e.getMessage());
        return null;
        }
    }

    @Override
    public Stove[] getStoves() throws RemoteException {
        try {
            Client clienteCocina= new Client(IP, PORT, SERVICENAMECOCINA);
            stoves = clienteCocina.getStoves();
            return stoves;

        } catch (Exception e) {
            System.out.println("Error en getStoves " + e.getMessage());
            return null;
        }
    }



}