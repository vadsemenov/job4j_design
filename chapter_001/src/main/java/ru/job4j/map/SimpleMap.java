package ru.job4j.map;

//8. Реализовать собственную структуру данных - HashMap [#273654]
//Ассоциативный массив на базе хэш-таблицы должен быть унифицирован через генерики и иметь методы:
//boolean insert(K key, V value);
//V get(K key);
//boolean delete(K key);
//
//Реализовывать итератор.
//Внутренняя реализация должна использовать массив. Нужно обеспечить фиксированное
// время вставки и получение. Предусмотрите возможность роста хэш-таблицы при нехватке
// места для нового элемента.
//
//Методы разрешения коллизий реализовывать не надо. Например: если при добавлении ключ
//уже есть, то возвращать false.

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Iterable<V> {

    private int arraySize;
    private Object[] array;
    private int modCount = 0;


    public SimpleMap() {
        arraySize = 10;
        this.array = new Object[arraySize];
    }

    public boolean insert(K key, V value) {
        modCount++;
        int hash = Math.abs(key.hashCode() % this.arraySize);

        if (this.arraySize < hash) {
            extendArray(hash);
        }

        Node node = new Node(hash, key, value);
        if (array[hash] == null) {
            array[hash] = node;
            return true;
        }
        return false;
    }

    private void extendArray(int hash) {
        this.array = Arrays.copyOf(this.array, hash);
    }


    public V get(K key) {
        modCount++;
        int hash = Math.abs(key.hashCode() % this.arraySize);
        if (array[hash] != null) {
            Node node = (Node) array[hash];
            return node.value;
        }
        return null;
    }

    public boolean delete(K key) {
        modCount++;
        int hash = Math.abs(key.hashCode() % this.arraySize);
        if (array[hash] != null) {
            Node node = (Node) array[hash];
            if (node.key.equals(key)) {
                array[hash] = null;
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
