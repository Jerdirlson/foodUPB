package entidades.estructuras.nodes;

import java.io.Serializable;

public class DoubleLinkedNode<T extends Serializable> extends Node<T> implements Serializable{

    DoubleLinkedNode<T> next;
    DoubleLinkedNode<T> previous;

    public DoubleLinkedNode() {
        super();
        this.next = null;
        this.previous = null;
    }

    public DoubleLinkedNode(T object) {
        super(object);
        this.next = null;
        this.previous = null;
    }

    public DoubleLinkedNode(T object, DoubleLinkedNode<T> next, DoubleLinkedNode<T> previous) {
        super(object);
        this.next = next;
        this.previous = previous;
    }

    public DoubleLinkedNode<T> getNext() {
        return next;
    }

    public DoubleLinkedNode<T> getPrevious() {
        return previous;
    }

    public void setNext(DoubleLinkedNode<T> next) {
        this.next = next;
    }

    public void setPrevious(DoubleLinkedNode<T> previous) {
        this.previous = previous;
    }

    public void add(T object) {
        DoubleLinkedNode<T> newNode = new DoubleLinkedNode<>(object);

        if (next == null) {
            next = newNode;
            previous = newNode;
        } else {
            previous.setNext(newNode);
            newNode.setPrevious(previous);
            next = newNode;
        }
    }
    
}
