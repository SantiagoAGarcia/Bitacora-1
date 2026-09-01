package dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Reto 02 - Pokedex gritona
 * Operador principal: map()
 */
public class Ejercicio2 {
    public static void main(String[] args) {
        List<String> pokemones = List.of("Pikachu", "Charmander", "Squirtle", "Bulbasaur");

        List<String> pokedexGritona = pokemones.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(pokedexGritona);
    }
}
