package ru.job4j;

import java.util.List;

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
        int index;
        User tempPreviousUser;

        for (User currentUser : current) {
            index = indexOfElement(currentUser, previous);
            //Проверка на добавленные элементы
            if (index == -1) {
                info.added++;
                continue;
            }
            //Проверка на измененные элементы
            tempPreviousUser = previous.get(index);
            if (tempPreviousUser.equals(currentUser) && !tempPreviousUser.name.equals(currentUser.name)) {
                info.changed++;
            }
        }
        //Проверка на удаленные элементы
        info.deleted = previous.size() - (current.size() - info.added);
        return info;
    }

    private int indexOfElement(User user, List<User> listOfUsers) {
        return listOfUsers.indexOf(user);
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
            return "Info{" +
                    "added=" + added +
                    ", changed=" + changed +
                    ", deleted=" + deleted +
                    '}';
        }
    }
}
