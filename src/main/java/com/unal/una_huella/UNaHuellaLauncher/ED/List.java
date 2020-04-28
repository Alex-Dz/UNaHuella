package com.unal.una_huella.UNaHuellaLauncher.ED;

public interface List<T> {
    public long size();
    public boolean isEmpty();
    public void pushFront(T key);
    public void pushBack(T key);
    public T topFront();
    public void popFront();
    public T topBack();
    public void popBack();
    public boolean find(T key);
    public void delete(T key);
    public void addBefore(NodoList node, T key);
    public void addAfter(NodoList node, T key);
}
