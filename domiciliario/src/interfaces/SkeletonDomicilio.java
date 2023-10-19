package interfaces;

import java.rmi.RemoteException;

import entidades.Pedido;
import entidades.estructuras.nodes.DoubleLinkedNode;
import entidades.estructuras.priorityQueue.PriorityQueueList;

public class SkeletonDomicilio  extends Remote{
    
    
    public DoubleLinkedNode<Pedido> obtenerPedido(int numeroPedido) throws RemoteException;

    void marcarDomicilioEnCamino(int numeroPedido) throws RemoteException;

    public PriorityQueueList<Pedido> buscarPedidosEnZona(String zona) throws RemoteException;

    
}
