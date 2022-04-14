package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    final private Properties properties;

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver"));
        connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password")
        );
    }

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    private void execute(String sql, String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println(getTableScheme(connection, tableName));
        }
    }

    private void execute(String sql) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void createTable(String tableName) throws Exception {
        String query = String.format("CREATE TABLE IF NOT EXISTS %s();", tableName);
        execute(query, tableName);
    }

    public void dropTable(String tableName) throws Exception {
        String query = String.format("DROP TABLE IF EXISTS %s;", tableName);
        execute(query);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String query = String.format("ALTER TABLE %s ADD COLUMN %s %s;", tableName, columnName, type);
        execute(query, tableName);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String query = String.format("ALTER TABLE %s DROP COLUMN %s;", tableName, columnName);
        execute(query, tableName);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String query = String.format(
                "ALTER TABLE %s rename COLUMN %s to %s;", tableName, columnName, newColumnName
        );
        execute(query, tableName);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(
                    String.format("select * from %s limit 1", tableName));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format(
                        "%-15s|%-15s%n",
                        metaData.getColumnName(i),
                        metaData.getColumnType(i)));
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
            TableEditor tableEditor = new TableEditor(properties);
            tableEditor.createTable("demo_table");
            tableEditor.addColumn("demo_table", "age", "int");
            tableEditor.renameColumn("demo_table", "age", "century");
            tableEditor.dropColumn("demo_table", "century");
            tableEditor.dropTable("demo_table");
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
}
