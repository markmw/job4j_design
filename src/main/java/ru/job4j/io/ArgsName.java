package ru.job4j.io;

import java.util.Map;
import java.util.HashMap;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("args is null");
        }
        for (String el : args) {
            String[] pair = el.split("=", 2);
            if (pair.length != 2 || !pair[0].startsWith("-") || pair[1].trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid argument format. Required format - '-key=value'");
            }
            values.put(pair[0].substring(1), pair[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
