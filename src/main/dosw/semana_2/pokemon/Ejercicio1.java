package dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Reto 01 - Pokemon tipo fuego
 * Operador principal: filter()
 */
public class Ejercicio1 {

    record PokemonSimple(String nombre, String tipo) {}

    public static void main(String[] args) {
        List<PokemonSimple> pokemones = List.of(
                new PokemonSimple("Pikachu", "Electrico"),
                new PokemonSimple("Charmander", "Fuego"),
                new PokemonSimple("Squirtle", "Agua"),
                new PokemonSimple("Vulpix", "Fuego"),
                new PokemonSimple("Bulbasaur", "Planta"),
                new PokemonSimple("Flareon", "Fuego")
        );

        List<String> tipoFuego = pokemones.stream()
                .filter(p -> p.tipo().equals("Fuego"))
                .map(PokemonSimple::nombre)
                .collect(Collectors.toList());

        System.out.println(tipoFuego);
    }
}
