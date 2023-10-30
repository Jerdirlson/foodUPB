package entidades;

import java.io.Serializable;

import entidades.estructuras.queue.QueueArray;


 public class Stove implements Serializable{
    private String cookingType;
    private boolean available;
    private QueueArray<Producto> pedidosPreparandose;
    private int fogonNumero;

    public Stove(String cookingType, int fogonNumero) {
        this.cookingType = cookingType;
        this.available = true;
        this.fogonNumero=fogonNumero;
        pedidosPreparandose= new QueueArray<>(1);
    }

    public String getCookingType() {
        return cookingType;
    }


    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void cook(Pedido order) {
        /*System.out.println("Cooking order " + order.getId() + " on stove " + this.cookingType);
        order.setCooked(true);*/
    }

    public Producto finishCooking() {
        return this.pedidosPreparandose.pop();
    }

    public Producto getPedidoPreparandose(){
        return pedidosPreparandose.peek()   ;
   }

    public void setPedidosPreparandose(Producto producto){
        pedidosPreparandose.push(producto);
        System.out.println("Esta haciendo bien el push");
    }

    public int getFogonNumero(){
        return this.fogonNumero;
    }
}

