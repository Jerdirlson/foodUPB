package entidades.estructuras.interfaces.node;

/**
 * Interface for Node. 
 * Prototype Pattern for Cloning Nodes.
 * 
 * @author Lenin Javier Serrano Gil
 * @param <T> generic objects
 */
public interface NodeInterface<T> extends Cloneable {

  /***
   * Sets an object in the node. The object must not be null. 
   * 
   * @param object
   * @return 'true' if the object was successfully set; otherwise, return 'false'.
   */
  public boolean setObject(T object);

  /***
   * Gets the object contained in the node.
   * 
   * @return the object in the node or null if the node is empty.
   */
  public T getObject();

  /***
   * Compares the object in the node with the specified object.
   * 
   * @param object
   * @return 'true' if the object is equals to the object in the node, otherwise 'false'.
   */
  public boolean isEquals(T object);

  /**
   * Gets a clone of the node.
   * 
   * @return a clone of the node or a null node if the node is empty.
   */
  // -> Prototype Pattern
  public NodeInterface<T> getClone();

  /***
   * Gets a string representation of the node.
   * 
   * @return a string representation of the node or a empty string if the node is empty.
   */
  public String toString();
}
