package entidades.estructuras.priorityQueue;

import java.io.Serializable;

import entidades.estructuras.queue.QueueList;

public class PriorityQueueList<T extends Serializable> {
    QueueList[] colaPrioridad;

    public int absoluteSize;

    public PriorityQueueList() {
        
    }

    public PriorityQueueList(int absoluteSize) {
        this.absoluteSize = absoluteSize;
        colaPrioridad = new QueueList[absoluteSize];
        for (int i = 0; i < colaPrioridad.length; i++) {
            colaPrioridad[i] = new QueueList();
        }
    }

    public boolean add(int indice, T objecto) {
        try {
            if(indice < absoluteSize){
                colaPrioridad[indice].push(objecto);
            }
        } catch (Exception e) {
            System.out.println("Fallo el add(indice, objecto)");
        }
        return true;
    }

    public <T> Object pop(){
        try {
            for (int i = 0; i < colaPrioridad.length; i++) {
                if(!colaPrioridad[i].isEmpty()){
                    if(!colaPrioridad[i].isEmpty()){
                        return colaPrioridad[i].pop();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Fallo el pop()");
        }
        return null;
    }

    public void print(){
        for (int i = 0; i < colaPrioridad.length; i++) {
            colaPrioridad[i].iterateStack();
        }
    }
}
