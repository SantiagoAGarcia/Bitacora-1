package dosw.patrones.ej01_pagos;

// Patrones combinados: Strategy + Factory Method
// Strategy encapsula cada medio de pago. Factory Method decide que
// Strategy construir segun el pais del usuario.

interface PaymentStrategy {
    void process(double amount);
}

class TarjetaStrategy implements PaymentStrategy {
    public void process(double amount) {
        System.out.println("Procesando pago con Tarjeta por $" + amount);
    }
}

class PseStrategy implements PaymentStrategy {
    public void process(double amount) {
        System.out.println("Procesando pago con PSE por $" + amount);
    }
}

class NequiStrategy implements PaymentStrategy {
    public void process(double amount) {
        System.out.println("Procesando pago con Nequi por $" + amount);
    }
}

class PayPalStrategy implements PaymentStrategy {
    public void process(double amount) {
        System.out.println("Procesando pago con PayPal por $" + amount);
    }
}

class StripeStrategy implements PaymentStrategy {
    public void process(double amount) {
        System.out.println("Procesando pago con Stripe por $" + amount);
    }
}

interface PaymentFactory {
    PaymentStrategy create(String type);
}

class ColombiaPaymentFactory implements PaymentFactory {
    public PaymentStrategy create(String type) {
        return switch (type.toUpperCase()) {
            case "PSE" -> new PseStrategy();
            case "NEQUI" -> new NequiStrategy();
            case "TARJETA" -> new TarjetaStrategy();
            default -> throw new IllegalArgumentException("Metodo no soportado en Colombia: " + type);
        };
    }
}

class UsaPaymentFactory implements PaymentFactory {
    public PaymentStrategy create(String type) {
        return switch (type.toUpperCase()) {
            case "PAYPAL" -> new PayPalStrategy();
            case "STRIPE" -> new StripeStrategy();
            case "TARJETA" -> new TarjetaStrategy();
            default -> throw new IllegalArgumentException("Metodo no soportado en USA: " + type);
        };
    }
}

class Checkout {
    private final PaymentStrategy strategy;

    Checkout(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    void pagar(double amount) {
        strategy.process(amount);
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Compra desde Colombia ===");
        PaymentFactory factoryCo = new ColombiaPaymentFactory();
        PaymentStrategy pseStrategy = factoryCo.create("PSE");
        Checkout checkoutCo = new Checkout(pseStrategy);
        checkoutCo.pagar(150000);

        System.out.println();
        System.out.println("=== Compra desde USA ===");
        PaymentFactory factoryUs = new UsaPaymentFactory();
        PaymentStrategy paypalStrategy = factoryUs.create("PAYPAL");
        Checkout checkoutUs = new Checkout(paypalStrategy);
        checkoutUs.pagar(49.99);
    }
}
