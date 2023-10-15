package Model;

import java.util.Iterator;
import java.util.concurrent.Semaphore;
import javax.swing.JOptionPane;
import Estructuras.DoubleLinkedList1;
import Estructuras.NodeInterface;
import Estructuras.QueueList;

public class CocinaModel {
    private static Semaphore stoveSemaphore;
    private static QueueList<Order> ClientesNormales;
    private static QueueList<Order> ClientesVIP;
    static Stove[] stoves;
    protected static Object estadoLabel;

    public CocinaModel() {
        stoves = new Stove[16];
        stoveSemaphore = new Semaphore(16);
        ClientesNormales = new QueueList<>(); // Inicializa ClientesNormales
        ClientesVIP = new QueueList<>(); // Inicializa ClientesVIP

        for (int i = 0; i < 16; i++) {
            if (i < 4) {
                stoves[i] = new Stove("Cocción Lenta");
            } else {
                stoves[i] = new Stove("Cocción Rápida");
            }
        }

        // Agregar algunos pedidos de ejemplo
        Client client1 = new Client("Cliente 1", 0);
        Order order1 = new Order(1, client1);
        Producto producto1 = new Producto("Producto 1", 10, 1, 5);
        Producto producto2 = new Producto("Producto 2", 15, 2, 7);
        order1.getPedido().add(producto1);
        order1.getPedido().add(producto2);
        addOrder(order1);

        Client client2 = new Client("Cliente 2", 1);
        Order order2 = new Order(2, client2);
        Producto producto3 = new Producto("Producto 3", 12, 3, 6);
        order2.getPedido().add(producto3);
        addOrder(order2);
    }

    /*public void imprimirPedido(Order order) {
        if (order != null) {
            Iterator<NodeInterface<Producto>> iterador = order.getPedido().iterator();
            StringBuilder productos = new StringBuilder();
            while (iterador.hasNext()) {
                Producto producto = iterador.next().getObject();
                productos.append(producto.getNombre() + "\n");
            }
            String mensajeAdicional = "";
            if (todosProductosCoccionRapida(order)) {
                mensajeAdicional = "Todos los productos se pueden cocinar en este fogón.";
            }
            productos.append("\n" + mensajeAdicional);
    
            JOptionPane.showMessageDialog(null, productos.toString(), "Productos a preparar", JOptionPane.INFORMATION_MESSAGE);
        }
    }*/
    

    public void addOrder(Order order) {
        if (order.getClient().getPriority() == 0) {
            ClientesNormales.push(order);
        } else if (order.getClient().getPriority() == 1) {
            ClientesVIP.push(order);
        }
    }

    public boolean todosProductosCoccionRapida(Order order) {
        Iterator<NodeInterface<Producto>> iterador = order.getPedido().iterator();
        while (iterador.hasNext()) {
            Producto producto = iterador.next().getObject();
            if (producto.getTiempoDeCocion() > 10) {
                return false; // Si encuentra un producto de cocción lenta, retorna false
            }
        }
        return true; // Todos los productos son de cocción rápida
    }

    public void prepararPedido() {

        Order order = ClientesVIP.pop();

        if (order == null) {
            order = ClientesNormales.pop();
        }
        if (order != null) {
            Iterator<NodeInterface<Producto>> iterador = order.getPedido().iterator();
            StringBuilder productos = new StringBuilder();
            while (iterador.hasNext()) {
                Producto producto = iterador.next().getObject();
                productos.append(producto.getNombre() + "\n");
            }
            String mensajeAdicional = "";
            if (todosProductosCoccionRapida(order)) {
                mensajeAdicional = "Todos los productos se pueden cocinar en este fogón.";
            }
            productos.append("\n" + mensajeAdicional);
    
            JOptionPane.showMessageDialog(null, productos.toString(), "Productos a preparar", JOptionPane.INFORMATION_MESSAGE);
        }
        Stove stove = null;
        try {
            stoveSemaphore.acquire();
            for (Stove s : stoves) {
                if (s.isAvailable()) {
                    stove = s;
                    break;
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        // Simular la cocción de los productos
        stove.cook(order);

        // Liberar el semáforo
        stoveSemaphore.release();

    
    }

    public void finishCooking() {
        // Aquí puedes agregar lógica para finalizar la cocción si es necesario.
    }
}


class Stove {
    private String cookingType;
    private Order currentOrder;
    private boolean available;

    public Stove(String cookingType) {
        this.cookingType = cookingType;
        this.available = true;
    }

    public String getCookingType() {
        return cookingType;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void cook(Order order) {
        System.out.println("Cooking order " + order.getId() + " on stove " + this.cookingType);
        order.setCooked(true);
    }

    public void finishCooking() {
        // Aquí puedes agregar lógica para finalizar la cocción si es necesario.
    }
}

class Order {
    private int id;
    private Client client;
    private boolean isCooked;
    private DoubleLinkedList1<Producto> pedido;

    public Order(int id, Client client) {
        this.id = id;
        this.client = client;
        this.isCooked = false;
        this.pedido = new DoubleLinkedList1<>();
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public boolean getCooked() {
        return isCooked;
    }

    public void setCooked(boolean isCooked) {
        this.isCooked = isCooked;
    }

    public DoubleLinkedList1<Producto> getPedido() {
        return pedido;
    }
}

class Client {
    private String name;
    private int priority;

    public Client(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }
}

class Producto {
    private String nombre;
    private int tiempoDeCocion;
    private int idProducto;
    private int precio;

    public Producto(String nombre, int tiempoDeCocion, int idProducto, int precio) {
        this.nombre = nombre;
        this.tiempoDeCocion = tiempoDeCocion;
        this.idProducto = idProducto;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempoDeCocion() {
        return tiempoDeCocion;
    }

    public void setTiempoDeCocion(int tiempoDeCocion) {
        this.tiempoDeCocion = tiempoDeCocion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}

class Menu {
    public DoubleLinkedList1<Producto> Menu;

    public Menu(DoubleLinkedList1<Producto> Menu) {
        this.Menu = Menu;
    }

    public void agregarProducto(Producto producto) {
        Menu.add(producto);
    }

    public void eliminarProducto(Producto producto) {
        Menu.remove(producto);
    }

    public void buscarProducto(Producto producto) {
        if (Menu.contains(producto)) {
            System.out.println("El producto " + producto + " se encuentra en el menú.");
        } else {
            System.out.println("El producto " + producto + " no se encuentra en nuestro menú.");
        }
    }
}
