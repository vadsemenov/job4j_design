package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс создает Сервер и Сокет для соединения.
 * Если клиент шлет Exit, то Сервер закрывается. */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;

                    while (!(str = in.readLine()).isEmpty()) {
                        String[] line = str.split(" ");
                        int index = line[1].lastIndexOf("=");
                        String word = line[1].substring(index + 1);

                        switch (word) {
                            case ("Hello"):
                                out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                                out.write("Hello".getBytes());
                                break;
                            case ("Exit"):
                                out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                                out.write("Exit".getBytes());
                                break;
                            default:
                                out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                                out.write( word.getBytes());
                                break;
                        }

                        if (word.equals("Exit")) {
                            break;
                        } else {
                            System.out.println(str);
                       }
                    }
                    if (str.equals("GET /?msg=Exit HTTP/1.1")) {
                        out.write("HTTP/1.1 200 Exit\r\n\r\n".getBytes());
                        break;
                   }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                }
            }
        }
    }
}