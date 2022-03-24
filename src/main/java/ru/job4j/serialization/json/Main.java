package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

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
        /* По заданию: 5. Преобразование JSON в POJO. JsonObject */
        /* JSONObject из json-строки строки */
        JSONObject jsonDepartment = new JSONObject(
                "{\"name\":\"Mark\","
                        + "\"age\":31,"
                        + "\"status\":false,"
                        + "\"department\":{\"name\":\"DRT\"},"
                        + "\"duties\":[\"support\",\"consultation\",\"engineering works\"]}"
        );
        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("handsome");
        list.add("fair-haired");
        JSONArray jsonArray = new JSONArray(list);
        /* JSONObject напрямую методом put */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", employee.getName());
        jsonObject.put("age", employee.getAge());
        jsonObject.put("status", employee.isStatus());
        jsonObject.put("department", employee.getDepartment());
        jsonObject.put("duties", employee.getDuties());
        jsonObject.put("info", jsonArray);
        /* Выведем результат в консоль */
        System.out.println(jsonObject);
        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(employee));
    }
}
