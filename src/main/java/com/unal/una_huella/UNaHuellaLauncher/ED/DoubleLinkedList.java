package com.unal.una_huella.UNaHuellaLauncher.ED;

import java.util.Iterator;

public class DoubleLinkedList<T> implements List<T> {

    private long size;
    private NodoList<T> head;
    private NodoList<T> tail;

    public DoubleLinkedList() {
        size = 0;
        this.head = null;
        this.tail = null;
    }

    @Override
    public long size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (head == tail && size == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void pushFront(T key) {
        NodoList<T> newNode = new NodoList<T>(key, head, null);
        newNode.next.prev = newNode;
        head = newNode;
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    @Override
    public void pushBack(T key) {
        NodoList<T> newNode = new NodoList<T>(key, null, null);
        if (tail == null) {
            head = tail = newNode;
            newNode.prev = null;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public T topFront() {
        if (head != null) {
            return head.key;
        } else {
            return null;
        }
    }

    @Override
    public void popFront() {
        if (head == null) {
            System.out.println("( ._.)/");
        }
        head.next.prev = null;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
    }

    @Override
    public T topBack() {
        if (tail != null) {
            return tail.key;
        } else {
            return null;
        }
    }

    @Override
    public void popBack() {
        if (head == null) {
            System.out.println("( ._.)/");
        }
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
    }

    @Override
    public boolean find(T key) {
        NodoList<T> temp = head;
        long i = 0;
        while (i < size) {
            if (temp.key == key) {
                return true;
            }
            temp = temp.next;
            i++;
        }
        return false;
    }

    public NodoList findNode(T key) {
        NodoList<T> temp = head;
        long i = 0;
        while (i < size) {
            if (temp.key == key) {
                return temp;
            }
            temp = temp.next;
            i++;
        }
        return null;
    }

    @Override
    public void delete(T key) {
        NodoList temp = findNode(key);
        if (temp != null) {
            if (head == tail) {
                popBack();
            } else {
                if (temp == head) {
                    temp.next.prev = temp.prev;
                    head = temp.next;
                    temp.next = null;
                } else if (temp == tail) {
                    temp.prev.next = temp.next;
                    tail = temp.prev;
                    temp.prev = null;
                } else {
                    temp.next.prev = temp.prev;
                    temp.prev.next = temp.next;
                    temp.next = null;
                }
            }
        }
    }

    @Override
    public void addBefore(NodoList node, T key) {
        NodoList<T> newNode = new NodoList<T>(key, node, node.prev);
        node.prev = newNode;
        if (newNode.prev != null) {
            newNode.prev.next = newNode;
        }
        if (head == node) {
            head = newNode;
        }
    }

    @Override
    public void addAfter(NodoList node, T key) {
        NodoList<T> newNode = new NodoList<T>(key, node.next, node);
        node.next = newNode;
        if (newNode.next != null) {
            newNode.next.prev = newNode;
        }
        if (tail == node) {
            tail = newNode;
        }
    }

    public NodoList<T> getHead() {
        return head;
    }

    public NodoList<T> getTail() {
        return tail;
    }

    public Iterator<T> iterator() {
        Iterator<T> it = new ListIterator<T>(this);
        return it;
    }

}
