package com.unal.una_huella.UNaHuellaLauncher.ED;

public class LinkedStack<T> implements Stack<T> {
    private NodoStack<T> top;
    private int size;

    public LinkedStack() {
        this.top = new NodoStack<T>();
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (top.next == null && top.key == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void push(T key) {
        NodoStack<T> nodo = new NodoStack<T>(key, this.top);
        this.top = nodo; // sube el top al nuevo
        size++;
    }

    @Override
    public T pop() {
        T temp;
        if (isEmpty()) {
            System.out.println("Pila Vacia...");
            return null;
        } else {
            temp = this.top.key;
            this.top = this.top.next;
            size--;
            return temp;
        }
    }

    @Override
    public T top() {
        T temp;
        if (isEmpty()) {
            System.out.println("Pila Vacia");
            return null;
        } else {
            temp = this.top.key;
            return temp;
        }
    }
}