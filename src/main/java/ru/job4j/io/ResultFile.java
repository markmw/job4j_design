package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static String multiple(int size) {
        StringBuilder array = new StringBuilder();
        for (int row = 0; row < size; row++) {
            for (int cell = 0; cell < size; cell++) {
                array.append((row + 1) * (cell + 1));
                array.append(" ");
            }
            array.append(System.lineSeparator());
        }
        return array.toString();
    }

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("/Users/adletbaitorynov/Downloads/result.txt")) {
            out.write(multiple(10).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
