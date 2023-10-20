package entidades;

import java.io.Serializable;

import entidades.estructuras.doublee.linked.DoubleLinkedList;

public class Pedido implements Serializable{
    
    private static DoubleLinkedList<Producto> productos;
    private UserClient cliente;

    public Pedido(){
        
    }

    public static DoubleLinkedList getProductos() {
        return productos;
    }

    public void setProductos(DoubleLinkedList productos) {
        this.productos = productos;
    }

    public UserClient getCliente() {
        return cliente;
    }

    public void setCliente(UserClient cliente) {
        this.cliente = cliente;
    }

}
