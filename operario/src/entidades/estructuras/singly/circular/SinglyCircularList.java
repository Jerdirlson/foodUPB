package entidades.estructuras.singly.circular;

import java.io.Serializable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import entidades.estructuras.interfaces.linkedlist.LinkedListInterface;
import entidades.estructuras.interfaces.node.NodeInterface;
import entidades.estructuras.nodes.SinglyLinkedNode;



public class SinglyCircularList<T extends Serializable> implements LinkedListInterface<T>{

    private SinglyLinkedNode<T> head;
    private SinglyLinkedNode<T> tail;
    private int size; // para saber el tamaño de la lista en cualquier momento


    @Override 
    public boolean add(T object) {
        boolean bool = false;
        if(object != null){
            try {
                if(isEmpty()){
                    head = tail = new SinglyLinkedNode<T>(object);
                    tail.setNext(head);
                }else{
                    tail.setNext(new SinglyLinkedNode<T>(object));
                    tail = tail.getNext();
                    tail.setNext(head);
                }
                size++;
                bool = true;
            } catch (Exception e) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
                bool = false;
            }
        }
        return bool;
    }

     /*
     * Este metodo si se le pasa un nodo con el valor que estamos buscando
     */
    @Override
    public boolean add(NodeInterface<T> node, T object) {
        boolean add = false;
        try {
            if(object != null){
                if(isEmpty()){
                    add = false;
                }else{
                    Iterator iterator = iterator();
                    SinglyLinkedNode<T> currentNode;
                    SinglyLinkedNode<T> newNode = new SinglyLinkedNode<T>(object);
                    while(iterator.hasNext()){
                        currentNode = (SinglyLinkedNode<T>)iterator.next();
                        if(currentNode.getObject().equals(node.getObject())){
                            newNode.setNext(currentNode.getNext());
                            currentNode.setNext(newNode);
                            add = true;
                            size++;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            add = false;
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return add;
    }

    /*
     * Este metodo es si se le pasa exactamente el mismo nodo con la referencia que tiene en la lista
     */
    public boolean addObjectReference(NodeInterface<T> node, T object) {
        boolean add = false;
        try {
            if(object != null){
                if(isEmpty()){
                    add = false;
                }else{
                    Iterator iterator = iterator();
                    SinglyLinkedNode<T> currentNode;
                    SinglyLinkedNode<T> newNode = new SinglyLinkedNode<T>(object);
                    while(iterator.hasNext()){
                        currentNode = (SinglyLinkedNode<T>)iterator.next();
                        if(currentNode.equals(node)){
                            newNode.setNext(currentNode.getNext());
                            currentNode.setNext(newNode);
                            add = true;
                            size++;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            add = false;
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return add;
    }

    /*
     * Este metodo si se le pasa un nodo con el valor que estamos buscando
     */
    @Override  
    public boolean add(NodeInterface<T> node, NodeInterface<T> next) {
        boolean bool = false;
        try {
            if(contains(node.getObject())){
                SinglyLinkedNode<T> snext = (SinglyLinkedNode<T>)next;
                SinglyLinkedNode<T> snode = (SinglyLinkedNode<T>)node;
                if (snode == tail){
                    tail = snext;
                    tail.setNext(head);
                }
                nodeOf(snode.getObject());
                Iterator iterator = iterator();
                while(iterator.hasNext()){
                    SinglyLinkedNode<T> currentNode = (SinglyLinkedNode<T>)iterator.next();
                    if(currentNode.getObject().equals(snode.getObject())){
                        snode = currentNode;
                        snext.setNext(snode.getNext());
                        snode.setNext(snext);
                        bool = true;
                        size++;
                        break;
                    }
                }
            }
            
        } catch (Exception e) {
            bool = false;
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return bool;
    }

     /*
     * Este metodo si se le pasa un nodo con la referencia exacta del mismo nodo de la lista
     */

    public boolean addNodeReference(NodeInterface<T> node, NodeInterface<T> next) {
        boolean bool = false;
        try {
            if(containsNode(node)){
                SinglyLinkedNode<T> snext = (SinglyLinkedNode<T>)next;
                SinglyLinkedNode<T> snode = (SinglyLinkedNode<T>)node;
                if (snode == tail){
                    tail = snext;
                    tail.setNext(head);
                }
                Iterator iterator = iterator();
                while(iterator.hasNext()){
                    SinglyLinkedNode<T> currentNode = (SinglyLinkedNode<T>)iterator.next();
                    if(currentNode.equals(snode)){
                        snode = currentNode;
                        snext.setNext(snode.getNext());
                        snode.setNext(snext);
                        bool = true;
                        size++;
                        break;
                    }
                }
            }
            
        } catch (Exception e) {
            bool = false;
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return bool;
    }

    @Override       //Sirve
    public boolean add(T[] objects) {
        boolean bool = false;
        try {
            for (T object : objects) {
                add(object);
            }
            bool = true;
        } catch (Exception e) {
            bool = false;
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return bool;
    }

    /*
     * Este es si se le pasa el valor del nodo, pero no es el nodo exacto
     */

    @Override   //Sirve
    public boolean add(NodeInterface<T> node, T[] objects) {
        boolean bool = true;
        try {
                if(contains(node.getObject())){

                    for (int i = objects.length - 1; i >= 0; i--) {
                        SinglyLinkedNode<T> newNode = new SinglyLinkedNode<T>(objects[i]);
                        if (!add(node, newNode)){
                            bool = false;
                        }
                    }
                }

        } catch (Exception e) {
            bool = false;
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return bool;
    }

    /*
     * Este es si se le pasa el nodo exacto de la lista con la referencia
     * NO SIRVE
     */

    public boolean addListNodeReference(NodeInterface<T> node, T[] objects) {
        boolean bool = true;
        try {
                if(containsNode(node)){
                    for (int i = objects.length - 1; i >= 0; i--) {
                        SinglyLinkedNode<T> newNode = new SinglyLinkedNode<T>(objects[i]);
                        if (!add(node, newNode)){
                            bool = false;
                        }
                    }
                }

        } catch (Exception e) {
            bool = false;
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return bool;
    }

    @Override //SIRVE
    public boolean addOnHead(T object) {
        boolean add = false;
        if(object != null){
            try {
                if(isEmpty()){
                    head = tail = new SinglyLinkedNode<T>(object);
                    tail.setNext(head);
                }else{
                    SinglyLinkedNode<T> newNode = new SinglyLinkedNode<T>(object);
                    newNode.setNext(head);      //El nuevo nodo apunta al que estaba en la cabeza
                    head = newNode;             //seteamos que el nuevo nodo sea la cabeza ahora
                    tail.setNext(head);
                }
                size++;
                add = true;
            } catch (Exception e) {
                add = false;
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
            }
        }
        return add;
    }
    /*
     * SIRVE
     */
    @Override //SIRVE
    public boolean addOnHead(T[] objects) {
        boolean add = false;
        try {
            for (int i = objects.length - 1; i >= 0; i--) {
             addOnHead(objects[i]);
            }
            add = true;
        } catch (Exception e) {
            add = false;
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return add;
    }

    @Override //SIRVE
    public boolean clear() {
        try {
            head = tail = null;
            size = 0;
            return true;
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
            return false;
        }
    }

    @Override  //SIRVE
    public LinkedListInterface<T> cloneList() {
        try {
            SinglyCircularList<T> clone = new SinglyCircularList<T>();
            SinglyLinkedNode<T> currentNode;
            Iterator iterator = iterator();

            while(iterator.hasNext()){
                    currentNode = (SinglyLinkedNode<T>)iterator.next();
                    clone.add(currentNode.getObject());
                }

            return clone;
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
            return null;
        }
    }

    @Override  //SIRVE
    public boolean contains(T object) {
        boolean bool = false;
        try {
            if(object != null){
                SinglyLinkedNode<T> currentNode;
                Iterator iterator = iterator();
                while(iterator.hasNext()){
                    currentNode = (SinglyLinkedNode<T>)iterator.next();

                    if(currentNode.getObject().equals(object)){
                            bool = true;
                    }

                }
            }
        } catch (Exception e) {
            bool = false;
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }        
        return bool;
    }

    public boolean containsNode(NodeInterface<T> node) {
        boolean bool = false;
        try {
            if(node != null){
                SinglyLinkedNode<T> currentNode;
                Iterator iterator = iterator();
                while(iterator.hasNext()){
                    currentNode = (SinglyLinkedNode<T>)iterator.next();

                    if(currentNode.equals(node)){
                            bool = true;
                    }

                }
            }
        } catch (Exception e) {
            bool = false;
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }        
        return bool;
    }

    @Override //SIRVE
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
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return bool;
    }

    @Override   //SIRVE
    public NodeInterface<T> nodeOf(T object) {
        SinglyLinkedNode<T> currentNode = null;
        SinglyLinkedNode<T> node = null;
        try {

            if(object != null){

                Iterator iterator = iterator();
                while(iterator.hasNext()){
                    currentNode = (SinglyLinkedNode<T>)iterator.next();

                    if(currentNode.getObject().equals(object)){
                        node = currentNode;
                        return node;
                    }
                }
            }
        } catch (Exception e) {
            // Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
            return null;
        }
        return node; 
    }

    @Override   //SIRVE
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override   //SIRVE
    public T get() {
        if(!isEmpty()){
            return head.getObject();
        }else{
            return null;
        }
    }
    /*
     * PROBAR-------------------------------------
     */

    @Override    //SIRVEE
    public T[] get(int n) {
        T[]array = (T[])new Object[n];
        try {
            if(n<= size){
                
                if(!isEmpty()){
                    SinglyLinkedNode<T> currentNode;
                    Iterator iterator = iterator();
                    for (int i = 0; i < n; i++) {
                        currentNode = (SinglyLinkedNode<T>)iterator.next();
                        array[i] = currentNode.getObject();
                    }
                }
            }else
                System.out.println("El tamaño no es valido");
            
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return array;
    }

    /*
     * PROBAR-------------------------------------
     */

    @Override   //SIRVE
    public T getPrevious(NodeInterface<T> node) {
        SinglyLinkedNode<T> currentNode = null;
        SinglyLinkedNode<T> previousNode = null;
        try {
            Iterator iterator = iterator();
            while(iterator.hasNext()){
                previousNode = currentNode;
                currentNode = (SinglyLinkedNode<T>)iterator.next(); //EN la primera iteracion este es head
                if(currentNode == head){
                    previousNode = tail;
                }
                if(currentNode.getObject().equals(node.getObject())){
                    return previousNode.getObject();
                }
            }
            previousNode = null;
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
            return null;
        }

        return previousNode.getObject();
    }

    /*
     * PROBAR-------------------------------------
     */
    @Override //SIRVE
    public T getFromEnd() {
        T object = null;
        try {
            if(!isEmpty()){
                object = tail.getObject();
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
            return null;
        }
        return object;
    }

    /*
     * PROBAR NO SE SI FUNCIONA CUIDADO -------------------------------------
     */
   @Override    //SIRVE
public T[] getFromEnd(int n) {
    T[] array = (T[]) new Object[n];
    try {
        if (!isEmpty() && n > 0 && n <= size) {
            SinglyLinkedNode<T> currentNode;
            Iterator<NodeInterface<T>> iterator = iterator();

            for (int i = 0; i < size - n; i++) {
                currentNode = (SinglyLinkedNode<T>) iterator.next();
            }

            int index = 0;

            while (iterator.hasNext()) {
                currentNode = (SinglyLinkedNode<T>) iterator.next();
                array[index++] = currentNode.getObject();
            }
        }
    } catch (Exception e) {
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    return array;
}

@Override   //SIRVE
    public T pop() {
        Object temp = null;
        try {
            if(!isEmpty()){
                temp = head.getObject();
                head = head.getNext();
                tail.setNext(head);
                size--;
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return (T) temp;
    }

    /*
     * si era la cola pues cambia el apuntador al get previous, si es la cabeza cambia el apuntador al siguiente y si esta en el medio 
     * obtiene el nodo del objeto que tiene antes de el y se cambia el apuntador de ese nodo al siguiente del actual. 
     */
    @Override   //SIRVE
    public boolean remove(T object) {
        boolean bool = false;
        try {
            if(!isEmpty()){
                SinglyLinkedNode nodeRemove = (SinglyLinkedNode) nodeOf(object);
                if(nodeRemove==head){
                    head = head.getNext();
                    tail.setNext(head);
                }else{
                    Iterator iterator = iterator();
                    SinglyLinkedNode iNode = null;
                    SinglyLinkedNode iNodeAnterior = null;
                    while(iterator.hasNext()){
                        iNodeAnterior = iNode;
                        iNode = (SinglyLinkedNode) iterator.next();
                        if(iNode.getObject().equals(object)){
                            iNodeAnterior.setNext(iNode.getNext());
                            if(iNode==tail){
                                tail = iNodeAnterior;
                                tail.setNext(head);
                            }
                            bool=true;
                            size--;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            bool = false;
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return bool;
    }
    /*
     * Es el mismo de arirba pero con nodos
     */
    @Override //SIRVE
    public boolean remove(NodeInterface<T> node) {
        boolean bool = false;
        try {
            if(!isEmpty()){
                SinglyLinkedNode<T> nodeRemove = (SinglyLinkedNode<T>) nodeOf(node.getObject());
                if(nodeRemove==head){
                    head = head.getNext();
                    tail.setNext(head);
                }else{
                    Iterator iterator = iterator();
                    SinglyLinkedNode<T> iNode = null;
                    SinglyLinkedNode<T> iNodeAnterior = null;
                    while(iterator.hasNext()){
                        iNodeAnterior = iNode;
                        iNode = (SinglyLinkedNode<T>) iterator.next();
                        if(iNode.getObject().equals(node.getObject())){
                            iNodeAnterior.setNext(iNode.getNext());
                            if(iNode==tail){
                                tail = iNodeAnterior;
                                tail.setNext(head);
                            }
                            bool=true;
                            size--;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            bool = false;
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return bool;
    }
    /*
     * Si no encuentra un elemento devuelve false pero si borra los que encuentre
     * borre todos los que encuentre pero tenga un booleano que ya sea false
     */
    @Override //SIRVIO
    public boolean removeAll(T[] objects) {
        boolean bool = true;
        try {
            for (T object : objects) {
                if(!remove(object)){
                    bool = false;
                }else
                    size--;
            }
        } catch (Exception e) {
            bool = false;
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return bool;
    }
    /*
     * Borrar todo menos lo que esta en el array
     * crea una nueva lista que se recorra la lista actual y si va encontrando los objetos que estan en la lista actual y en el array 
     * mete los nodos en la nueva lista
     * tendria que reemplazar la cabeza y la cola por la nueva lista
     */
    @Override   //sirvio
    public boolean retainAll(T[] objects) {
        boolean bool = true;
        try {
            SinglyCircularList list = new SinglyCircularList();
            Iterator iterator = iterator();
            while(iterator.hasNext()) {
                SinglyLinkedNode<T> nodo = (SinglyLinkedNode<T>) iterator.next();
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
            size = list.size;
        } catch (Exception e) {
            e.getStackTrace();
            bool = false;
        }
        return bool;
    }

    @Override
    public int size() {
        return size;
    }

    /*
     * Validar si es de from a to es importante que solo tenga un solo orden si no se peta
     * el from sera la head de la nueva lista que cree, luego buscar el to y va a ser la cola de la nueva lista
     * despues la nu
     */
    @Override   //SIRVE
    public LinkedListInterface<T> subList(NodeInterface<T> from, NodeInterface<T> to) {
        SinglyCircularList<T> newList = new SinglyCircularList<T>();
        try {
            if(from != null && to != null){
                Iterator iterator = iterator();
                boolean bfrom = false;
                boolean bto = false;
                SinglyLinkedNode node; 

                while(iterator.hasNext()){
                    node = (SinglyLinkedNode)iterator.next();
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
                    node = (SinglyLinkedNode) from;
                    while(node!=((SinglyLinkedNode)to).getNext()){
                        newList.add((T) node.getObject());
                        node = node.getNext();
                    }
                }

            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return newList;
    }

@Override //SIRVE
public T[] toArray() {
    Object[] array = new Object[size];
    try {
        Iterator iterator = iterator();
        SinglyLinkedNode node; 
        int i = 0;
        while(iterator.hasNext()){
            node = (SinglyLinkedNode)iterator.next();
            array[i++] = node.getObject();
        }
    } catch (Exception e) {
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    return (T[]) array;
}

/* 
* 
*/
@Override   //SIRVE
public boolean sortObjectsBySize() {
    boolean bool = false;
    SinglyCircularList<T> newList = new SinglyCircularList<T>();
        if (!isEmpty()) {
            Iterator iter = iterator();
            SinglyLinkedNode<T> nodeTemp = null;
            Object[][] arrTo = (T[][]) new Object[size()][2];
            for (int i = 0; i < size(); i++) {
                nodeTemp = (SinglyLinkedNode<T>) iter.next();
                String stringNode = nodeTemp.getObject().toString();
                Integer sizeString = stringNode.length();
                for (int j = 0; j < 2; j++) {
                    arrTo[i][0] = sizeString;
                    arrTo[i][1] = nodeTemp;
                }
            }
            int fila = arrTo.length;
            for (int i = fila / 2; i > 0; i /= 2) {
                for (int j = i; j < fila; j++) {
                    Object[] filaTemp = arrTo[j];
                    int k;
                    for (k = j; k >= i && ((int) arrTo[k - i][0] > (int) filaTemp[0]); k -= i) {
                        arrTo[k] = arrTo[k - i];
                    }
                    arrTo[k] = filaTemp;
                }
            }
            for (Object[] objects : arrTo) {
                newList.add(((SinglyLinkedNode<T>) objects[1]).getObject());
            }

            head = newList.head;
            tail = newList.tail;
            tail.setNext(head);
            bool = true;
        }
        return bool;
    }

    @Override   //SIRVE
    public SinglyLinkedNode<T> getHead() {
        return head;
    }

    @Override
    public Iterator<NodeInterface<T>> iterator() {
        return new Iterator<NodeInterface<T>>() {
            private SinglyLinkedNode<T> current = head; // Comenzar desde el inicio de la lista
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
    
}
