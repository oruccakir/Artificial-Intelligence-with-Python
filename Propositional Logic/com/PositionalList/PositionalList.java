package com.PositionalList;
import java.util.Iterator;

public class PositionalList <T> implements PositionalListInterface<T> {

    public int size;
    Position <T> header;    // header and trailer will not be used as position they used as place holder for adding and deleting
    Position <T> tailer;

    public PositionalList(){

        header = new Position<T>();
        tailer = new Position<>();

        header.next = tailer;
        tailer.prev = header;
        
    }

    @Override
    public Position<T> first() {
        
        if(isEmpty()) return null;

        return header.next;

    }

    @Override
    public Position<T> last() {
        
        if(isEmpty()) return null;

        return tailer.prev;
    }

    @Override
    public Position<T> before(Position<T> p) {
        
        if(p == first()) return null;

        return p.prev;

    }

    @Override
    public Position<T> after(Position<T> p) {
        
        if(p == last()) return null;

        return p.next;

    }

    @Override
    public boolean isEmpty() {
        return header.next == tailer;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Position<T> addFirst(T data) {

        Position<T> first = header, second = header.next;

        size++;

        return addBetween(first, second, data);

    }

    @Override
    public Position<T> addLast(T data) {
        
        Position<T> first = tailer.prev, second = tailer;

        size++;

        return addBetween(first, second, data);

    }

    @Override
    public Position<T> addBefore(Position<T> p, T data) {
        
        Position<T> first = p.prev, second = p;

        size++;

        return addBetween(first, second, data);

    }

    @Override
    public Position<T> addAfter(Position<T> p, T data) {
        
        Position<T> first = p, second = p.next;

        size++;

        return addBetween(first, second, data);

    }

    @Override
    public T set(Position<T> p, T data) {

        T replacedElement = p.data;

        p.data = data;

        return replacedElement;

    }

    @Override
    public T remove(Position<T> p) {

        if(isDeleted(p)) return null;

        Position<T> first = p.prev, second = p.next;

        T deletedElement = p.data;

        first.next = second;
        second.prev = first;

        /*
         * this code fragment garantee that deleted element can not be deleted again
         */

        p.next = null;
        p.prev = null;
        p.data = null;

        size--;

        return deletedElement;

    }

    @Override
    public void printList() {
        
        Position<T> temp = header.next;

        System.out.print("[ ");

        while(temp != tailer){

            System.out.print(temp.data+" ");

            temp = temp.next;

        }

        System.out.println("]");

    }
    
    public Position<T> addBetween(Position<T> first, Position<T> second, T data){

        Position<T> newPosition = new Position<T>(first, second, data);

        second.prev = newPosition;
        first.next = newPosition;

        return newPosition;

    }

    public Boolean isDeleted(Position<T> p){

        if(p.next == null && p.prev == null && p.data == null) return true;

        return false;

    }

    @Override
    public boolean equals(Object obj){

        if(obj == null) return false;

        if(obj.getClass() != this.getClass()) return false;

        PositionalList<T> other = (PositionalList<T>) obj;

        if(this.size() != other.size()) return false;

        Iterator<T> first = this.iterator();
        Iterator<T> second = other.iterator();

        while(first.hasNext() && second.hasNext()){

            if(first.next().equals(second.next()) == false) return false;

        }

        return true;

    }

    /*
    * notice that for each loop while running use iterator as underlying process
     */

    private class MyIterator implements Iterator<T>{

        private Position<T> pointer;

        public MyIterator(){

            pointer = header;

        }

        @Override
        public boolean hasNext() {
            return pointer != tailer.prev;
        }

        @Override
        public T next() {

            if(hasNext() == false) return null;

            pointer = pointer.next;

            return pointer.data;

        }

    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    

    
}
