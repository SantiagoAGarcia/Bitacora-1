package dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Reto 09 - Equipo Elite
 * Operador principal: filter()
 */
public class Ejercicio9 {

    public static List<Pokemon> equipoBase() {
        return List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psiquico", 70, 680, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragon", 55, 530, "Kanto", false),
                new Pokemon(4L, "Squirtle", "Agua", 30, 210, "Kanto", false),
                new Pokemon(5L, "Gengar", "Fantasma", 50, 495, "Kanto", false),
                new Pokemon(6L, "Charizard", "Fuego", 60, 610, "Kanto", false)
        );
    }

    public static void main(String[] args) {
        List<Pokemon> equipoElite = equipoBase().stream()
                .filter(p -> p.getPoderCombate() > 500)
                .collect(Collectors.toList());

        System.out.println("Equipo Elite (PC > 500): " + equipoElite);
    }
}
