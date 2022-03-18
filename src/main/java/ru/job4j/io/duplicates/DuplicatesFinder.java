package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(
                Path.of("/Users/adletbaitorynov/Downloads/Java projects"),
                duplicatesVisitor);
        duplicatesVisitor.getDuplicates().stream()
                .flatMap(Collection::stream)
                .forEach(System.out::println);
    }
}
