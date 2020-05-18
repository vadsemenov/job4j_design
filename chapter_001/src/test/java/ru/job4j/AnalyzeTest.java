package ru.job4j;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static ru.job4j.Analyze.User;

public class AnalyzeTest {

    List<User> previous;
    List<User> current;

    @Before
    public void createUserLists() {

        previous = List.of(
                new User(1, "Vladimir"),
                new User(2, "Petr"),
                new User(3, "Ivan"));

        current = List.of(
                new User(1, "Vladimir"),
                new User(3, "Sergey"),
                new User(4, "Nikolay"),
                new User(5, "Vadim"));

    }

    @Test
    public void whenCompareDifferentListOfUsers() {
        Analyze analyze = new Analyze();
        Analyze.Info diffInfo = analyze.diff(previous, current);

        Assert.assertEquals(2, diffInfo.added);
        Assert.assertEquals(1, diffInfo.deleted);
        Assert.assertEquals(1, diffInfo.changed);
    }


}