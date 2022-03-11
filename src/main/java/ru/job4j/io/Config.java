package ru.job4j.io;

import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            in.lines()
                    .filter(line -> !line.isEmpty() && !line.contains("#"))
                    .forEach(line -> {
                        String[] el = line.split("=");
                        if (el.length != 2) {
                            throw new IllegalArgumentException();
                        }
                        values.put(el[0], el[1]);
                    });
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("/Users/adletbaitorynov/Downloads/Java projects/job4j_design/app.properties"));
    }
}
