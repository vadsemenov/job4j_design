package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Iterable<V> {

    private static final double LOAD_FACTOR = 0.75f;
    private int arraySize;
    private Object[] array;
    private int modCount = 0;

    public SimpleMap() {
        arraySize = 16;
        this.array = new Object[arraySize];
    }

    public boolean insert(K key, V value) {
        modCount++;
        int hash = 31 * key.hashCode();
        int index = Math.abs(key.hashCode() % this.arraySize);

        if (index >= (int) LOAD_FACTOR * this.arraySize) {
            extendArray(index);
        }

        Node node = new Node(hash, key, value);
        if (array[index] == null) {
            array[index] = node;
            return true;
        }
        if (array[index] != null) {
            Node currentNode = (Node) array[index];
            if (node.key.equals(currentNode.key)) {
                array[index] = node;
                return true;
            }
        }
        return false;
    }

    private void extendArray(int newIndex) {
        System.arraycopy(this.array, 0, this.array, 0, newIndex);
    }


    public V get(K key) {
        int index = Math.abs(key.hashCode() % this.arraySize);
        if (array[index] != null) {
            Node node = (Node) array[index];
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    public boolean delete(K key) {
        modCount++;
        int index = Math.abs(key.hashCode() % this.arraySize);
        if (array[index] != null) {
            Node node = (Node) array[index];
            if (node.key.equals(key)) {
                array[index] = null;
                return true;
            }
        }

        return false;
    }


    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            private final int expectedModCount = modCount;
            int index = 0;

            @Override
            public boolean hasNext() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                int nextIndex = -1;
                for (int i = index; i < array.length; i++) {
                    if (array[i] != null) {
                        nextIndex = i;
                        break;
                    }
                }
                index = nextIndex;
                return index >= 0;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node node = (Node) array[index++];
                return node.value;
            }
        };
    }

    private class Node {
        public int hash;
        public K key;
        public V value;

        public Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }
}
