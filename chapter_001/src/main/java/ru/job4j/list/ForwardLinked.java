package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void revert() {
        if (this.head == null) {
            throw new NoSuchElementException();
        }
        Node<T> intermedNode = null;
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            intermedNode = new Node<T>(it.next(), intermedNode);
        }
        this.head = intermedNode;
    }

    public void deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        head = head.next;
    }

    public T deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T res;
        Node<T> tail = head;

        Node<T> previosNode = null;

        while (tail.next != null) {
            previosNode = tail;
            tail = tail.next;
        }

        res = tail.value;
        if (previosNode != null) {
            previosNode.next = null;
        }
        tail = null;
        return res;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}