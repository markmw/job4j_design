package ru.job4j.serialization.json;

import java.util.Arrays;

public class Employee {
    private final String name;
    private final int age;
    private final boolean status;
    private final Department department;
    private final String[] duties;

    public Employee(String name, int age, boolean status, Department department, String[] duties) {
        this.name = name;
        this.age = age;
        this.status = status;
        this.department = department;
        this.duties = duties;
    }

    @Override
    public String toString() {
        return "Employee{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", status=" + status
                + ", department=" + department
                + ", duties=" + Arrays.toString(duties)
                + '}';
    }
}
