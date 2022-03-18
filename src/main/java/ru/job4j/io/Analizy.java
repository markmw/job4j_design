package ru.job4j.io;

import java.io.*;
import java.util.Stack;

public class Analizy {
    public static void unavailable(String source, String target) {
        try (
                BufferedReader in = new BufferedReader(new FileReader(source));
                PrintWriter out = new PrintWriter(new FileOutputStream(target))
        ) {
            Stack<String> temp = new Stack<>();
            in.lines()
                    .map(line -> line.split(" "))
                    .forEach(line -> {
                        if (("400".equals(line[0]) || "500".equals(line[0])) && temp.isEmpty()) {
                            temp.push(line[1]);
                        } else if ("200".equals(line[0]) && temp.size() == 1) {
                            String rsl = temp.pop() + ";" + line[1];
                            out.println(rsl);
                        }
                    });

        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public static void main(String[] args) {
        unavailable("./data/server.log", "./data/unavalaible.csv");
    }
}
