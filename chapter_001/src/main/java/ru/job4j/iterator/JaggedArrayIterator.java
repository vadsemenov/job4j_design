package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;


public class JaggedArrayIterator implements Iterator {

    private final int[][] values;
    private final int arrayLength;
    private int index = 0;

    public JaggedArrayIterator(final int[][] values) {
        this.values = values;

//        int tempIndex = 0;
//        for (int i = 0; i < this.values.length; i++) {
//            for (int j = 0; j < this.values[i].length; j++) {
//                tempIndex++;
//            }
//        }
//        this.arrayLength = tempIndex;

        this.arrayLength = (int) Arrays.stream(this.values).flatMap(e -> Stream.of(e)).count() + 1;

    }


    @Override
    public boolean hasNext() {

        return this.arrayLength > this.index;
    }

    @Override
    public Object next() {
//        int tempIndex = 0;
//        for (int i = 0; i < this.values.length; i++) {
//            for (int j = 0; j < this.values[i].length; j++) {
//                if (tempIndex == this.index) {
//                    index++;
//                    return values[i][j];
//                }
//                ++tempIndex;
//            }
//        }
//
//        return null;
        return Arrays.stream(this.values).flatMapToInt(Arrays::stream).skip(index++).findFirst().getAsInt();
    }
}
