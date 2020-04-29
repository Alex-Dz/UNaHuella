package com.unal.una_huella.UNaHuellaLauncher.ED;

import java.util.Iterator;

public class ListIterator<T> implements Iterator<T> {
    
    private DoubleLinkedList list;
    long positionList;
    NodoList<T> node;

    public ListIterator(DoubleLinkedList list) {
        this.list = list;
        positionList = 0;
        node = list.getHead();
    }

    

    @Override
    public boolean hasNext() {

        if (node.next != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T next() {
        positionList++;
        if (node.next != null) {
            node = node.next;
            return node.prev.key;
        } else {
            return node.key;
        }

    }

    @Override
    public void remove() {
        Iterator.super.remove(); //To change body of generated methods, choose Tools | Templates.
    }

}
