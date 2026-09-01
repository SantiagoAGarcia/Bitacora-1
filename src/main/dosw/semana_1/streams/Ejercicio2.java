package dosw.semana_1.streams;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ejercicio 02 - Cantidad de palabras con mas de 4 caracteres
 * Operadores principales: filter() - map() - sorted()
 */
public class Ejercicio2 {
    public static void main(String[] args) {
        List<String> palabras = List.of("java", "stream", "api", "functional", "code", "git");

        List<String> resultado = palabras.stream()
                .filter(p -> p.length() > 4)
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(resultado);
        System.out.println("Cantidad de palabras resultantes: " + resultado.size());
    }
}
