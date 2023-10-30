package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;



import entidades.Pedido;
import entidades.Producto;
import entidades.estructuras.interfaces.node.NodeInterface;
import entidades.estructuras.nodes.DoubleLinkedNode;
import entidades.estructuras.queue.QueueList;
import interfaces.SkeletonCocina;
import entidades.Stove;

public class ServiceCocina extends UnicastRemoteObject implements SkeletonCocina{

    private static QueueList<Producto> ClientesNormales;
    private static QueueList<Producto> ClientesVIP;
    static Stove[] stoves;
    protected static Object estadoLabel;
    private static int fogonNumero;

    protected ServiceCocina() throws RemoteException {
        super();
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

    @Override
    public void addOrder(Pedido order) throws RemoteException {
        System.out.println("ESTO ES LO QUE LLEGA EN VIP " + order.getCliente().getVip());
         Iterator<NodeInterface<Producto>> iterador = order.getProductos().iterator();
         DoubleLinkedNode<Producto> currentNode ;
         while(iterador.hasNext()) {
             currentNode = (DoubleLinkedNode<Producto>) iterador.next();
             currentNode.getObject().setUsuarioCliente(order.getCliente());
         if (order.getCliente().getVip() == true) {
            ClientesVIP.push(currentNode.getObject());
            System.out.println(ClientesVIP.size());
            System.out.println("SI HACE EL PUSH");      
              
        } else  {
            ClientesNormales.push(currentNode.getObject());
        }
    }
    }
      @Override
       public void CocinarPedido(Producto order) throws RemoteException{
       System.out.println(order.getUsuarioCliente().getNombre_client());
             
            asignarFogon(order);
            stoves[order.getNumeroFogonDondeSeEstaCocinando()].setAvailable(false);
             if(order.getUsuarioCliente().getVip() == true) {
                ClientesVIP.pop();
            }else{
                ClientesNormales.pop();
            }
            
        } 
    

        public boolean asignarFogon(Producto currentProducto) throws RemoteException {
            System.out.println("Antes de entrar al if");
            if (currentProducto.tiempoDeCocion >= 20) {
                System.out.println("Entro al if");
                for (int i = 1; i < 5; i++) {
                    if (i < stoves.length && stoves[i].isAvailable()) {
                        System.out.println("ENTRA AL IF DEL PRIMER FOR");
                        stoves[i].setPedidosPreparandose(currentProducto);
                        System.out.println("El pedido se asigno en el fogon " + i + currentProducto.getNombre_producto());
                        currentProducto.setNumeroFogonDondeSeEstaCocinando(i);
                        System.out.println(currentProducto.getNumeroFogonDondeSeEstaCocinando());
                        return true;
                    }
                }
            } else if(currentProducto.tiempoDeCocion < 20) {
                System.out.println("ENTRA AL ELSE");
                for (int i = 5; i < 17; i++) {
                    System.out.println("ENTRA AL FOR DEL ELSE");
                    if (stoves[i].isAvailable()) {
                        System.out.println("ENTRA AL IF DEL FOR DEL ELSE");
                        stoves[i].setPedidosPreparandose(currentProducto);
                        
                        System.out.println("El pedido se asigno en el fogon " + i + getPedidosPreparandose(i).nombre_producto);
                        currentProducto.setNumeroFogonDondeSeEstaCocinando(i);
                        System.out.println(i);
                        System.out.println(stoves[i].isAvailable());

                        return true;
                        
                    }

                }
            }
            return false;
        }

   /*  @Override
    public boolean asignarFogon(Producto currentProducto) throws RemoteException {
        if (currentProducto.tiempoDeCocion >= 20) {
            for (int i = 1; i < 5; i++) {
                if (stoves[i].isAvailable()) {
                    stoves[i].setPedidosPreparandose(currentProducto);
                    stoves[i].setAvailable(false);
                    currentProducto.setNumeroFogonDondeSeEstaCocinando(i);
                    return true;  
                }
            }
        } else {
            for (int i = 5; i < 17; i++) {
                if (stoves[i].isAvailable()) {
                    stoves[i].setPedidosPreparandose(currentProducto);
                    currentProducto.setNumeroFogonDondeSeEstaCocinando(i);
                    return true;  
                }
            }
        }
        return false;  // No se asignó a ningún fogón. 
    }*/
         
   
  
    @Override
    public void finishCooking(int numeroFogonDondeSeEstaCocinando) throws RemoteException {
    
            stoves[numeroFogonDondeSeEstaCocinando].setAvailable(true);
            Producto productoTerminado = stoves[numeroFogonDondeSeEstaCocinando].finishCooking();
            System.out.println(" El estado de esta estufa es: "+  stoves[numeroFogonDondeSeEstaCocinando].isAvailable());

            ServiceDomicilio.pushProducto(productoTerminado);
    }

    @Override
    public void prepararPedido(Producto producto) throws RemoteException {
    }

    public void mostrarPedido(entidades.Stove stove) throws RemoteException{
        stove.getPedidoPreparandose();
    }

    public static Producto getPedidoACocinar() throws RemoteException{
        Producto pedidoActual;
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