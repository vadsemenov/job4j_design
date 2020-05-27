package ru.job4j.io;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringJoiner;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void drop() throws IOException {
        String source = "./data/server.file";
        File target = folder.newFile("target.txt");

        Analizy analizy = new Analizy();
        analizy.unavailable(source, target.getAbsolutePath());

        StringJoiner text = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(target))) {
            read.lines().forEach(text::add);
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringJoiner result = new StringJoiner(System.lineSeparator());
        result.add("10:57:01;10:59:01;");
        result.add("11:01:02;11:02:02;");

        Assert.assertEquals(result.toString(), text.toString());

    }
}