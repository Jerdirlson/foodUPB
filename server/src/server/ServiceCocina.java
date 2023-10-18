package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.concurrent.Semaphore;

import javax.swing.JOptionPane;

import app.ConfigLoader;
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

    protected ServiceCocina() throws RemoteException {
        super();
        //TODO Auto-generated constructor stub
    }

    @Override
    public void addOrder(Pedido order) throws RemoteException {
         if (order.getCliente().getVip() == true) {
            ClientesVIP.push(order);
            ClientesNormales.push(order);
        } else  {
            ClientesNormales.push(order);
        }
        throw new UnsupportedOperationException("Unimplemented method 'addOrder'");
    }

   public void CocinarPedido(Pedido order) throws RemoteException{
        Iterator<NodeInterface<Producto>> iterador = order.getProductos().iterator();
        while(iterador.hasNext()) {
            asignarFogon(iterador.next().getObject());
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


    @Override
    public void finishCooking(Producto producto) throws RemoteException {
        int i=producto.getNumeroFogonDondeSeEstaCocinando();
        stoves[i].setAvailable(true);
        
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
    
}


    
    
    

