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
                if (integer != null) {
                    return true;
                }

                if (this.inIndex != null) {
                    while (it.hasNext() || this.inIndex.hasNext()) {
                        if (!this.inIndex.hasNext()) {
                            this.inIndex = it.next();
                        }

                        if (this.inIndex.hasNext()) {
                            this.integer = inIndex.next();
                            if (this.integer != null) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            }


            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Integer res = integer;
                integer = null;
                return res;
            }
        };
    }
}