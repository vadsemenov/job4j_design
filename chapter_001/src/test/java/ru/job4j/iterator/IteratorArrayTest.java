package ru.job4j.iterator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class IteratorArrayTest {
    @Test
    public void whenGetNextCallShouldPointForward(){
        IteratorArray iteratorArray = new IteratorArray(new int[]{1,3} );
        iteratorArray.next();
        int result =(Integer) iteratorArray.next();
        assertThat(result,is(3));
    }

    @Test
    public void whenCheckNextPositionShouldReturnContantValue(){
        IteratorArray iteratorArray = new IteratorArray(new int[]{1} );
        iteratorArray.next();
        boolean result = iteratorArray.hasNext();
        assertThat(result,is(false));
    }
}