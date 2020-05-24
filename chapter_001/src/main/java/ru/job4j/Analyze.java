package ru.job4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Класс должен возвращать статистику об изменении коллекции.
 * List<User> previous - начальные данные,
 * List<User> current - измененные данные.
 * <p>
 * Нужно понять:
 * Сколько добавлено новых пользователей.
 * Сколько изменено пользователей. Изменённым считается объект в котором изменилось имя. а id осталось прежним.
 * Сколько удалено пользователей.
 */
public class Analyze {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, User> userMap = previous.stream().collect(Collectors.toMap(user -> user.id, user -> user));
        for (User user : current) {
            User userPrevious = userMap.get(user.id);
            //Проверка на добавленные элементы
            if (userPrevious == null) {
                info.added++;
                continue;
            }
            //Проверка на измененные элементы
            if (!userPrevious.name.equals(user.name)) {
                info.changed++;
                continue;
            }
        }
        //Проверка на удаленные элементы
        info.deleted = previous.size() - (current.size() - info.added);
        return info;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            User user = (User) o;

            if (id != user.id) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int result = Integer.hashCode(id);
            return result;
        }
    }

    public static class Info {

        int added;
        int changed;
        int deleted;

        @Override
        public String toString() {
            return "Info{"
                    + "added=" + added
                    + ", changed=" + changed
                    + ", deleted=" + deleted
                    + '}';
        }
    }
}
