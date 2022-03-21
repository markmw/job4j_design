package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        String pattern = "(?<=msg=)\\w+?(?=\\s)";
                        Matcher matcher = Pattern.compile(pattern).matcher(str);
                        if (matcher.find()) {
                            String text = matcher.group();
                            if ("Hello".equals(text)) {
                                out.write("Hello".getBytes());
                            } else if ("Exit".equals(text)) {
                                server.close();
                            } else {
                                out.write("What".getBytes());
                            }
                        }
                    }
                    out.flush();
                }
            }
        }
    }
}
