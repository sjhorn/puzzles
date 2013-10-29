package com.hornmicro;

public class Node<T extends Comparable<?>> {
    public Node<T> left;
    public Node<T> right;
    public T value;
    
    public Node() { 
        
    }
    
    public Node(T value) {
        this.value = value;
    }

}
