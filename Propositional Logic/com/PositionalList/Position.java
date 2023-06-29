package com.PositionalList;

public class Position<T> {

    public Position<T> prev;
    public Position<T> next;
    public T data;

    public Position(Position<T> prev,Position<T> next,T data){

        this.data = data;
        this.next = next;
        this.prev = prev;

    }

    public Position(){
        
    }

    public String toString(){
        return " Data : "+data;
    }

}
