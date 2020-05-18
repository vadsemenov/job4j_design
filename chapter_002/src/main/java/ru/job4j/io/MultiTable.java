package ru.job4j.io;

import java.io.FileOutputStream;

/**
 * Класс выводит таблицу умножения в файл.
 */
public class MultiTable {

    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                text.append(i + "x" + j + "=" + (i * j) + System.lineSeparator());
            }
            text.append(System.lineSeparator());
        }
        writeToFile("multTab", text);
    }

    public static void writeToFile(String fileName, StringBuilder text) {
        try (FileOutputStream out = new FileOutputStream(fileName + ".txt")) {
            out.write(text.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
