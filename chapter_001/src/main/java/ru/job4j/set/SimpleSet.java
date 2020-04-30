package ru.job4j.set;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSet<E> implements Iterable<E> {
    private Object[] container;
    private int size = 0;
    private int index = 0;
    private int modCount = 0;

    public SimpleSet() {
        this.container = new Object[100];
    }

    public void add(E model) {
        modCount++;
        checkCapacity();
        if (!IsUniqueValue(model)) {
            return;
        }
        this.container[index] = model;
        this.size += 1;
        this.index++;
    }

    private boolean IsUniqueValue(E model) {
        for (int i = 0; i < this.size; i++) {
            if (this.container[i] == model) {
                return false;
            }
        }
        return true;
    }

    private void checkCapacity() {
        if (index > this.container.length + 1) {
            this.container = Arrays.copyOf(this.container, this.container.length + 10);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
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
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) container[i++];
            }
        };
    }
}
