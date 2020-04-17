package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class JaggedArrayIterator implements Iterator {

    private final int[][] values;
    private int row = 0;
    private int coloumn = 0;

    public JaggedArrayIterator(final int[][] values) {
        this.values = values;
//        this.arrayLength = (int) Arrays.stream(this.values).flatMap(e -> Stream.of(e)).count() + 1;
    }


    @Override
    public boolean hasNext() {
        return this.values.length > row;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Object res = this.values[this.row][this.coloumn++];
        if (values[row].length == coloumn) {
            row++;
            coloumn = 0;

        }
        return res;
        //       return Arrays.stream(this.values).flatMapToInt(Arrays::stream).skip(index++).findFirst().getAsInt();
    }
}
