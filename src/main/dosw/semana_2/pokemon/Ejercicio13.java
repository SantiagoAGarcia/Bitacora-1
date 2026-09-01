package dosw.semana_2.pokemon;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Reto 13 - Organizar por tipo
 * Operador principal: groupingBy()
 */
public class Ejercicio13 {
    public static void main(String[] args) {
        List<Pokemon> pokedex = List.of(
                new Pokemon(1L, "Squirtle", "Agua", 20, 210, "Kanto", false),
                new Pokemon(2L, "Psyduck", "Agua", 22, 230, "Kanto", false),
                new Pokemon(3L, "Charmander", "Fuego", 18, 250, "Kanto", false),
                new Pokemon(4L, "Vulpix", "Fuego", 24, 260, "Kanto", false),
                new Pokemon(5L, "Bulbasaur", "Planta", 19, 240, "Kanto", false)
        );

        Map<String, List<String>> porTipo = pokedex.stream()
                .collect(Collectors.groupingBy(
                        Pokemon::getTipo,
                        Collectors.mapping(Pokemon::getNombre, Collectors.toList())
                ));

        porTipo.forEach((tipo, nombres) -> System.out.println(tipo + ": " + nombres));
    }
}
