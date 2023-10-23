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
    private static QueueList<Pedido> ClientesNormales;
    private static QueueList<Pedido> ClientesVIP;
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
        String clientName = order.getCliente().getNombre_client();
        System.out.println(order.getCliente().getNombre_client());
        StringBuilder string1= new StringBuilder();
        Client clienteCocina= new Client(IP, PORT, SERVICENAMECOCINA);
        clienteCocina.CocinarPedido(order);
        Iterator<NodeInterface<Producto>> iterador = order.getProductos().iterator();
        while(iterador.hasNext()){
            string1.append(iterador.next().getObject().nombre_producto+ "\n");
        }

        String mensaje = "Pedido a cocinar del usuario " + clientName + ":\n" + string1.toString();

        System.out.println("    El pedido llego hasta aqui" + order.getProductos().size());
          JOptionPane.showMessageDialog(null,mensaje);
    }

    @Override
    public void asignarFogon( Producto producto) throws RemoteException{
        try{
         Client clienteCocina= new Client(IP, PORT, SERVICENAMECOCINA);
        clienteCocina.asignarFogon(producto);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Todos los fogones estan llenos, por favor espere para poder cocinar el producto "+ producto );
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