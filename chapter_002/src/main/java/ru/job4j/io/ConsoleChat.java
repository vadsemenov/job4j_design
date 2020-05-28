package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Класс - консольный чат с ботом из текстового файла
 */
public class ConsoleChat {
    private List<String> text = new ArrayList<String>();
    private String answersFileName;
    private String targetFileName;


    public ConsoleChat(String answersFileName, String targetFileName) {
        this.answersFileName = answersFileName;
        //Почему файл не читается? Как мне правильно задать путь?
        //this.answersFileName = "./data/chatAnswers.txt";
        this.targetFileName = targetFileName;

        fillTheAnswers();
        chat();
    }

    public void fillTheAnswers() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.answersFileName))) {
            in.lines().forEach(text::add);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void chat() {

        Scanner scanner = new Scanner(System.in);
        boolean isGetAnswer = true;
        String answer = "";

        try (FileOutputStream out = new FileOutputStream(this.targetFileName)) {
            while (!answer.equals("закончить")) {
                answer = scanner.nextLine();
                out.write(answer.toString().getBytes());
                out.write(System.lineSeparator().getBytes());

                if (answer.equals("стоп")) {
                    isGetAnswer = false;
                } else if (answer.equals("продолжить")) {
                    isGetAnswer = true;
                } else if (answer.equals("закончить")) {
                    break;
                }

                if (isGetAnswer) {
                    answer = randomAnswer();
                    System.out.println(answer);
                    out.write(answer.getBytes());
                    out.write(System.lineSeparator().getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String randomAnswer() {
        return text.get(new Random().nextInt(text.size()));
    }

}
