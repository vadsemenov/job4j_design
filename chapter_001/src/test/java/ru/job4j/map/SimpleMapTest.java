package ru.job4j.map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;

public class SimpleMapTest {

    SimpleMap<String, Integer> simpleMap;

    @Before
    public void makeSimpleMap() {
        simpleMap = new SimpleMap<String, Integer>();
        simpleMap.insert("Vadim", 40);
        simpleMap.insert("Vlad", 42);
        simpleMap.insert("Boris", 39);
        simpleMap.insert("Ivan", 37);
        simpleMap.insert("Tom", 39);
    }

    @Test
    public void getValues() {
        Assert.assertThat(simpleMap.get("Vadim"), is(40));
    }

    @Test
    public void iterateValues() {
        Iterator<Integer> it = simpleMap.iterator();
        Assert.assertTrue(it.hasNext());
        it.next();
        Assert.assertTrue(it.hasNext());
        it.next();
        Assert.assertTrue(it.hasNext());
        it.next();
        Assert.assertTrue(it.hasNext());
        it.next();
    }

    @Test
    public void deleteValue() {
        Assert.assertTrue(simpleMap.delete("Vadim"));
        Assert.assertFalse(simpleMap.delete("Vadim"));
        Assert.assertTrue(simpleMap.delete("Tom"));
    }

    @Test
    public void insertValue() {
        Assert.assertTrue(simpleMap.insert("Petr", 40));
        Assert.assertTrue(simpleMap.insert("Vladimir", 30));
    }

}