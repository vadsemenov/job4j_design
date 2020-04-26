package ru.job4j.generic;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Пример универсальной обертки над массивом.
 *
 * @param <T>
 */
public class SimpleArray<T> implements Iterable<T> {

    private Object[] data;
    private int index = 0;
    private int size = 0;
    private int modCount = 0;

    public SimpleArray(int size) {
        this.data = new Object[size];
    }


    public void add(T model) {
        this.data[index++] = model;
        size++;
    }


    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        data[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(this.data, index + 1, data, index, this.data.length - index - 1);
        this.index--;
        this.size--;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) data[index];
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
                return data.length > this.i;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) data[i++];
            }
        };
    }
}
