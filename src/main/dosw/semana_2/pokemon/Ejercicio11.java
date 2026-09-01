package dosw.semana_2.pokemon;

import java.util.List;

/**
 * Reto 11 - Poder promedio
 * Operadores principales: mapToDouble() + average()
 */
public class Ejercicio11 {
    public static void main(String[] args) {
        List<Pokemon> equipo = Ejercicio9.equipoBase();

        double promedio = equipo.stream()
                .mapToDouble(Pokemon::getPoderCombate)
                .average()
                .orElse(0);

        System.out.printf("Poder de combate promedio: %.2f%n", promedio);
    }
}
