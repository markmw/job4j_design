package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        float floatVar = 33.33f;
        double doubleVar = 33.3333333;
        boolean bool = true;
        char charVar = 'A';
        short shortVar = 123;
        byte byteVar = 123;
        long longVar = 1233333333333333L;
        LOG.debug(
                "User info name: {}, "
                        + "age: {}, "
                        + "floatVar: {}, "
                        + "doubleVar: {}, "
                        + "bool: {}, "
                        + "charVar: {}, "
                        + "shortVar: {}, "
                        + "byteVar: {} "
                        + "longVar: {}",
                name, age, floatVar, doubleVar, bool, charVar, shortVar, byteVar, longVar);
    }
}
