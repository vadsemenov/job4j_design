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

import java.util.Iterator;

public class SimpleHashMap<K, V> implements Iterable<V> {

    public boolean insert(K key, V value) {
        return false;
    }


    public V get(K key) {
        return null;
    }

    public boolean delete(K key) {
        return false;
    }


    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public V next() {
                return null;
            }
        };
    }
}
