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
        if (node == null) {
            return false;
        }
        if (node.next != null) {
            return true;
        } else if (node == list.getTail()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T next() {
        positionList++;
        T value;
        if (node == list.getTail() || node.next != null) {
            value = node.key;
            node = node.next;
            return value;
        } else {
            value = node.key;
            return value;
        }

    }

    @Override
    public void remove() {
        Iterator.super.remove(); //To change body of generated methods, choose Tools | Templates.
    }

}
