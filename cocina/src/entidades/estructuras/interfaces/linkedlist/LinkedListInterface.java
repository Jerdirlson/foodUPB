package entidades.estructuras.interfaces.linkedlist;

import java.util.Iterator;

import entidades.estructuras.interfaces.node.NodeInterface;


/**
 * Interface for Linked List
 * 
 * @author Lenin Javier Serrano Gil
 * @param <T> generic objects
 */
public interface LinkedListInterface<T> {

  /***
   * Adds an object at the end of the list.
   * 
   * @param object
   * @return 'true' if the object was added successfully, otherwise 'false'.
   */
  public boolean add(T object);

  /***
   * Adds an object into the next position of the specified node.
   * 
   * @param node
   * @param object
   * @return 'true' if the object was added successfully, otherwise 'false'.
   */
  public boolean add(NodeInterface<T> node, T object);

  /***
   * Adds a node at the next position of the specified node.
   * 
   * @param node 
   * @param next
   * @return 'true' if the node was added successfully, otherwise 'false'.
   */
  public boolean add(NodeInterface<T> node, NodeInterface<T> next);

  /***
   * Adds all the objects in an array to the end  of the list.
   * 
   * @param objects
   * @return 'true' if the objects was added successfully, otherwise 'false'.
   */
  public boolean add(T[] objects);

  /***
   * Adds all the objects from an array starting at the specified node.
   * 
   * @param node
   * @param objects
   * @return  'true' if the objects was added successfully, otherwise 'false'.
   */
  public boolean add(NodeInterface<T> node, T[] objects);

  /***
   * Adds the specified object at the beginning of the list.
   * 
   * @param object
   * @return 'true' if the object was added successfully, otherwise 'false'.
   */
  public boolean addOnHead(T object);

  /***
   * Adds all the objects in an array at the beginning of the list.
   * 
   * @param objects
   * @return 'true' if the object was added successfully, otherwise 'false'.
   */
  public boolean addOnHead(T[] objects);

  /***
   * Removes all nodes from the list.
   * 
   * @return 'true' if the list was cleared successfully, otherwise 'false'.
   */
  public boolean clear();

  /***
   * Gets a clone of the list.
   * 
   * @return a new instance of the list. If the list is empty, then a new empty list.
   */
  public LinkedListInterface<T> cloneList();

  /***
   * Determines if the list contains the specified object.
   * 
   * @param object
   * @return 'true' if the object is present in the list.
   */
  public boolean contains(T object);

  /***
   * Determines if the list contains the specified objects.
   * 
   * @param objects
   * @return 'true' if the list contains all objects in the array.
   */
  public boolean contains(T[] objects);

  /***
   * Gets the node of the specified object.
   * 
   * @param object
   * @return the node of the specified object or a null node if the list does not contain the object.
   */
  public NodeInterface<T> nodeOf(T object);

  /***
   * Determines if the list contains any node.
   * 
   * @return 'true' if the list is empty or 'false' if the list contains any node.
   */
  public boolean isEmpty();

  /***
   * Gets the first object of the list.
   * 
   * @return the first object of the list or a null object if the list is empty.
   */
  public T get();

   /***
   * Gets the first n objects of the list.
   * 
   * @return the first object of the list or an array empty if the list is empty.
   */
  public T[] get(int n);

  /***
   * Gets an object from the node before the specified node.
   * 
   * @param node
   * @return an object from the node before the specified node, 
   * or a null object if the specified node is the first node, 
   * or a null object if the list not contains the specified node.
   */


  public T getPrevious(NodeInterface<T> node);

   /***
   * Gets an object from the end of the list
   * 
   * @return an object from the end of the list or a null object if the list is empty.
   */
  public T getFromEnd();

  /***
   * Gets the last n objects of the list.
   * 
   * @return the last n objects of the list, or an array empty if the list is empty.
   */
  public T[] getFromEnd(int n);

  /***
   * Gets and removes the object from the beginning of the list
   * 
   * @return the object from the beginning of the list or a null object if the list is empty.
   */
  public T pop();

  /***
   * Removes the node from the list that is associated with the specified object.
   * 
   * @param object
   * @return 'true' if the node was removed successfully, otherwise 'false'.
   */
  public boolean remove(T object);

  /***
   * Removes the node from the list that is associated with the specified node.
   * 
   * @param node
   * @return 'true' if the node was removed successfully, otherwise 'false'.
   */
  public boolean remove(NodeInterface<T> node);

  /***
   * Removes all nodes from the list that are related to the objects in the specified array.
   * 
   * @param objects
   * @return 'true' if all nodes was removed successfully, otherwise 'false'.
   */
  public boolean removeAll(T[] objects);

  /***
   * Removes all nodes from the list that are not related to the objects in the specified array.
   * 
   * @param objects
   * @return 'true' if all nodes was removed successfully, otherwise 'false'.
   */
  public boolean retainAll(T[] objects);

  /***
   * Gets the size of the list.
   * 
   * @return the size of the list.
   */
  public int size();

  /***
   * Gets a sub-list by the specified "from" and "to" nodes.
   * 
   * @param from
   * @param to
   * @return a sub-list between the specified 'from' and 'to' nodes. 
   * If the 'from' node is after the 'to' node or the list is empty, return a empty list.
   */
  public LinkedListInterface<T> subList(NodeInterface<T> from, NodeInterface<T> to);

  /***
   * Gets an array with the objects of the list.
   * 
   * @return an array with the objects of the list or an empty array if the list is empty.
   */
  public T[] toArray();

  /***
   * Sort the list by the size of the objects
   * 
   * @return 'true' if the list was sorted successfully, otherwise 'false'.
   */
  public boolean sortObjectsBySize();

  /***
   * Gets a string representation of the list.
   * 
   * @return a string representation of the list or an empty string if the list is empty.
   */
  public String toString();

  /***
   * Gets an iterator of the list.
   * 
   * @return an iterator of the list or a null iterator if the list is empty.
   */
  
    public NodeInterface<T> getHead();


  public Iterator<NodeInterface<T>> iterator();



}
