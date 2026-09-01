package dosw.patrones.ej05_banco;

// Patrones combinados: Adapter + Facade
// Adapter traduce PaymentProcessor <-> LegacyBankService.
// Facade oculta los 8 pasos de inicializacion tras un metodo simple.

interface PaymentProcessor {
    void pay(double amount);
}

class LegacyBankService {
    void connect() { System.out.println("[LEGACY] Conectando al banco antiguo..."); }
    void authenticate() { System.out.println("[LEGACY] Autenticando sesion..."); }

    void executeTransaction(String account, int cents) {
        System.out.println("[LEGACY] Ejecutando transaccion en cuenta " + account + " por " + cents + " centavos");
    }

    boolean verifyBalance(String account, int cents) {
        System.out.println("[LEGACY] Verificando saldo suficiente...");
        return true;
    }

    void disconnect() { System.out.println("[LEGACY] Desconectando del banco antiguo..."); }
}

class LegacyBankAdapter implements PaymentProcessor {
    private final LegacyBankService legacy;
    private final String cuenta;

    LegacyBankAdapter(LegacyBankService legacy, String cuenta) {
        this.legacy = legacy;
        this.cuenta = cuenta;
    }

    public void pay(double amount) {
        int cents = (int) Math.round(amount * 100);
        if (legacy.verifyBalance(cuenta, cents)) {
            legacy.executeTransaction(cuenta, cents);
        }
    }
}

class BankFacade {
    private final LegacyBankService legacy = new LegacyBankService();
    private final PaymentProcessor adapter;

    BankFacade(String cuenta) {
        this.adapter = new LegacyBankAdapter(legacy, cuenta);
    }

    public void procesarPago(double monto) {
        legacy.connect();
        legacy.authenticate();
        System.out.println("[FACADE] Preparando contexto de transaccion...");
        adapter.pay(monto);
        legacy.disconnect();
    }
}

public class Main {
    public static void main(String[] args) {
        BankFacade facade = new BankFacade("ACC-001");
        System.out.println("=== Desarrollador llama procesarPago(250.75) ===");
        facade.procesarPago(250.75);
    }
}
