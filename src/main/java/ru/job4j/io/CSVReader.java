package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        List<String[]> list = new ArrayList<>();
        String delimiter = argsName.get("delimiter");
        if (!delimiter.equals(";")) {
            throw new IllegalArgumentException("Haven't one of needed arguments.");
        }
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

    public static void validate(Path path) {
        if (!path.toFile().exists() || !path.toFile().isFile()) {
            throw new IllegalArgumentException("Path doesn't exist or not file");
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        if (args.length != 4) {
            throw new IllegalArgumentException(
                    "Root folder is null. Usage java -jar CSVReader.jar ROOT_FOLDER."
            );
        }
        Path path = Path.of(argsName.get("path"));
        validate(path);
        handle(argsName);
    }
}
