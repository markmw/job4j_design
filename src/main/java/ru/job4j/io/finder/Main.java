package ru.job4j.io.finder;

public class Main {
    public static void main(String[] args) {
        Finder finder = new Finder(args);
        finder.validate();
        finder.searcher();
    }
}