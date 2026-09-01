package dosw.patrones.ej02_notificaciones;

import java.util.ArrayList;
import java.util.List;

// Patrones combinados: Observer + Factory Method
// Observer desacopla el Pedido de los canales de notificacion.
// Factory Method crea el mensaje correcto para cada canal.

class OrderEvent {
    String orderId;
    String newState;

    OrderEvent(String orderId, String newState) {
        this.orderId = orderId;
        this.newState = newState;
    }
}

interface Message {
    void send();
}

class EmailMessage implements Message {
    private final String html;
    EmailMessage(String html) { this.html = html; }
    public void send() { System.out.println("[EMAIL] Enviando HTML: " + html); }
}

class SmsMessage implements Message {
    private final String texto;
    SmsMessage(String texto) { this.texto = texto; }
    public void send() { System.out.println("[SMS] Enviando texto (" + texto.length() + " chars): " + texto); }
}

class PushMessage implements Message {
    private final String json;
    PushMessage(String json) { this.json = json; }
    public void send() { System.out.println("[PUSH] Enviando payload JSON: " + json); }
}

interface MessageFactory {
    Message build(OrderEvent event);
}

class EmailMessageFactory implements MessageFactory {
    public Message build(OrderEvent event) {
        return new EmailMessage("<h1>Pedido " + event.orderId + "</h1><p>Estado: " + event.newState + "</p>");
    }
}

class SmsMessageFactory implements MessageFactory {
    public Message build(OrderEvent event) {
        String texto = "Pedido " + event.orderId + ": " + event.newState;
        if (texto.length() > 160) texto = texto.substring(0, 160);
        return new SmsMessage(texto);
    }
}

class PushMessageFactory implements MessageFactory {
    public Message build(OrderEvent event) {
        return new PushMessage("{\"orderId\":\"" + event.orderId + "\",\"state\":\"" + event.newState + "\"}");
    }
}

interface NotificationObserver {
    void notify(OrderEvent event);
}

class EmailNotifier implements NotificationObserver {
    private final MessageFactory factory = new EmailMessageFactory();
    public void notify(OrderEvent event) { factory.build(event).send(); }
}

class SmsNotifier implements NotificationObserver {
    private final MessageFactory factory = new SmsMessageFactory();
    public void notify(OrderEvent event) { factory.build(event).send(); }
}

class PushNotifier implements NotificationObserver {
    private final MessageFactory factory = new PushMessageFactory();
    public void notify(OrderEvent event) { factory.build(event).send(); }
}

class Pedido {
    private final String id;
    private String estado;
    private final List<NotificationObserver> observers = new ArrayList<>();

    Pedido(String id) {
        this.id = id;
        this.estado = "pendiente";
    }

    void addObserver(NotificationObserver o) {
        observers.add(o);
    }

    void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        OrderEvent event = new OrderEvent(id, nuevoEstado);
        for (NotificationObserver o : observers) {
            o.notify(event);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Pedido pedido = new Pedido("PED-1001");
        pedido.addObserver(new EmailNotifier());
        pedido.addObserver(new SmsNotifier());
        pedido.addObserver(new PushNotifier());

        System.out.println("=== Cambio de estado: enviado ===");
        pedido.cambiarEstado("enviado");

        System.out.println();
        System.out.println("=== Cambio de estado: entregado ===");
        pedido.cambiarEstado("entregado");
    }
}
