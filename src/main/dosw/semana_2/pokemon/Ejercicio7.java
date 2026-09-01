package dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Reto 07 - Orden del Profesor Oak
 * Operador principal: sorted()
 */
public class Ejercicio7 {
    public static void main(String[] args) {
        List<String> pokemones = List.of("Squirtle", "Pikachu", "Mewtwo", "Bulbasaur", "Charmander", "Abra");

        List<String> ordenados = pokemones.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(ordenados);
    }
}
