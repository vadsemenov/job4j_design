package ru.job4j.io;

import java.io.FileOutputStream;

/**
 * Пример записи в файл
 */
public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write("Hello, world!".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}