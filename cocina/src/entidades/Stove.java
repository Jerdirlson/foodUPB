package entidades;


import entidades.estructuras.queue.QueueArray;


 public class Stove {
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

    public void finishCooking() {
        // Aquí puedes agregar lógica para finalizar la cocción si es necesario.
    }

    public String getPedidoPreparandose(){
         return pedidosPreparandose.toString();
    }

    public void setPedidosPreparandose(Producto producto){
        this.pedidosPreparandose.push(producto);
    }

    public int getFogonNumero(){
        return this.fogonNumero;
    }
}

