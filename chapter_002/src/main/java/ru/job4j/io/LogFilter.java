package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс выводит строчки из файла, где предпоследнее число 404
 */
public class LogFilter {
    public static List<String> filter(String file) {

        try (BufferedReader in = new BufferedReader(new FileReader("log.txt"))) {
            List<String> lines = new ArrayList<String>();
            List<String> result = new ArrayList<String>();
            in.lines().forEach(lines::add);
            for (String line : lines) {
                String[] separetedLine = line.split(" ");
                if (separetedLine[separetedLine.length - 2].equals("404")) {
                    result.add(line);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}