package dosw.semana_1.streams;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ejercicio 04 - Personas mayores de edad
 * Operadores principales: filter() - map()
 */
public class Ejercicio4 {

    static class User {
        private final String name;
        private final int age;

        User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    public static void main(String[] args) {
        List<User> users = List.of(
                new User("Ana", 25),
                new User("Luis", 17),
                new User("Carlos", 30),
                new User("Beatriz", 15)
        );

        List<String> mayoresDeEdad = users.stream()
                .filter(u -> u.getAge() >= 18)
                .map(User::getName)
                .collect(Collectors.toList());

        System.out.println(mayoresDeEdad);
    }
}
