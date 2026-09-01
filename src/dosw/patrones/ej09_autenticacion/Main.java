package dosw.patrones.ej09_autenticacion;

// Patrones combinados: Strategy + Chain of Responsibility
// Strategy = "que llave uso para entrar" (mecanismo de autenticacion).
// Chain of Responsibility = "pasar los controles" (autorizacion en cadena).

class Credentials {
    String user;
    String secret;
    String location;
    int hourOfDay;

    Credentials(String user, String secret, String location, int hourOfDay) {
        this.user = user;
        this.secret = secret;
        this.location = location;
        this.hourOfDay = hourOfDay;
    }
}

class AuthResult {
    boolean success;
    String detail;

    AuthResult(boolean success, String detail) {
        this.success = success;
        this.detail = detail;
    }
}

interface AuthStrategy {
    AuthResult authenticate(Credentials c);
}

class PasswordStrategy implements AuthStrategy {
    public AuthResult authenticate(Credentials c) {
        System.out.println("[Auth] Autenticando con usuario/contrasena");
        return new AuthResult(true, "password-ok");
    }
}

class GoogleStrategy implements AuthStrategy {
    public AuthResult authenticate(Credentials c) {
        System.out.println("[Auth] Autenticando con Google OAuth");
        return new AuthResult(true, "google-ok");
    }
}

class BiometricStrategy implements AuthStrategy {
    public AuthResult authenticate(Credentials c) {
        System.out.println("[Auth] Autenticando con biometria");
        return new AuthResult(true, "biometric-ok");
    }
}

class AccessDeniedException extends RuntimeException {
    AccessDeniedException(String msg) { super(msg); }
}

abstract class Validator {
    private Validator next;

    Validator setNext(Validator next) {
        this.next = next;
        return next;
    }

    void validate(Credentials c) {
        check(c);
        if (next != null) {
            next.validate(c);
        }
    }

    abstract void check(Credentials c);
}

class CredentialValidator extends Validator {
    void check(Credentials c) {
        System.out.println("[Chain] Validando credenciales de " + c.user);
    }
}

class PermissionValidator extends Validator {
    void check(Credentials c) {
        System.out.println("[Chain] Validando permisos de " + c.user);
    }
}

class LocationValidator extends Validator {
    void check(Credentials c) {
        System.out.println("[Chain] Validando ubicacion: " + c.location);
        if (!c.location.equals("oficina") && !c.location.equals("vpn")) {
            throw new AccessDeniedException("Ubicacion no permitida: " + c.location);
        }
    }
}

class TimeValidator extends Validator {
    void check(Credentials c) {
        System.out.println("[Chain] Validando horario laboral: " + c.hourOfDay + "h");
        if (c.hourOfDay < 6 || c.hourOfDay > 20) {
            throw new AccessDeniedException("Fuera de horario laboral");
        }
    }
}

class AuthService {
    AuthStrategy selectStrategy(String tipoUsuario) {
        return switch (tipoUsuario) {
            case "empleado" -> new PasswordStrategy();
            case "externo" -> new GoogleStrategy();
            case "ejecutivo" -> new BiometricStrategy();
            default -> new PasswordStrategy();
        };
    }
}

public class Main {
    public static void main(String[] args) {
        AuthService service = new AuthService();
        Credentials creds = new Credentials("jsalazar", "***", "oficina", 10);

        AuthStrategy strategy = service.selectStrategy("empleado");
        AuthResult result = strategy.authenticate(creds);
        System.out.println("Resultado autenticacion: " + result.detail);

        if (result.success) {
            CredentialValidator cred = new CredentialValidator();
            PermissionValidator perm = new PermissionValidator();
            LocationValidator loc = new LocationValidator();
            TimeValidator time = new TimeValidator();
            cred.setNext(perm).setNext(loc).setNext(time);

            try {
                cred.validate(creds);
                System.out.println("Acceso concedido a " + creds.user);
            } catch (AccessDeniedException e) {
                System.out.println("Acceso denegado: " + e.getMessage());
            }
        }
    }
}
