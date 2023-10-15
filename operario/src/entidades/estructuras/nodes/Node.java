package entidades.estructuras.nodes;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import entidades.estructuras.interfaces.node.NodeInterface;


public class Node<T extends Serializable> implements NodeInterface<T>, Serializable{

    private T object;

    public Node(T object) {
        this.object = object;
    }

    public Node(){
        this.object = null;
    }

    @Override
    public boolean setObject(T object) {
        if(object != null){
            try {
                this.object = object;
                return true;
            } catch (Exception e) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
                return false;
            }
        }
        return false;
    }

    @Override
    public T getObject() {
        return this.object;
    }

    @Override
    public boolean isEquals(T object) {
        try {
            return this.object.toString().equals(object.toString());
            //se hace el to string para que pueda comprara de manera correcta los objetos
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public NodeInterface<T> getClone() {
        try {
            if(object != null){
                return new Node<T>(object);
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return null;
    }

}
