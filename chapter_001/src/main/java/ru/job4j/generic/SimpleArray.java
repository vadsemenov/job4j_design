package ru.job4j.generic;

import java.util.Iterator;

/**
 * Пример универсальной обертки над массивом.
 *
 * @param <T>
 */
public class SimpleArray<T> implements Iterable<T> {

    private Object[] data;
    private int index = 0;

    public SimpleArray(int size) {
        this.data = new Object[size];
    }


    public void add(T model) {
        if (index > this.data.length) {
            throw new IndexOutOfBoundsException();
        }
        this.data[index++] = model;
    }


    public void set(int index, T model) {
        if (index > this.data.length) {
            throw new IndexOutOfBoundsException();
        }
        data[index] = model;
    }

    public void remove(int index) {
        if (index > this.data.length) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(this.data, index + 1, data, index, this.data.length - index - 1);
    }

    public T get(int index) {
        if (index > this.data.length) {
            throw new IndexOutOfBoundsException();
        }
        return (T) data[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return data.length > this.i;
            }

            @Override
            public T next() {
                return (T) data[i++];
            }
        };
    }
}
