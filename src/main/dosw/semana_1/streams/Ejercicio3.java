package dosw.semana_1.streams;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ejercicio 03 - Obtener nombres de los usuarios
 * Operadores principales: filter() - map() - sorted()
 */
public class Ejercicio3 {

    static class User {
        private final Long id;
        private final String name;
        private final int age;
        private final boolean active;

        User(Long id, String name, int age, boolean active) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.active = active;
        }

        public String getName() {
            return name;
        }

        public boolean isActive() {
            return active;
        }
    }

    public static void main(String[] args) {
        List<User> users = List.of(
                new User(1L, "Ana", 25, true),
                new User(2L, "Luis", 17, false),
                new User(3L, "Carlos", 30, true),
                new User(4L, "Beatriz", 22, true)
        );

        List<String> sortedUsers = users.stream()
                .filter(User::isActive)
                .map(User::getName)
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(sortedUsers);
    }
}
