package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator {
    private final int[] values;
    private int index = 0;

    public EvenIterator(final int[] values) {
        this.values = values;
    }


    @Override
    public boolean hasNext() {
        if (index < 0 || values.length <= index) {
            return false;
        }

        int nextIndex = -1;

        for (int i = index; i < this.values.length; i++) {
            if (this.values[i] % 2 == 0) {
                nextIndex = i;
                break;
            }
        }

        index = nextIndex;

        return index >= 0;

        //        if (Arrays.stream(this.values).filter(e -> e % 2 == 0).skip(index).count() > 0) {
//            return true;
//        }
//        return false;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return values[index++];
        // return Arrays.stream(this.values).filter(e -> e % 2 == 0).skip(index++).findFirst().getAsInt();
    }

}

