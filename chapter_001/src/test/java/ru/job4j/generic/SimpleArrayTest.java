package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {

    SimpleArray<Integer> simpleArray;

    @Before
    public void setUp() {
        simpleArray = new SimpleArray<>(10);
        simpleArray.add(0);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
    }

    @Test
    public void whenAddValueInArrayGet3dValue() {
        assertThat(simpleArray.get(3), is(3));
    }

    @Test
    public void whenSet2dValueGetValue() {
        simpleArray.set(2, 56);
        assertThat(simpleArray.get(2), is(56));
    }

    @Test
    public void whenTryTestIterator() {
        Iterator<Integer> it = simpleArray.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(0));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenRemove3dValueGet4() {
        simpleArray.remove(3);
        assertThat(simpleArray.get(3), is(4));
    }
}