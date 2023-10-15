package src.Domiciliario;

import java.util.Timer;
import java.util.TimerTask;

import src.Estructuras.QueueList ;
import Model.CocinaModel;
import src.Estructuras.DoubleLinkedList;
 



public class Domiciliario {
    private QueueList<Pedido> pedidosParaLlevar;
    private long tiempoInicio;
    private final long TIEMPO_MAXIMO = 5 * 60 * 1000; // 5 minutos en milisegundos
    private final int MAX_PEDIDOS = 8;

    public Domiciliario() {
        pedidosParaLlevar = new LinkedList<>();
        tiempoInicio = 0;
    }

    public void agregarPedido(Pedido pedido) {
        if (pedidosParaLlevar.isEmpty()) {
            tiempoInicio = System.currentTimeMillis(); 
        }

        if (pedidosParaLlevar.size() < MAX_PEDIDOS) {
            pedidosParaLlevar.add(pedido);
        } else {
            System.out.println("Se han acumulado 8 pedidos. El domiciliario debe salir.");
        }

        if (System.currentTimeMillis() - tiempoInicio > TIEMPO_MAXIMO) {
            System.out.println("Se ha excedido el tiempo máximo de espera de 5 minutos. El domiciliario debe salir.");
        }
    }

    public void entregarPedidos() {
        pedidosParaLlevar.clear();
        tiempoInicio = 0;
    }
   }
