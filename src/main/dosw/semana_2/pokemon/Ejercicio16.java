package dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Reto 16 - Entrenadores experimentados
 * Operador principal: filter()
 */
public class Ejercicio16 {
    public static void main(String[] args) {
        List<Entrenador> entrenadores = List.of(
                new Entrenador(1L, "Ash", 8, List.of()),
                new Entrenador(2L, "Misty", 5, List.of()),
                new Entrenador(3L, "Brock", 6, List.of()),
                new Entrenador(4L, "Gary", 10, List.of()),
                new Entrenador(5L, "May", 3, List.of()),
                new Entrenador(6L, "Dawn", 7, List.of())
        );

        List<String> experimentados = entrenadores.stream()
                .filter(e -> e.getMedallas() > 5)
                .map(e -> e.getNombre() + "(" + e.getMedallas() + ")")
                .collect(Collectors.toList());

        System.out.println("Entrenadores con > 5 medallas: " + experimentados);
    }
}
