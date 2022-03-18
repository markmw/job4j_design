package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertEquals("Petr Arsentev", config.value("name"));
        assertNull(config.value("surname"));
    }

    @Test
    public void whenPair() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"),
                is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(config.value("hibernate.connection.url"),
                is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenIllegalArgumentException() {
        String path = "./data/pair_with_exception.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenKeyIsMissing() {
        String path = "./data/key_is_missing.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value(""), is("value"));
    }

    @Test
    public void whenPairTwoEqualsSymbols() {
        String path = "./data/pair_two_equals_symbols.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key"), is("value"));
    }
}