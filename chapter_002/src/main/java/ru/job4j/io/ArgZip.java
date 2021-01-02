package ru.job4j.io;

/**
 * Класс разбивает аргументы коммандной строки для zip архива.
 * java -jar pack.jar -d c:\project\job4j\ -e class -o project.zip
 */
public class ArgZip {

    private final String[] args;
    private String directory;
    private String exclude;
    private String output;


    public ArgZip(String[] args) {
        this.args = args;
        valid();
        findArgument();
    }

    private void findArgument() {
        this.directory = args[1];
        this.exclude = args[3];
        this.output = args[5];
    }

    public void valid() {
        if (this.args.length < 6) {
            throw new IllegalArgumentException("Arguments is incorrect. Usage java -jar dir.jar OPTIONS.");
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