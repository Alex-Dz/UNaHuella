package com.unal.una_huella.UNaHuellaLauncher.ED;

public class NodoList<T> {

    public T key;
    public NodoList next;
    public NodoList prev;

    public NodoList() {
        this.key = null;        //  nudes = nulls
        this.next = null;       
        this.prev = null;
    }

    public NodoList(T key, NodoList next, NodoList prev) {
        this.key = key;
        this.next = next;
        this.prev = prev;
    }
}
