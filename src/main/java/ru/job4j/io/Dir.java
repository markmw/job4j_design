package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("/Users/adletbaitorynov/Downloads/Java projects/");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("Total size : %s GB", file.getTotalSpace() / 1073741824));
        for (File subfile : file.listFiles()) {
            System.out.println(subfile.getName() + " : " + subfile.length() + " BYTE");
        }
    }
}
