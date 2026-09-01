package dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Reto 12 - Campeon regional
 * Operador principal: max(Comparator)
 */
public class Ejercicio12 {
    public static void main(String[] args) {
        List<Pokemon> equipo = List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psiquico", 70, 680, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragon", 55, 530, "Kanto", false),
                new Pokemon(6L, "Charizard", "Fuego", 60, 610, "Kanto", false)
        );

        Optional<Pokemon> campeon = equipo.stream()
                .max(Comparator.comparingDouble(Pokemon::getPoderCombate));

        campeon.ifPresent(p ->
                System.out.println("Campeon: " + p.getNombre() + " con PC: " + (int) p.getPoderCombate()));
    }
}
