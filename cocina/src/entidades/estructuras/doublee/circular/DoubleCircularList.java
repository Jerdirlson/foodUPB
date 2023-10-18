package entidades.estructuras.doublee.circular;

import java.io.Serializable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import entidades.estructuras.interfaces.linkedlist.LinkedListInterface;
import entidades.estructuras.interfaces.node.NodeInterface;
import entidades.estructuras.nodes.DoubleLinkedNode;


public class DoubleCircularList<T extends Serializable> implements LinkedListInterface<T>, Serializable{

    private DoubleLinkedNode<T> head;
    private DoubleLinkedNode<T> tail;
    private int size;

    @Override       //Sirve
    public boolean add(T object) {
        boolean bool = false;
        try {
            if(object != null){
                if(isEmpty()){
                    head = tail = new DoubleLinkedNode<T>(object);
                    tail.setNext(head);
                    head.setPrevious(tail);
                }else{
                    tail.setNext(new DoubleLinkedNode<T>(object));
                    tail.getNext().setPrevious(tail);
                    tail = tail.getNext();
                    tail.setNext(head);
                    head.setPrevious(tail);

                }
                size++;
                bool = true;
            }
        } catch (Exception e) {
            System.out.println("Fallo el add(object)");
        }
    return bool;
    } 

    @Override       //Sirve
    public boolean add(NodeInterface<T> node, T object) {
        boolean bool = false;
        try {
            if(object != null){
                if(!isEmpty()){
                    Iterator iterator = iterator();
                    DoubleLinkedNode<T> currentNode;
                    DoubleLinkedNode<T> newNode = new DoubleLinkedNode<T>(object);
                    while(iterator.hasNext()){
                        currentNode = (DoubleLinkedNode<T>)iterator.next();
                        if(currentNode.getObject().equals(node.getObject())){
                            if(currentNode == tail){
                                tail.setNext(newNode);
                                newNode.setPrevious(tail);
                                tail = newNode;
                                tail.setNext(head);
                                head.setPrevious(tail);
                                bool = true;
                                size++;
                                break;
                            }else{
                            newNode.setNext(currentNode.getNext());
                            newNode.setPrevious(currentNode);
                            newNode.getNext().setPrevious(newNode);
                            currentNode.setNext(newNode);
                            bool = true;
                            size++;
                            break;
                            }
                        }
                    }
                }else
                    bool = false;
            }
        } catch (Exception e) {
            bool = false;
            System.out.println("Fallo el add(node, object)");
        }
        return bool;
    }

    @Override       //Sirve
    public boolean add(NodeInterface<T> node, NodeInterface<T> next) {
        boolean bool = false;
        try {
            if(contains(node.getObject())){
                DoubleLinkedNode<T> snode = (DoubleLinkedNode<T>)node;
                DoubleLinkedNode<T> snext = (DoubleLinkedNode<T>)next;
                if (snode == tail){
                    tail = snext;
                }
                Iterator iterator = iterator();
                while(iterator.hasNext()){
                    DoubleLinkedNode<T> currentNode = (DoubleLinkedNode<T>)iterator.next();
                    if(currentNode.getObject().equals(snode.getObject())){
                        if (currentNode == tail){
                            tail.setNext(snext);
                            snext.setPrevious(tail);
                            tail = snext;
                            tail.setNext(head);
                            head.setPrevious(tail);
                            bool = true;
                            size++;
                            break;
                        }
                        snode = currentNode;
                        snext.setNext(snode.getNext());
                        snext.setPrevious(snode);
                        snext.getNext().setPrevious(snext);
                        snode.setNext(snext);
                        bool = true;
                        size++;
                        break;
                    }
                
                }
            }
        } catch (Exception e) {
            System.out.println("Fallo el add(node, next)");
            bool = false;
        }
        return bool;
    }

    @Override       //Sirve
    public boolean add(T[] objects) {
        boolean bool = false;
        try {
            for (T object : objects) {
                bool = add(object);
            }
        } catch (Exception e) {
            System.out.println("Fallo el add(objects)");
            bool = false;
        }
    return bool;
    }

    @Override       //Sirve
    public boolean add(NodeInterface<T> node, T[] objects) {
        boolean bool = true;
        try {
            if(contains(node.getObject())){
                for (int i = objects.length - 1; i >= 0; i--) {
                    bool = add(node, objects[i]);
                }

            }
        } catch (Exception e) {
            System.out.println("Fallo el add(node, objects)");
            bool = false;
        }
        return bool;
    }

    @Override       //Sirve
    public boolean addOnHead(T object) {
        boolean bool = false;
        try {
            if(isEmpty()){
                head = tail = new DoubleLinkedNode<T>(object);
            }else{
                DoubleLinkedNode<T> newNode = new DoubleLinkedNode<T>(object);
                newNode.setNext(head);
                head.setPrevious(newNode);
                head = newNode;
                head.setPrevious(tail);
                tail.setNext(head);
            }
            size++;
            bool = true;
        } catch (Exception e) {
            System.out.println("Fallo el addOnHead(object)");
            bool = false;
        }
        return bool;
    }

    @Override       //Sirve
    public boolean addOnHead(T[] objects) {
        boolean bool = false;
        try {
            for (int i = objects.length - 1; i >= 0; i--) {
                bool = addOnHead(objects[i]);
               }
        } catch (Exception e) {
            System.out.println("Fallo el addOnHead(objects)");
            bool = false;
        }
        return bool;
    }

    @Override       //Sirve
    public boolean clear() {
        try {
            head = tail = null;
            size = 0;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override       //Sirve
    public LinkedListInterface<T> cloneList() {
        try {
            DoubleCircularList<T> clone = new DoubleCircularList<T>();
            DoubleLinkedNode<T> currentNode;
            Iterator iterator = iterator();
            while(iterator.hasNext()){
                currentNode = (DoubleLinkedNode<T>)iterator.next();
                clone.add(currentNode.getObject());
            }
            return clone;
        } catch (Exception e) {
            return null;
        }
    }

    @Override       //Sirve
    public boolean contains(T object) {
        boolean bool = false;
        try {
            if(object != null){
                DoubleLinkedNode<T> currentNode;
                Iterator iterator = iterator();
                while(iterator.hasNext()){
                    currentNode = (DoubleLinkedNode<T>)iterator.next();
                    if(currentNode.getObject().equals(object)){
                        bool = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Fallo el contains(object)");
        }
        return bool;
    }

    @Override
    public boolean contains(T[] objects) {
        boolean bool = true;
        try {
            for (T object : objects) {
                if(!contains(object)){
                    bool = false;
                }
            }
        } catch (Exception e) {
            bool = false;
            System.out.println("Fallo el contains(objects)");
        }
        return bool;
    }

    @Override
    public NodeInterface<T> nodeOf(T object) {
        DoubleLinkedNode<T> currentNode = null;
        try {
            if(object != null){
                Iterator iterator = iterator();
                while(iterator.hasNext()){
                    currentNode = (DoubleLinkedNode<T>)iterator.next();
                    if(currentNode.getObject().equals(object)){
                        return currentNode;
                    }
                }
            }
            currentNode = null;
            System.out.println("No se encontro ");
        } catch (Exception e) {
            System.out.println("Fallo el nodeOf(object)");
            currentNode = null;
        }
        return currentNode;

    }

    @Override       //Sirve
    public boolean isEmpty() {
        return head == null;
    }

    @Override       //Sirve
    public T get() {
        if(!isEmpty()){
            return head.getObject();
        }else{
            return null;
        }
    }

    @Override       //Sirve
    public T[] get(int n) {
        T[]array = (T[])new Object[n];
        try {
            if(n <= size){
                if(!isEmpty()){
                    DoubleLinkedNode<T> currentNode;
                    Iterator iterator = iterator();
                    for (int i = 0; i < n; i++) {
                        currentNode = (DoubleLinkedNode<T>)iterator.next();
                        array[i] = currentNode.getObject();
                    }
                }
            }else
                System.out.println("El tamaño no es valido");
        } catch (Exception e) {
            System.out.println("Fallo el get(n)");
        }
        return array;
        
    }

    @Override       //Sirve
    public T getPrevious(NodeInterface<T> node) {
        try {
            Iterator iterator = iterator();
            while(iterator.hasNext()){
                DoubleLinkedNode<T> currentNode = (DoubleLinkedNode<T>)iterator.next();
                if(currentNode.getObject().equals(node.getObject())){
                    
                    return currentNode.getPrevious().getObject();
                }
            }
        } catch (Exception e) {
            System.out.println("Fallo el getPrevious(node)");
        }
        return null;
    }

    @Override       //Sirve
    public T getFromEnd() {
        T object = null;
        try {
            if(!isEmpty()){
                object = tail.getObject();
            }
        } catch (Exception e) {
            System.out.println("Fallo el getFromEnd()");
        }
        return object;
    }

    @Override       //Sirve
    public T[] getFromEnd(int n) {
        T[]array = (T[])new Object[n];
        try {
            if(!isEmpty() && n > 0 && n <= size ){
                DoubleLinkedNode<T> currentNode;
                Iterator iteratorReverse = reverseIterator();
                for (int i = 0; i < n; i++) {
                    currentNode = (DoubleLinkedNode<T>)iteratorReverse.next();
                    array[i] = currentNode.getObject();
                }
            }else
                System.out.println("El tamaño no es valido");
        } catch (Exception e) {
            System.out.println("Fallo el getFromEnd(n)");
        }
        return array;
    }

    @Override
    public T pop() {        //Sirve
        T temp = null;
        try {
            if(!isEmpty()){
                if(head != tail){
                    temp = head.getObject();
                    head = head.getNext();
                    head.setPrevious(tail);
                    tail.setNext(head);
                    size--;
                }else{
                    temp = tail.getObject();
                    head = tail = null;
                    size = 0;
                }
            }
        }catch (Exception e) {
            System.out.println("Fallo el pop()");
        }
        return temp;
    }

    @Override       //Sirve
    public boolean remove(T object) {
        boolean bool = false;
        try {
            if(!isEmpty()){
                DoubleLinkedNode nodeRemove = (DoubleLinkedNode) nodeOf(object);
                if(nodeRemove == head){
                    T deleteNode = pop();
                    if(deleteNode != null){
                        bool = true;
                    }
                }else{
                    Iterator iterator = iterator();
                    while(iterator.hasNext()){
                        DoubleLinkedNode iNode = (DoubleLinkedNode)iterator.next();
                        if(iNode.getObject().equals(object)){
                            if(iNode == tail){
                                tail = iNode.getPrevious();
                                tail.setNext(head);
                                head.setPrevious(tail);
                                size--;
                                bool = true;
                                break;
                            }else{
                                iNode.getPrevious().setNext(iNode.getNext());
                                iNode.getNext().setPrevious(iNode.getPrevious());
                                size--;
                                bool = true;
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Fallo el remove(object)");
        }
        return bool;
    }

    @Override  //Sirve     //Este metodo es si se le pasa exactamente el mismo nodo que se encuentra en la lista
    public boolean remove(NodeInterface<T> node) {
        boolean bool = false;
        try {
            if(!isEmpty()){
                if(node == head){
                    pop();

                }else{
                    Iterator iterator = iterator();
                    while(iterator.hasNext()){
                        DoubleLinkedNode iNode = (DoubleLinkedNode)iterator.next();
                        if(iNode.equals(node)){
                            if(iNode == tail){
                                tail = iNode.getPrevious();
                                tail.setNext(head);
                                head.setPrevious(tail);
                                size--;
                                bool = true;
                                break;
                            }else{
                                iNode.getPrevious().setNext(iNode.getNext());
                                iNode.getNext().setPrevious(iNode.getPrevious());
                                size--;
                                bool = true;
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Fallo el remove(object)");
        }
        return bool;
    }

    @Override       //Sirve
    public boolean removeAll(T[] objects) {
        boolean bool = true;
        try {
            for (T object : objects) {
                if(!remove(object)){
                    bool = false;
                }
            }
        } catch (Exception e) {
            bool = false;
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return bool;
    }

    @Override       //Sirve
    public boolean retainAll(T[] objects) {
       boolean bool = true;
        try {
            DoubleCircularList<T> list = new DoubleCircularList<>();
            Iterator iterator = iterator();
            while(iterator.hasNext()) {
                DoubleLinkedNode<T> nodo = (DoubleLinkedNode<T>) iterator.next();
                for (T object : objects) {
                    if (nodo.getObject().equals(object)) {
                        list.add(nodo.getObject());
                    }
                    if(!contains(object))
                        bool = false;
                }
            }
            head = list.head;
            tail = list.tail;
            tail.setNext(head);
            head.setPrevious(tail);
            size = list.size;
            
        } catch (Exception e) {
            bool = false;
            System.out.println("Fallo el retainAll(objects)");
        }
        return bool;
    }

    @Override       //Sirve
    public int size() {
        return size;
    }

    @Override       //SIRVE 
    public LinkedListInterface<T> subList(NodeInterface<T> from, NodeInterface<T> to) {
        DoubleCircularList<T> subList = new DoubleCircularList<T>();
        try {
            if(from != null && to != null){
                Iterator iterator = iterator();
                boolean bfrom = false;
                boolean bto = false;
                DoubleLinkedNode<T> node; 

                while(iterator.hasNext()){
                    node = (DoubleLinkedNode<T>)iterator.next();
                    if(!bfrom){
                        if(node.equals(from)){
                            bfrom = true;
                        }
                    }if(bfrom){
                        if(node.equals(to)){
                            bto = true;
                        }
                    }
                }
                if(bfrom && bto){
                    node = (DoubleLinkedNode<T>) from;
                    do {
                        subList.add((T) node.getObject());
                        node = node.getNext();
                    } while (node!=((DoubleLinkedNode<T>)to).getNext());
                }

            }
        } catch (Exception e) {
            System.out.println("Fallo el subList(from, to)");
        }
        return subList;

    }

    @Override
    public T[] toArray() {
        System.out.println("Sizeee" +size);
        Object[] array = new Object[size];
        try {
            Iterator iterator = iterator();
            DoubleLinkedNode node; 
            int i = 0;
            while(iterator.hasNext()){
                node = (DoubleLinkedNode)iterator.next();
                array[i++] = node.getObject();
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return (T[]) array;
    }
    @Override
    public boolean sortObjectsBySize() {
        if (head == null) {
            return false;
        }
    
        boolean swapped;
        do {
            swapped = false;
    
            DoubleLinkedNode<T> current = head;
            do {
                if (current.getObject().toString().length() > current.getNext().getObject().toString().length()) {
                    T temp = current.getObject();
                    current.setObject(current.getNext().getObject());
                    current.getNext().setObject(temp);
                    swapped = true;
                }
                current = current.getNext();
            } while (current != head.getPrevious());
    
            if (!swapped) {
                break;
            }
    
            DoubleLinkedNode<T> tail = head.getPrevious();
            do {
                if (tail.getObject().toString().length() < tail.getPrevious().getObject().toString().length()) {
                    T temp = tail.getObject();
                    tail.setObject(tail.getPrevious().getObject());
                    tail.getPrevious().setObject(temp);
                    swapped = true;
                }
                tail = tail.getPrevious();
            } while (tail != head);
        } while (swapped);
    
        return true;
    }
    


    @Override
    public DoubleLinkedNode<T> getHead() {
        return head;
    }

    @Override
    public Iterator<NodeInterface<T>> iterator() {
        return new Iterator<NodeInterface<T>>() {
            private DoubleLinkedNode<T> current = head; // Comenzar desde el inicio de la lista
            boolean firstHead= true;
                
            @Override
            public boolean hasNext() {
                if(firstHead) {
                    firstHead = false;
                    return current!=null; // Hay un siguiente nodo
                }else{
                    return current!=head;
                }
            }

            @Override
            public NodeInterface<T> next() {
                NodeInterface<T> nextNode = current; // Obtener el nodo actual
                current = current.getNext(); // Mover al siguiente nodo
                return nextNode;
            }
        };
    }

    public Iterator<NodeInterface<T>> reverseIterator() {
        return new Iterator<NodeInterface<T>>() {
            private DoubleLinkedNode<T> current = tail;
            boolean firstTail= true;
            public boolean hasNext() {
                if(firstTail) {
                    firstTail = false;
                    return current!=null; // Hay un siguiente nodo
                }else{
                    return current!=tail;
                }
            }
    
            public NodeInterface<T> next() {
                NodeInterface<T> nextNode = current; // Obtener el nodo actual
                current = current.getPrevious(); // Mover al siguiente nodo
                return nextNode;
            }
        };
    }
    
}
