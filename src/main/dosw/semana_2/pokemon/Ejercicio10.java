package dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Reto 10 - Pokedex compacta
 * Operadores principales: map() + collect()
 */
public class Ejercicio10 {
    public static void main(String[] args) {
        List<Pokemon> equipo = Ejercicio9.equipoBase();

        List<String> nombres = equipo.stream()
                .map(Pokemon::getNombre)
                .collect(Collectors.toList());

        System.out.println(nombres);
    }
}
