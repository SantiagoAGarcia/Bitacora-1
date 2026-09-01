package dosw.semana_2.pokemon;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Reto 14 - Organizar por region
 * Operador principal: groupingBy()
 */
public class Ejercicio14 {
    public static void main(String[] args) {
        List<Pokemon> pokedex = List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 25, 320, "Kanto", false),
                new Pokemon(2L, "Chikorita", "Planta", 18, 220, "Johto", false),
                new Pokemon(3L, "Torchic", "Fuego", 20, 240, "Hoenn", false),
                new Pokemon(4L, "Piplup", "Agua", 19, 230, "Sinnoh", false),
                new Pokemon(5L, "Charmander", "Fuego", 18, 250, "Kanto", false),
                new Pokemon(6L, "Totodile", "Agua", 21, 260, "Johto", false)
        );

        Map<String, List<String>> porRegion = pokedex.stream()
                .collect(Collectors.groupingBy(
                        Pokemon::getRegion,
                        Collectors.mapping(Pokemon::getNombre, Collectors.toList())
                ));

        porRegion.forEach((region, nombres) -> System.out.println(region + ": " + nombres));
    }
}
