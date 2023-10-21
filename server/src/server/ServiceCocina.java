package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import entidades.Pedido;
import entidades.Producto;
import entidades.estructuras.interfaces.node.NodeInterface;
import entidades.estructuras.nodes.DoubleLinkedNode;
import entidades.estructuras.queue.QueueArray;
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
            ClientesVIP.push(order);
            System.out.println(ClientesVIP.size());
            System.out.println("SI HACE EL PUSH");        
        } else  {
            ClientesNormales.push(order);
        }
    }

   public void CocinarPedido(Pedido order) throws RemoteException{
        try {
            Iterator<NodeInterface<Producto>> iterador = order.getProductos().iterator();
            DoubleLinkedNode<Producto> currentNode ;
            int count = 0;
            while(iterador.hasNext()) {
                System.out.println("Iteracion " + count + 1);
                currentNode = (DoubleLinkedNode<Producto>) iterador.next();
                System.out.println("currenNode : " + currentNode.getObject().getNombre_producto());
                asignarFogon(currentNode.getObject());
                // System.out.println("Entra aqui" + currentNode.getObject().getNombre_producto());
            }
            
            if(order.getCliente().getVip() == true) {
                ClientesVIP.pop();
            }else{
                ClientesNormales.pop();
            }
            
        } catch (Exception e) {
            System.out.println("El error esta en CocinarPedido " + e.getMessage());
        }
    }
    


    public void asignarFogon( Producto currentProducto) throws RemoteException{
        System.out.println("Antes de entrar al if");
        if(currentProducto.tiempoDeCocion>=20){
            System.out.println("Entro al if");
            for(int i=0; i < 4;i++){
                if(stoves[i].isAvailable()==true){
                    System.out.println("ENTRA EN EL 1 FOR" +stoves[i].getPedidoPreparandose().nombre_producto);
                   stoves[i].setPedidosPreparandose(currentProducto);
                         currentProducto.setNumeroFogonDondeSeEstaCocinando(i);
                    break;
                }
            }
        }
        else{
            System.out.println("ENTRA AL ELSE`");
            for(int i=4; i < 16;i++){
                System.out.println("ENTRA AL FOR DEL ELSE");
                if(stoves[i].isAvailable()==true){
                    System.out.println("ENTRA AL IF DEL FOR DEL ELSE");
                    stoves[i].setPedidosPreparandose(currentProducto);
                    stoves[i].setAvailable(false);
                    System.out.println("El pedido se asigno en el fogon "+ i + getPedidosPreparandose(i).nombre_producto);
                              currentProducto.setNumeroFogonDondeSeEstaCocinando(i);
                       break;
                }
            }

        }


    }


    //Preguntarle a anagie porque esto no esta igual que en la cocina
    @Override
    public void finishCooking(int numeroFogonDondeSeEstaCocinando) throws RemoteException {
    
            stoves[numeroFogonDondeSeEstaCocinando].setAvailable(true);
            stoves[numeroFogonDondeSeEstaCocinando].finishCooking();
    //     stoves[i].setAvailable(true);
    }

    @Override
    public void prepararPedido(Producto producto) throws RemoteException {
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

    @Override
    public Producto getPedidosPreparandose(int fogonNumero) throws RemoteException {
            try {
                return stoves[fogonNumero].getPedidoPreparandose(); 
            
            } catch (Exception e) {
                System.out.println("Error en el getClientesNoVip " + e.getMessage());
                return null;
            }
    }

    @Override
    public Stove[] getStoves() throws RemoteException {
        try {
            return stoves;
        } catch (Exception e) {
            System.out.println("Error en getStoves Server " + e.getMessage());
            return null;
        }
    }
}

    
    
    

