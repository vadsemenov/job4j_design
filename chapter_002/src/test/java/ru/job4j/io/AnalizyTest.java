package ru.job4j.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringJoiner;

public class AnalizyTest {
    @Test
    public void whenParseFile() {
        Analizy analizy = new Analizy();
        String source = "./data/server.file";
        String target = "./data/target.file";
        analizy.unavailable(source, target);

        StringJoiner text = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(target))) {
            read.lines().forEach(text::add);
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringJoiner result = new StringJoiner(System.lineSeparator());
        result.add("10:57:01;10:59:01;");
        result.add("11:01:02;11:02:02;");

        Assert.assertEquals(result.toString(),text.toString());
    }

}