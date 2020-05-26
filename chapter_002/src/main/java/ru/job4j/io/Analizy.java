package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс парсит файл и выбирает нерабочие промежутки сервера
 */
public class Analizy {

    public void unavailable(String source, String target) {
        List<String> text = new LinkedList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {

            String[] splited;
            boolean isUnavaliable = false;
            String start = "";
            String end = "";
            String line = "";
            while ((line = read.readLine()) != null) {
                if (!line.equals("") && line.contains(" ")) {
                    splited = line.split(" ");
                    if (splited[0].equals("400") || splited[0].equals("500")) {
                        if (!isUnavaliable) {
                            start = splited[1];
                            isUnavaliable = true;
                        }
                    } else {
                        if (isUnavaliable) {
                            end = splited[1];
                            isUnavaliable = false;
                            text.add(start + ";" + end + ";");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            for (String string : text) {
                out.println(string);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}