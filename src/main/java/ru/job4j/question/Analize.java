package ru.job4j.question;

import java.util.Set;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int changed = 0;
        int deleted = 0;
        int added = 0;
        Map<Integer, String> currentMap = current.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        for (User user : previous) {
            String currentName = currentMap.get(user.getId());
            if (currentName == null) {
                deleted += 1;
            }
            boolean rsl = currentName != null
                    && !Objects.equals(currentName, user.getName());
            boolean rsl1 = currentName != null
                    && Objects.equals(currentName, user.getName());
            if (rsl) {
                changed += 1;
                currentMap.remove(user.getId());
            } else if (rsl1) {
                currentMap.remove(user.getId());
            }
        }
        added = current.size() - previous.size() + deleted;
        return new Info(added, changed, deleted);
    }
}
