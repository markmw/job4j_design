package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        validate(argsName);
        List<String[]> list = new ArrayList<>();
        String delimiter = argsName.get("delimiter");
        try (Scanner scanner = new Scanner(
                new FileInputStream(argsName.get("path")), StandardCharsets.UTF_8)
                .useDelimiter(delimiter + "|" + System.lineSeparator());
             FilterOutputStream fileOut = "stdout".equals(argsName.get("out"))
                     ? System.out
                     : new BufferedOutputStream(new FileOutputStream(argsName.get("out")));
             PrintWriter out = new PrintWriter(fileOut, true, StandardCharsets.UTF_8)) {
            while (scanner.hasNext()) {
                list.add(scanner.nextLine().split(delimiter));
            }
            List<String> filters = Arrays.asList(argsName.get("filter").split(","));
            for (String[] strings : list) {
                StringJoiner joiner = new StringJoiner(delimiter);
                for (int i = 0; i < strings.length; i++) {
                    if (filters.contains(list.get(0)[i])) {
                        joiner.add(strings[i]);
                    }
                }
                out.println(joiner);
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    private static void validate(ArgsName argsName) {
        if (!new File(argsName.get("path")).exists() || !argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("Path doesn't exist or not file");
        }
        if (!";".equals(argsName.get("delimiter"))) {
            throw new IllegalArgumentException("Haven't one of needed arguments.");
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        if (args.length != 4) {
            throw new IllegalArgumentException(
                    "Invalid arguments. Required format - '-path= -delimiter= -out= -filter='"
            );
        }
        handle(argsName);
    }
}
