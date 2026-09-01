package dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Reto 15 - Maestro de gimnasios
 * Operador principal: max(Comparator)
 */
public class Ejercicio15 {

    public static List<Entrenador> entrenadoresBase() {
        return List.of(
                new Entrenador(1L, "Ash", 8, List.of()),
                new Entrenador(2L, "Misty", 5, List.of()),
                new Entrenador(3L, "Brock", 6, List.of()),
                new Entrenador(4L, "Gary", 10, List.of())
        );
    }

    public static void main(String[] args) {
        Optional<Entrenador> campeon = entrenadoresBase().stream()
                .max(Comparator.comparingInt(Entrenador::getMedallas));

        campeon.ifPresent(e -> {
            System.out.println("Campeon de gimnasios: " + e.getNombre());
            System.out.println("Medallas obtenidas: " + e.getMedallas());
        });
    }
}
