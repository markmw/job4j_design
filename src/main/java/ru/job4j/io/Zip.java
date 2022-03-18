package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
2
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target))
        )) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argZip = ArgsName.of(args);
        Path source = Path.of(argZip.get("d"));
        String targetPath = argZip.get("o");
        String symbol = argZip.get("e");
        File directory = source.toFile();
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Not directory");
        }
        if (!symbol.startsWith(".")) {
            throw new IllegalArgumentException("Extension must start with a dot '.'");
        }
        List<Path> sources = Search.search(source, i -> !i.toFile().getName().endsWith(symbol));
        File target = new File(targetPath);
        packFiles(sources, target);
    }
}
