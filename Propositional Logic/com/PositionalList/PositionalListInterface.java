package com.PositionalList;
public interface PositionalListInterface <T> extends Iterable<T>{

    ////////////////////////////////////////// Accessor Methods 
    /*
     * returns the position of the first element of L (or null if empty)
     */

    public Position<T> first();  
    
    /*
     * returns the position of the last element of L (or null if empty)
     */

    public Position<T> last();

    /*
     * returns the position of L immediately before position p (or null if p is the first position)
     */

    public Position<T> before(Position<T> p); 

    /*
     * returns the position of L immediately after position p (or null if p is the last position)
     */

    public Position<T> after(Position<T> p);

    /*
     * return true if list L does not contain any elements
     */

    public boolean isEmpty();

    /*
     * returns the number of elements in list L
     */

    public int size();


    /////////////////////////////////////// Update methods

    /*
     * Inserts a new element e at the front of the list, returning the position of the new element
     */

    public Position<T> addFirst(T data);

    /*
     * Inserts a new element e at the back of the list, returning the position of the new element
     */

    public Position<T> addLast(T data);

    /*
     * inserts a new element e in the list, just before position p, returning the position of the new element
     */

    public Position<T> addBefore(Position<T> p, T data);

    /*
     * inserts a new element e in the list, just after position p, returning the position of the new element
     */

    public Position<T> addAfter(Position<T> p, T data);

    /*
     * replaces the element at position p with element e, returning the element formerly at position p
     */

    public T set(Position<T> p, T data);

    /*
     * removes and returns the element at position p in the list, invalidating the position
     * if that element is deleted beforehand then returns null
     */

    public T remove(Position<T> p);

    /*
     * print contents of the list
     */

    public void printList();




}
