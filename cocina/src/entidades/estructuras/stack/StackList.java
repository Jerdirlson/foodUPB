package entidades.estructuras.stack;

import java.io.Serializable;
import java.util.Stack;

import entidades.estructuras.doublee.linked.DoubleLinkedList;
import entidades.estructuras.interfaces.stack.StackInterface;
import entidades.estructuras.nodes.DoubleLinkedNode;

public class StackList<T extends Serializable> implements StackInterface<T>{

    public DoubleLinkedList<T> list = new DoubleLinkedList<T>();
    public int size;

    @Override
    public boolean clear() {
        boolean bool = false;
        try {
            while(!isEmpty()){
                pop();
            }
            bool = true;
        } catch (Exception e) {
            bool = false;
            System.out.println("Fallo el clear()");
        }
        return bool;
    }

    @Override
    public T peek() {
        T Object = null;
        try {
            Object = list.getHead().getObject();
        } catch (Exception e) {
            System.out.println("Fallo el peek()");
        }
        return Object;
    }

    @Override
    public T pop() {
        T Object = null;
        try {
            Object = list.pop();
            size--;
        } catch (Exception e) {
            System.out.println("Fallo el pop()");
        }
        return Object;
    }


    @Override
    public boolean push(T object) {
        boolean bool = false;
        try {
            list.addOnHead(object);
            size++;
            bool = true;
        } catch (Exception e) {
            bool = false;
            System.out.println("Fallo el push(object)");
        }
        return bool;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean search(T object) {
        boolean bool = false;
        try {
            bool = list.contains(object);
        } catch (Exception e) {
            System.out.println("Fallo el search(object)");
            bool = false;
        }
        return bool;
    }

    @Override
    public boolean sort() {
        boolean bool = false;
        try {
        if (isEmpty() || size == 1) {
            return true;
        }

        // Crear una pila auxiliar para almacenar temporalmente los elementos ordenados.
        Stack<T> sortedStack = new Stack<>();

        // Mover todos los elementos de la cola a la pila.
        while (!isEmpty()) {
            T current = pop(); // Utiliza poll() en lugar de pop() para una lista.

            if (sortedStack.isEmpty() || current.toString().length() >= sortedStack.peek().toString().length()) {
                sortedStack.push(current);
            } else {
                Stack<T> tempStack = new Stack<>();
                while (!sortedStack.isEmpty() && current.toString().length() < sortedStack.peek().toString().length()) {
                    tempStack.push(sortedStack.pop());
                }
                sortedStack.push(current);

                while (!tempStack.isEmpty()) {
                    sortedStack.push(tempStack.pop());
                }
            }
        }

        // Mover los elementos ordenados de la pila de vuelta a la cola.
        while (!sortedStack.isEmpty()) {
            push(sortedStack.pop()); // Utiliza offer() en lugar de push() para una lista.
        }

        bool = true;
    } catch (Exception e) {
        // Maneja las excepciones según tus necesidades.
        return false;
    }
        return bool;
    }

    @Override
    public boolean reverse() {
        boolean bool = false;
        try {
            StackList<T> reversedStack = new StackList<>();
             while (!isEmpty()) {
            reversedStack.push(pop());
            }
        
            list = reversedStack.list;
            size = reversedStack.size;
            bool = true;

        } catch (Exception e) {
            bool = false;
            System.out.println("Fallo el reverse()");
        }
        return bool;
    }

    public void iterateStack() {
        DoubleLinkedNode<T> currentNode = list.getHead();
        while (currentNode != null) {
            T element = currentNode.getObject();
            System.out.print(element + " ");
            currentNode = currentNode.getNext();
        }
        System.out.println();
    }
}
