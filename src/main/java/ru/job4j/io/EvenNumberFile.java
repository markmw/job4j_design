package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("/Users/adletbaitorynov/Downloads/even.txt")) {
            StringBuilder numbers = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                numbers.append((char) read);
            }
            String[] lines = numbers.toString().split(System.lineSeparator());
            for (String line : lines) {
                System.out.println(line + " "
                        + (Integer.parseInt(line) % 2 == 0 ? "is even" : "is not even"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
