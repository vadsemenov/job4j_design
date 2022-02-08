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
    private List<String> textAnswer = new ArrayList<String>();
    private List<String> textChat = new ArrayList<String>();

    private String answersFileName;
    private String targetFileName;

    private static final String OUT = "закончить";
    private static final String CONTINUE = "продолжить";
    private static final String STOP = "стоп";


    public ConsoleChat(String answersFileName, String targetFileName) {
        this.answersFileName = answersFileName;
        /*this.answersFileName = "./chapter_002/data/chatAnswers.txt";*/
        this.targetFileName = targetFileName;

        fillTheAnswers();
    }

    public void fillTheAnswers() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.answersFileName))) {
            in.lines().forEach(textAnswer::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeChat() {
        if (textChat.size() > 0) {
            try (FileOutputStream out = new FileOutputStream(this.targetFileName)) {
                for (String str : textChat) {
                    out.write(str.getBytes());
                    out.write(System.lineSeparator().getBytes());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void chat() {

        Scanner scanner = new Scanner(System.in);
        boolean isGetAnswer = true;
        String answer = "";

        while (!answer.equals(OUT)) {
            answer = scanner.nextLine();
            textChat.add(answer);
            textChat.add(System.lineSeparator());

            if (answer.equals(STOP)) {
                isGetAnswer = false;
            } else if (answer.equals(CONTINUE)) {
                isGetAnswer = true;
            } else if (answer.equals(OUT)) {
                break;
            }

            if (isGetAnswer) {
                answer = randomAnswer();
                System.out.println(answer);
                textChat.add(answer);
                textChat.add(System.lineSeparator());
            }
        }
        writeChat();
    }

    public String randomAnswer() {
        return textAnswer.get(new Random().nextInt(textAnswer.size()));
    }

}
