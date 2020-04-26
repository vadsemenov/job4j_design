package ru.job4j.list;


import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleLinkedTest {

    @Test
    public void whenAddAndThenGetValue() {
        SimpleLinked<Integer> simpleLinked = new SimpleLinked<>();
        simpleLinked.add(0);
        simpleLinked.add(1);
        simpleLinked.add(2);
        simpleLinked.add(3);
        assertThat(simpleLinked.get(2), is(2));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenTryAddWhenIterate() {
        SimpleLinked<Integer> simpleLinked = new SimpleLinked<>();
        simpleLinked.add(0);
        Iterator<Integer> it = simpleLinked.iterator();
        simpleLinked.add(1);
        it.next();
    }

    @Test//(expected = NoSuchElementException.class)
    public void whenTryIterateAndDontHasNext() {
        SimpleLinked<Integer> simpleLinked = new SimpleLinked<>();
        simpleLinked.add(0);
        simpleLinked.add(1);
        Iterator<Integer> it = simpleLinked.iterator();
       MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(0));
        MatcherAssert.assertThat(it.next(), is(1));
    }


}