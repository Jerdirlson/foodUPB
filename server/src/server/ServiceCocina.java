package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;


import entidades.Pedido;
import entidades.Producto;
import entidades.estructuras.interfaces.node.NodeInterface;
import entidades.estructuras.queue.QueueList;
import interfaces.SkeletonCocina;
import entidades.Stove;

public class ServiceCocina extends UnicastRemoteObject implements SkeletonCocina{

    private static QueueList<Pedido> ClientesNormales;
    private static QueueList<Pedido> ClientesVIP;
    static Stove[] stoves;
    protected static Object estadoLabel;
    private static int fogonNumero;

    protected ServiceCocina() throws RemoteException {
        super();
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
        System.out.println("ESTO ES LO QUE LLEGA EN VIP " + order.getCliente().getVip());
         if (order.getCliente().getVip() == true) {
            System.out.println("LLEGAA HASTA AQUIIIIIII");
            ClientesVIP.push(order);
        } else  {
            ClientesNormales.push(order);
        }
    }

   public void CocinarPedido(Pedido order) throws RemoteException{
        try {
            Iterator<NodeInterface<Producto>> iterador = order.getProductos().iterator();
            while(iterador.hasNext()) {
                asignarFogon(iterador.next().getObject());
            }
            
        } catch (Exception e) {
            System.out.println("El error esta en CocinarPedido " + e.getMessage());
        }
    }
    


    public void asignarFogon( Producto producto) throws RemoteException{
        if(producto.tiempoDeCocion>=20){
            for(int i=0; i < 4;i++){
                if(stoves[i].isAvailable()==true){
                    stoves[i].setPedidosPreparandose(producto);
                    producto.setNumeroFogonDondeSeEstaCocinando(i);
                }
            }
        }
        else{
            for(int i=4; i < 16;i++){
                if(stoves[i].isAvailable()==true){
                    stoves[i].setPedidosPreparandose(producto);
                }
            }

        }


    }


    //Preguntarle a anagie porque esto no esta igual que en la cocina
    @Override
    public void finishCooking(Stove producto) throws RemoteException {
    //     int i=producto.getNumeroFogonDondeSeEstaCocinando();
    //     stoves[i].setAvailable(true);
    }

    @Override
    public void prepararPedido(Producto producto) throws RemoteException {
       int i=producto.getNumeroFogonDondeSeEstaCocinando();
        stoves[i].setAvailable(false); 
    }

    public void mostrarPedido(entidades.Stove stove) throws RemoteException{
        stove.getPedidoPreparandose();
    }

    public static Pedido getPedidoACocinar() throws RemoteException{
        Pedido pedidoActual;
        if(!ClientesVIP.isEmpty()){
            pedidoActual=ClientesVIP.pop();
        }else{
            pedidoActual=ClientesNormales.pop();
        }
        return pedidoActual;
    }

    @Override
    public QueueList getClientesVip() throws RemoteException {
        try {
            return ClientesVIP;
        } catch (Exception e) {
            System.out.println("Error en getCLientesVip serviceCocina: " + e.getMessage());
            return null;
        }
    }

    @Override
    public QueueList getClientesNoVip() throws RemoteException {
        try {
            return ClientesNormales;
        } catch (Exception e) {
            System.out.println("Error en getCLientesVip serviceCocina: " + e.getMessage());
            return null;
        }
    }
    
}


    
    
    

