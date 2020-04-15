package ru.job4j.iterator;


import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

public class EvenIterator implements Iterator {
    private final int[] values;
    private int index = 0;

    public EvenIterator(final int[] values) {
        this.values = values;
    }


    @Override
    public boolean hasNext() {
        if (Arrays.stream(this.values).filter(e -> e % 2 == 0).skip(index).count() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Object next() {
        return Arrays.stream(this.values).filter(e -> e % 2 == 0).skip(index++).findFirst().getAsInt();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEachRemaining(Consumer action) {
        throw new UnsupportedOperationException();
    }
}

