package dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Reto 19 - Top 3 entrenadores
 * Criterio: 1) medallas desc, 2) poder acumulado desc, 3) nombre asc (desempate)
 * Operadores principales: sorted() + limit(3)
 */
public class Ejercicio19 {

    record RankingEntrenador(String nombre, int medallas, double poderAcumulado) {}

    public static void main(String[] args) {
        List<RankingEntrenador> entrenadores = List.of(
                new RankingEntrenador("Gary", 10, 2340),
                new RankingEntrenador("Ash", 8, 1850),
                new RankingEntrenador("Dawn", 7, 2100),
                new RankingEntrenador("Brock", 6, 1670)
        );

        Comparator<RankingEntrenador> criterio = Comparator
                .comparingInt(RankingEntrenador::medallas).reversed()
                .thenComparing(Comparator.comparingDouble(RankingEntrenador::poderAcumulado).reversed())
                .thenComparing(RankingEntrenador::nombre);

        List<RankingEntrenador> top3 = entrenadores.stream()
                .sorted(criterio)
                .limit(3)
                .collect(Collectors.toList());

        for (int i = 0; i < top3.size(); i++) {
            RankingEntrenador e = top3.get(i);
            System.out.println("#" + (i + 1) + " " + e.nombre() + " -- " + e.medallas()
                    + " medallas, PC: " + (int) e.poderAcumulado());
        }
    }
}
