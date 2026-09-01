package dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Reto 20 - Pokedex analitica
 * Operadores principales: groupingBy() + counting()
 * Construye: cantidad por tipo, cantidad por region, cantidad de legendarios,
 * promedio de nivel y el Pokemon mas fuerte. Todo con Streams.
 */
public class Ejercicio20 {
    public static void main(String[] args) {
        List<Pokemon> pokedex = List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Charmander", "Fuego", 62, 610, "Kanto", false),
                new Pokemon(3L, "Vulpix", "Fuego", 40, 350, "Kanto", false),
                new Pokemon(4L, "Flareon", "Fuego", 58, 480, "Kanto", false),
                new Pokemon(5L, "Squirtle", "Agua", 38, 210, "Kanto", false),
                new Pokemon(6L, "Psyduck", "Agua", 44, 240, "Kanto", false),
                new Pokemon(7L, "Vaporeon", "Agua", 65, 520, "Kanto", false),
                new Pokemon(8L, "Mewtwo", "Psiquico", 88, 680, "Kanto", true),
                new Pokemon(9L, "Mew", "Psiquico", 85, 600, "Kanto", true),
                new Pokemon(10L, "Bulbasaur", "Planta", 30, 250, "Kanto", false)
        );

        Map<String, Long> porTipo = pokedex.stream()
                .collect(Collectors.groupingBy(Pokemon::getTipo, Collectors.counting()));

        Map<String, Long> porRegion = pokedex.stream()
                .collect(Collectors.groupingBy(Pokemon::getRegion, Collectors.counting()));

        long legendarios = pokedex.stream()
                .filter(Pokemon::isLegendario)
                .count();

        double promedioNivel = pokedex.stream()
                .mapToInt(Pokemon::getNivel)
                .average()
                .orElse(0);

        pokedex.stream()
                .max(Comparator.comparingDouble(Pokemon::getPoderCombate))
                .ifPresent(masFuerte -> {
                    System.out.println("Por tipo: " + porTipo);
                    System.out.println("Por region: " + porRegion);
                    System.out.println("Legendarios: " + legendarios);
                    System.out.printf("Promedio nivel: %.1f%n", promedioNivel);
                    System.out.println("Mas fuerte: " + masFuerte.getNombre()
                            + " (PC: " + (int) masFuerte.getPoderCombate() + ")");
                });
    }
}
