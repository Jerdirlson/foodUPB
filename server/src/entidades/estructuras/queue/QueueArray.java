package entidades.estructuras.queue;

import java.io.Serializable;
import java.util.Collections;
import java.util.Stack;

import entidades.estructuras.interfaces.stack.StackInterface;
import entidades.estructuras.stack.StackArray;


public class QueueArray<T extends Serializable> implements StackInterface<T>{

    Object[] array;
    int size;
    final int maxSize;
    int head = 0;
    int tail = 0;

    public QueueArray(int maxSize) {
        this.maxSize = maxSize;
        array = new Object[maxSize];
        size = 0;
    }

    @Override
    public boolean clear() {
        try {
            array = new Object[maxSize];
            size = 0;
            head = 0;
            tail = 0;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public T peek() {
        try {
            Object object = new Object();
            if (size != 0) {
                object = array[head];
            }
            return (T) object;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public T pop() {
        try {
            T object = (T) array[head];
            if (size != 0) {
                array[head] = null;
                head = (head + 1) % maxSize;
                size--;
                return object;
            }
            return (T) object;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean push(T object) {
        try {
            if (size != maxSize) {
                array[tail] = object;
                tail = (tail + 1) % maxSize;
                size++;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean search(T object) {
        try {
            boolean bool = false;
            for (Object obj : array) {
                if (obj == object) {
                    bool = true;
                }
            }
            return bool;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean sort() {
        boolean bool = false;
        try {
            if (isEmpty() || size == 1) {
                return true;
            }

            StackArray<T> stack = new StackArray<>(maxSize);

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
        try {
            if (size <= 1) {
                return true;
            }
            
            Stack<T> stack = new Stack<>();
            
            int index = head;
                for (int i = 0; i < size; i++) {
                    stack.push((T) array[index]);
                    index = (index + 1) % maxSize;
                }

            index = head;
                for (int i = 0; i < size; i++) {
                    array[index] = stack.pop();
                    index = (index + 1) % maxSize;
                }
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String iterateStack(){
        String stringRes = "";
        for (Object object : array) {
            if (object != null) {
                stringRes += "(" + object.toString() + ")";
            } else {
                stringRes += "[null]";
            }
        }
        return stringRes;
    }
}
