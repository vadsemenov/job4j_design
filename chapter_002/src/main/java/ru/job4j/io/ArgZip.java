package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс архивирует директорию в *.zip
 * java -jar pack.jar -d=c:\project\job4j\ -e=class -o=project.zip
 */
public class ArgZip {

    private final String[] args;
    private String directory;
    private String exclude;
    private String output;


    public ArgZip(String[] args) {
        this.args = args;
        valid();
        this.directory = findDirectory();
        this.exclude = findExclude();
        this.output = findOutput();
    }

    public static void main(String[] args) throws IOException {
        new ArgZip(args).zipFiles();
    }

    public void zipFiles() throws IOException {
        List<Path> paths = Search.search(Paths.get(this.directory), this.exclude);
        List<File> files = new ArrayList<>();
        for (Path file : paths) {
            files.add(file.toFile());
        }
        new Zip().packFiles(files, new File(this.output));
    }

    public String findDirectory() {
        return findArgument(args[0]);
    }

    public String findExclude() {
        return findArgument(args[1]);
    }

    public String findOutput() {
        return findArgument(args[2]);
    }

    public String findArgument(String arg) {
        String[] splited = arg.split("=");
        return splited[1];
    }

    public void valid() {
        if (this.args.length < 3) {
            throw new IllegalArgumentException("Arguments is incorrect. Usage java -jar dir.jar ROOT_FOLDER EXCLUDE_FILE OUTPUT_FILEWS.");
        }
    }

    public String directory() {
        return this.directory;
    }

    public String exclude() {
        return this.exclude;
    }

    public String output() {
        return this.output;
    }
}