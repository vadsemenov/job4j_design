package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс создает Сервер и Сокет для соединения.
 * Если клиент шлет Bye, то Сервер закрывается.
 */
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
                        if (str.equals("GET /?msg=Bye HTTP/1.1")) {
                            break;
                        } else {
                            System.out.println(str);
                        }
                    }
                    if (str.equals("GET /?msg=Bye HTTP/1.1")) {
                        out.write("HTTP/1.1 200 Exit\r\n\\".getBytes());
                        break;
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                }
            }
        }
    }
}