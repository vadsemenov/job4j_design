package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinked<T> implements Iterable<T> {
    private Node<T> first;
    private int size = 0;
    private int index = 0;
    private int modCount = 0;


    public void add(T value) {
        modCount++;
        Node<T> node = new Node<T>(value, null);
        if (first == null) {
            first = node;
            return;
        }
        Node<T> tail = first;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        size++;
        index++;
    }

    public T get(int index) {
        if (index < size) {
            Node<T> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x.value;
        }
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final int expectedModCount = modCount;
            private int i = 0;

            @Override
            public boolean hasNext() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return size >= this.i && size != 0;
            }

            @Override
            public T next() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                Node<T> x = first;
                if (size > 1) {
                    for (int j = 0; j < index; j++) {
                        x = x.next;
                    }
                }
                this.i++;
                return x.value;
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
