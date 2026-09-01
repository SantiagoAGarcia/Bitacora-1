package dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Reto 04 - Pokemon alfa
 * Operador principal: max(Comparator)
 */
public class Ejercicio4 {

    record PokemonNivel(String nombre, int nivel) {}

    public static void main(String[] args) {
        List<PokemonNivel> equipo = List.of(
                new PokemonNivel("Pikachu", 45),
                new PokemonNivel("Charmander", 62),
                new PokemonNivel("Squirtle", 38),
                new PokemonNivel("Snorlax", 90),
                new PokemonNivel("Mewtwo", 88)
        );

        Optional<PokemonNivel> alfa = equipo.stream()
                .max(Comparator.comparingInt(PokemonNivel::nivel));

        alfa.ifPresent(p -> System.out.println("Pokemon Alfa: " + p.nombre() + " (nivel " + p.nivel() + ")"));
    }
}
