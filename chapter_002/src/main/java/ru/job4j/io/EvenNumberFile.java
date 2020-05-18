package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }

            String[] numbers = text.toString().split(System.lineSeparator());
            for (String number : numbers) {
                if (isEven(Integer.valueOf(number))) {
                    System.out.println(number + "-четное число");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
}
