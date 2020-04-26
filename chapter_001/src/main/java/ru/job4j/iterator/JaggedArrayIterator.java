package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class JaggedArrayIterator implements Iterator<Integer> {

    private final int[][] values;
    private int row = 0;
    private int coloumn = 0;

    public JaggedArrayIterator(final int[][] values) {
        this.values = values;
//        this.arrayLength = (int) Arrays.stream(this.values).flatMap(e -> Stream.of(e)).count() + 1;
    }

    @Override
    public boolean hasNext() {
        if (this.row >= this.values.length) {
            return false;
        }

        while (this.values[row].length == 0) {
            this.row += 1;
            if (this.row >= this.values.length) {
                break;
            }
        }
        return this.values.length > this.row;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer res = this.values[this.row][this.coloumn++];
        if (this.values[row].length == this.coloumn) {
            if (this.values.length > this.row) {
                this.row++;
            }
            this.coloumn = 0;
        }
        return res;
        //       return Arrays.stream(this.values).flatMapToInt(Arrays::stream).skip(index++).findFirst().getAsInt();
    }
}
