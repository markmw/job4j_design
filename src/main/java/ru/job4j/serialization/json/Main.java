package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Employee employee = new Employee(
                "Mark",
                31,
                false,
                new Department("DRT"),
                new String[] {"support", "consultation", "engineering works"});
        final Gson gson = new GsonBuilder().create();
        String employeeJson = gson.toJson(employee);
        System.out.println("Employee to JSON: " + employeeJson);
        final Employee employeeFromJson = gson.fromJson(employeeJson, Employee.class);
        System.out.println("Employee from JSON: " + employeeFromJson);
    }
}
