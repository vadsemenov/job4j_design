package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Класс для поиска файлов в директории, исключая файлы с расширением *.js
 */
public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Root folder is null or extension is null. Usage java -jar search.jar ROOT_FOLDER EXTENSION.");
        }
        //Path start = Paths.get(args[0]);
        //String ext = args[1];
        Path start = Paths.get(".");
        String ext = "js";
        search(start, ext).forEach(System.out::println);
    }

    public static List<Path> search(Path root, String ext) throws IOException {

        SearchFiles searcher = new SearchFiles(p -> !p.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}