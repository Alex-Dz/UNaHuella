package com.unal.una_huella.UNaHuellaLauncher.ED;

public class NodoStack<T> {
    public T key;
    public NodoStack next;

    public NodoStack(){
        this.key = null;
        this.next = null;
    }

    public NodoStack(T key, NodoStack next){
        this.key = key;
        this.next = next;
    }
}
