package dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Reto 06 - Pokedex sin duplicados
 * Operador principal: distinct()
 */
public class Ejercicio6 {
    public static void main(String[] args) {
        List<String> pokemones = List.of("Pikachu", "Charmander", "Pikachu", "Squirtle", "Charmander", "Mewtwo");

        List<String> sinDuplicados = pokemones.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println(sinDuplicados);
    }
}
