package ru.job4j.io.finder;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Finder {
    private final ArgsName find;

    public Finder(String[] args) {
        this.find = ArgsName.of(args);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        FinderFiles searcher = new FinderFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPath();
    }

    private Predicate<Path> searchType(String name, String type) {
        Predicate<Path> condition = null;
        if ("mask".equals(type)) {
            condition = p -> p.toFile().getName().matches(
                    name.replace("?", ".").replace("*", ".*"));
        } else if ("name".equals(type)) {
            condition = p -> p.toFile().getName().equals(name);
        } else if ("regex".equals(type)) {
            condition = p -> p.toFile().getName().matches(name);
        }
        return condition;
    }

    public void searcher() {
        try (PrintWriter pw = new PrintWriter(find.get("o"))) {
            List<Path> listPath = search(
                    Path.of(find.get("d")),
                    searchType(find.get("n"), find.get("t")));
            listPath.forEach(path -> pw.printf("%s%n", path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void validate() {
        if (find.size() != 4
                && find.get("d") == null
                || find.get("n") == null
                || find.get("t") == null
                || find.get("o") == null) {
            throw new IllegalArgumentException("Invalid arguments. Use java -jar Finder.jar"
                    + " -d=PATH -n=NAME -t=TYPE_NAME_OR_MASK_OR_REGEX -o=OUTPUT_PATH");
        }
        if (!Files.isDirectory(Path.of(find.get("d")))) {
            throw new IllegalArgumentException("Invalid path to directory.");
        }
    }
}
