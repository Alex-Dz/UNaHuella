package com.unal.una_huella.UNaHuellaLauncher.ED;

public class NodoTree<T> {
    private T key;
    private NodoTree<T> left;
    private NodoTree<T> right;
    private NodoTree<T> parent;
    private int height;

    public NodoTree() {
        this.key = null;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.height = 0;
    }

    public NodoTree(T key) {
        this.key = key;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.height = 0;
    }

    public NodoTree(T key, NodoTree<T> left, NodoTree<T> right) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.parent = null;
        this.height = 0;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public NodoTree<T> getLeft() {
        return left;
    }

    public void setLeft(NodoTree<T> left) {
        this.left = left;
    }

    public NodoTree<T> getRight() {
        return right;
    }

    public void setRight(NodoTree<T> right) {
        this.right = right;
    }

    public NodoTree<T> getParent() {
        return parent;
    }

    public void setParent(NodoTree<T> parent) {
        this.parent = parent;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}