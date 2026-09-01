package dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Reto 08 - Evoluciones preparadas
 * Operador principal: filter()
 */
public class Ejercicio8 {

    record PokemonEvolucion(String nombre, boolean puedeEvolucionar) {}

    public static void main(String[] args) {
        List<PokemonEvolucion> pokemones = List.of(
                new PokemonEvolucion("Pikachu", true),
                new PokemonEvolucion("Raichu", false),
                new PokemonEvolucion("Charmander", true),
                new PokemonEvolucion("Charizard", false),
                new PokemonEvolucion("Squirtle", true),
                new PokemonEvolucion("Blastoise", false)
        );

        List<String> listosParaEvolucionar = pokemones.stream()
                .filter(PokemonEvolucion::puedeEvolucionar)
                .map(PokemonEvolucion::nombre)
                .collect(Collectors.toList());

        System.out.println("Listos para evolucionar: " + listosParaEvolucionar);
    }
}
