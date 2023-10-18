package entidades.estructuras.nodes;

import java.io.Serializable;

public class SinglyLinkedNode<T extends Serializable> extends Node<T> {

    SinglyLinkedNode<T> next; //es el puntero que va señalando al siguiente
    
    public SinglyLinkedNode() {
        super();
        this.next = null;
    }

    public SinglyLinkedNode(T object) {
        super(object);
        this.next = null;
    }

    public SinglyLinkedNode(T object, SinglyLinkedNode<T> next) {
        super(object);
        this.next = next;
    }

    public SinglyLinkedNode<T> getNext() {
        return next;
    }

    public void setNext(SinglyLinkedNode<T> next) {
        this.next = next;
    }

}
