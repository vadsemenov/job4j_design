package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            private Iterator<Integer> inIndex = conv();
            private Integer integer = null;

            public Iterator<Integer> conv() {
                if (it.hasNext()) {
                    return it.next();
                }
                return null;
            }

            @Override
            public boolean hasNext() {
                return this.inIndex != null && this.inIndex.hasNext();
            }


            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Integer i = inIndex.next();
                while (inIndex != null && !inIndex.hasNext()) {
                    inIndex = it.hasNext() ? it.next() : null;
                }
                return i;
            }
        };
    }
}