package dosw.patrones.ej08_restaurante;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Patrones combinados: Builder + Observer
// Builder garantiza que el pedido este completo y valido antes de existir.
// Observer notifica a los subsistemas cuando el pedido se confirma.

enum Size { SMALL, MEDIUM, LARGE }
enum Meat { BEEF, DOUBLE_BEEF, CHICKEN, VEGGIE }

interface OrderObserver {
    void onConfirmed(Order order);
}

class Order {
    private final Size size;
    private final Meat meat;
    private final List<String> toppings;
    private final List<String> sides;
    private final List<OrderObserver> observers = new ArrayList<>();

    private Order(OrderBuilder b) {
        this.size = b.size;
        this.meat = b.meat;
        this.toppings = List.copyOf(b.toppings);
        this.sides = List.copyOf(b.sides);
    }

    void addObserver(OrderObserver o) { observers.add(o); }

    void confirm() {
        System.out.println("Pedido confirmado: " + this);
        for (OrderObserver o : observers) {
            o.onConfirmed(this);
        }
    }

    public String toString() {
        return "Order[size=" + size + ", meat=" + meat + ", toppings=" + toppings + ", sides=" + sides + "]";
    }

    static class OrderBuilder {
        private Size size = Size.MEDIUM;
        private Meat meat = Meat.BEEF;
        private final List<String> toppings = new ArrayList<>();
        private final List<String> sides = new ArrayList<>();

        OrderBuilder setSize(Size size) { this.size = size; return this; }
        OrderBuilder setMeat(Meat meat) { this.meat = meat; return this; }
        OrderBuilder addTopping(String... items) { toppings.addAll(Arrays.asList(items)); return this; }
        OrderBuilder addSide(String... items) { sides.addAll(Arrays.asList(items)); return this; }
        Order build() { return new Order(this); }
    }
}

class KitchenService implements OrderObserver {
    public void onConfirmed(Order order) { System.out.println("[Cocina] Preparando: " + order); }
}

class BillingService implements OrderObserver {
    public void onConfirmed(Order order) { System.out.println("[Facturacion] Generando cuenta para: " + order); }
}

class DeliveryService implements OrderObserver {
    public void onConfirmed(Order order) { System.out.println("[Domicilio] Preparando ruta para: " + order); }
}

public class Main {
    public static void main(String[] args) {
        Order order = new Order.OrderBuilder()
                .setSize(Size.LARGE)
                .setMeat(Meat.DOUBLE_BEEF)
                .addTopping("queso", "lechuga")
                .addSide("papas", "gaseosa")
                .build();

        order.addObserver(new KitchenService());
        order.addObserver(new BillingService());
        order.addObserver(new DeliveryService());

        order.confirm();
    }
}
