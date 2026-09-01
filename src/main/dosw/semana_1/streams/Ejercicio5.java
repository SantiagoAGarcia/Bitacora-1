package dosw.semana_1.streams;

import java.util.List;

/**
 * Ejercicio 05 - Transacciones bancarias
 * Operadores principales: peek() - anyMatch()
 */
public class Ejercicio5 {

    static class Transaction {
        String id;
        double amount;
        boolean approved;

        Transaction(String id, double amount, boolean approved) {
            this.id = id;
            this.amount = amount;
            this.approved = approved;
        }
    }

    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
                new Transaction("T1", 150.0, true),
                new Transaction("T2", 300.0, true),
                new Transaction("T3", 75.5, false),
                new Transaction("T4", 500.0, true)
        );

        boolean existeNoAprobada = transactions.stream()
                .peek(t -> System.out.println("Procesando transaccion " + t.id + " -> $" + t.amount))
                .anyMatch(t -> !t.approved);

        System.out.println("Existe al menos una transaccion no aprobada: " + existeNoAprobada);
        System.out.println("El lote de transacciones es valido: " + !existeNoAprobada);
    }
}
