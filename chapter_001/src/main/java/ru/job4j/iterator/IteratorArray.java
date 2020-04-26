package ru.job4j.iterator;

import java.util.Iterator;

public class IteratorArray implements Iterator {
    private final int[] value;
    private int index = 0;

    public IteratorArray(final int[] value) {
        this.value = value;
    }

    @Override
    public boolean hasNext() {
        return value.length > index;
    }

    @Override
    public Object next() {
        return value[index++];
    }
}
