package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public static void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target))
        )) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(String.valueOf(source)));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(String.valueOf(source)
                        ))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public static void validate(File directory, String symbol) {
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Not directory");
        }
        if (!symbol.startsWith(".")) {
            throw new IllegalArgumentException("Extension must start with a dot '.'");
        }
    }


    public static void main(String[] args) throws IOException {
        ArgsName argZip = ArgsName.of(args);
        Path source = Path.of(argZip.get("d"));
        String targetPath = argZip.get("o");
        String symbol = argZip.get("e");
        File directory = source.toFile();
        validate(directory, symbol);
        List<Path> sources = Search.search(source, i -> !i.toFile().getName().endsWith(symbol));
        File target = new File(targetPath);
        packFiles(sources, target);
    }
}
