package ru.job4j.io;

import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = in.readLine();
                    System.out.println(str);
                    if (str.contains("?msg=Exit")) {
                        server.close();
                    } else if (str.contains("?msg=Hello")) {
                        out.write("Hello\r\n\r\n".getBytes());
                    } else if (str.contains("?msg=")) {
                        out.write("What?\r\n\r\n".getBytes());
                    }
                    out.flush();
                }
            }
        } catch (IOException ie) {
            LOG.error("Exception in log", ie);
        }
    }
}
