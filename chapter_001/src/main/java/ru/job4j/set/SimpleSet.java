package ru.job4j.set;

import ru.job4j.list.SimpleArray;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {

    private SimpleArray<E> simpleArray = new SimpleArray<>();

    public void add(E model) {
        if (!isUniqueValue(model)) {
            return;
        }
        simpleArray.add(model);
    }

    private boolean isUniqueValue(E model) {
        for (int i = 0; i < this.simpleArray.getSize(); i++) {
            if (this.simpleArray.get(i).equals(model)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return simpleArray.iterator();
    }
}
