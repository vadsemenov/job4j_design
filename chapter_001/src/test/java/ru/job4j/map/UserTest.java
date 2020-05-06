package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    @Test
    public void whenPutInTheMap() {
        User first = new User("Vadim", 1, new GregorianCalendar(2020, 04, 30));
        User second = new User("Vadim", 1, new GregorianCalendar(2020, 04, 30));
        Map<User, Object> map = new HashMap<>();
        map.put(first, "first");
        map.put(second, "Second");
        System.out.println(map);
    }
}