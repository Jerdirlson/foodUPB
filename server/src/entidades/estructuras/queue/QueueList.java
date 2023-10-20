package entidades.estructuras.queue;

import java.io.Serializable;

import entidades.estructuras.doublee.linked.DoubleLinkedList;
import entidades.estructuras.interfaces.stack.StackInterface;
import entidades.estructuras.nodes.DoubleLinkedNode;
import entidades.estructuras.stack.StackList;

public class QueueList<T extends Serializable> implements StackInterface<T>, Serializable{

    public DoubleLinkedList<T> list = new DoubleLinkedList<>();
    private int size;
    private int absoluteSize;

    public QueueList() {
        this.size = 0;
    }

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
            list.add(object);
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

            StackList<T> stack = new StackList<>();

            while (!isEmpty()) {
                stack.push(pop());
            }

            stack.sort();

            while (!stack.isEmpty()) {
                push(stack.pop());
            }

            bool = true;

        } catch (Exception e) {
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
