package ru.job4j.io.finder;

import java.util.Map;
import java.util.HashMap;

public class ArgsName {
    private Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Args is null");
        }
        for (String el : args) {
            String[] pair = el.split("=", 2);
            if (pair.length != 2
                    || pair[0].length() < 2
                    || !pair[0].startsWith("-")
                    || pair[1].isEmpty()) {
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

    public int size() {
        return values.size();
    }
}
