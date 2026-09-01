package dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Reto 18 - Top 5 Pokemon mas fuertes
 * Operadores principales: sorted() + limit(5)
 */
public class Ejercicio18 {
    public static void main(String[] args) {
        List<Pokemon> pokedex = List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psiquico", 70, 680, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragon", 55, 530, "Kanto", false),
                new Pokemon(4L, "Squirtle", "Agua", 30, 210, "Kanto", false),
                new Pokemon(5L, "Gengar", "Fantasma", 50, 495, "Kanto", false),
                new Pokemon(6L, "Charizard", "Fuego", 60, 610, "Kanto", false)
        );

        List<String> top5 = pokedex.stream()
                .sorted(Comparator.comparingDouble(Pokemon::getPoderCombate).reversed())
                .limit(5)
                .map(p -> p.getNombre() + " -- PC: " + (int) p.getPoderCombate())
                .collect(Collectors.toList());

        for (int i = 0; i < top5.size(); i++) {
            System.out.println("#" + (i + 1) + " " + top5.get(i));
        }
    }
}
