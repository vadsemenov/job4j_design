package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int size = 0;
    private int index = 0;
    private int modCount = 0;

    public SimpleArray() {
        this.container = new Object[100];
    }

    public T get(int index) {
        if (this.size == 0 || index >= this.index) {
            throw new NoSuchElementException();
        }
        return (T) this.container[index];
    }

    public void add(T model) {
        modCount++;
        checkCapacity();
        this.container[index] = model;
        size += 1;
        index++;
    }

    private void checkCapacity() {
        if (index > this.container.length + 1) {
            this.container = Arrays.copyOf(this.container, this.container.length + 10);
        }
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
                return container.length > this.i && size != 0;
            }

            @Override
            public T next() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[i++];
            }
        };
    }
}