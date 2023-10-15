package entidades.estructuras.stack;

import java.io.Serializable;

import entidades.estructuras.interfaces.stack.StackInterface;

public class StackArray<T extends Serializable> implements StackInterface<T>{
    Object[] array;
    int size;
    final int maxSize;
    public StackArray(int maxSize){
        this.maxSize = maxSize;
        array = new Object[maxSize];
        size = 0;
    }

    @Override
    public boolean clear() {
        boolean bool = false;
        try {
            array = new Object[maxSize];
            size = 0;
            bool = true;
        } catch (Exception e) {
            bool = false;
            System.out.println("Fallo el clear()");
        }
        return bool;
    }

    @Override
    public T peek() {
        try {
            if(!isEmpty()){
                return (T)array[size-1];
            }
        } catch (Exception e) {
            System.out.println("Fallo el peek()");
        }
        return null;
    }

    @Override
    public T pop() {
        Object object = new Object();
        try {
            if(!isEmpty()){
                object = array[size-1];
                array[size-1] = null;
                size--;
            }
            
        } catch (Exception e) {
            System.out.println("Fallo el pop()");
        }
        return (T) object;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean push(T object) {
        boolean bool = false;
        try {
            if (size != maxSize) {
            array[size] = object;
            size++;
            bool = true;
        }
        } catch (Exception e) {
            bool = false;
            System.out.println("Fallo el push(object)");
        }
        return bool;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean search(T object) {
        boolean bool = false;
        try {
            StackArray<T> list = new StackArray<>(maxSize);
            while (!isEmpty()) {
                Object object2 = pop();
                if (object2.equals(object)) {
                    bool = true;
                }
                list.push((T) object2);
            }
            while (!list.isEmpty()) {
                push(list.pop());
            }
        } catch (Exception e) {
            bool = false;
            System.out.println("Fallo el search(object)");
        }

        return bool;
    }

    @Override
    public boolean sort() {
        // Crear una pila auxiliar para contener los elementos ordenados
        StackArray<T> sortedStack = new StackArray<>(maxSize);
    
        while (!isEmpty()) {
            T current = pop();
            
            if (sortedStack.isEmpty() || current.toString().length() >= sortedStack.peek().toString().length()) {
                sortedStack.push(current);
            } else {
                StackArray<T> tempStack = new StackArray<>(maxSize);
                while (!sortedStack.isEmpty() && current.toString().length() < sortedStack.peek().toString().length()) {
                    tempStack.push(sortedStack.pop());
                }
                sortedStack.push(current);
                
                while (!tempStack.isEmpty()) {
                    sortedStack.push(tempStack.pop());
                }
            }
        }

        while (!sortedStack.isEmpty()) {
            push(sortedStack.pop());
        }
    
        return true;
    }

    @Override
    public boolean reverse() {
        StackArray<T> reversedStack = new StackArray<>(maxSize);
        while(size != 0){
            reversedStack.push(pop());
        }
        array = reversedStack.array;
        size = reversedStack.size;
        return true;
    }
    

    public void iterateStack() {
        
        System.out.println("Stack");
        for (int i = array.length - 1 ; i >= 0; i--) {
            System.out.println(array[i] + " ");
            
        }
    }


}
