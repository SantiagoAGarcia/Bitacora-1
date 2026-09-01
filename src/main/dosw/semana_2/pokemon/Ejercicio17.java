package dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Reto 17 - Equipo mas poderoso
 * Operadores principales: mapToDouble() + sum()
 */
public class Ejercicio17 {
    public static void main(String[] args) {
        List<Pokemon> equipoAsh = List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 600, "Kanto", false),
                new Pokemon(2L, "Charizard", "Fuego", 60, 600, "Kanto", false),
                new Pokemon(3L, "Sceptile", "Planta", 55, 650, "Hoenn", false)
        );
        List<Pokemon> equipoGary = List.of(
                new Pokemon(4L, "Nidoking", "Veneno", 60, 800, "Kanto", false),
                new Pokemon(5L, "Arcanine", "Fuego", 62, 800, "Kanto", false),
                new Pokemon(6L, "Blastoise", "Agua", 58, 740, "Kanto", false)
        );
        List<Pokemon> equipoBrock = List.of(
                new Pokemon(7L, "Onix", "Roca", 50, 600, "Kanto", false),
                new Pokemon(8L, "Geodude", "Roca", 40, 600, "Kanto", false),
                new Pokemon(9L, "Vulpix", "Fuego", 35, 470, "Kanto", false)
        );

        List<Entrenador> entrenadores = List.of(
                new Entrenador(1L, "Ash", 8, equipoAsh),
                new Entrenador(2L, "Gary", 10, equipoGary),
                new Entrenador(3L, "Brock", 6, equipoBrock)
        );

        Optional<Entrenador> masPoderoso = entrenadores.stream()
                .max(Comparator.comparingDouble(e ->
                        e.getEquipo().stream().mapToDouble(Pokemon::getPoderCombate).sum()));

        masPoderoso.ifPresent(e -> {
            double poderAcumulado = e.getEquipo().stream()
                    .mapToDouble(Pokemon::getPoderCombate)
                    .sum();
            System.out.println("Entrenador mas poderoso: " + e.getNombre());
            System.out.println("Poder acumulado del equipo: " + (int) poderAcumulado);
        });
    }
}
