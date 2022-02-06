package ru.job4j;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar birthday = Calendar.getInstance();
        User user = new User("Petr", 1, birthday);
        User user1 = new User("Petr", 1, birthday);

        Map<User, Object> map = new HashMap<User, Object>();
        map.put(user, new Object());
        map.put(user1, new Object());

        for (Map.Entry<User, Object> obj : map.entrySet()) {
            System.out.printf("key: %s, value: %s \n", obj.getKey(), obj.getValue());
        }

    }
}
