package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        List<String> log = new ArrayList<>();
        String question = scanner.nextLine();
        List<String> list = readPhrases();
        log.add(question);
        while (!OUT.equals(question)) {
            if (STOP.equals(question)) {
                while (!CONTINUE.equals(question)) {
                    question = scanner.nextLine();
                    log.add(question);
                }
            }
            int i = ThreadLocalRandom.current().nextInt(0, list.size());
            String answer = list.get(i);
            System.out.println(answer);
            log.add(answer);
            question = scanner.nextLine();
            log.add(question);
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            in.lines().forEach(rsl::add);
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(out::println);
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/bot.log", "./data/bot_answer.txt");
        cc.run();
    }
}
