package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;


/**
 * Класс парсит файл конфигураций(ключ-значение)в Map.
 */
public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            read.lines().filter(el -> el.charAt(0) != '#' && !el.equals("") && el.contains("="))
                    .forEach(el -> {
                        String[] splited = el.split("=");
                        values.put(splited[0], splited[1]);
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }


    public String value(String key) {
        return this.values.get(key);
    }
}