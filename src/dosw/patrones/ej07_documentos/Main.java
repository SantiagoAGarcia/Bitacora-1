package dosw.patrones.ej07_documentos;

// Patrones combinados: Chain of Responsibility + State
// Chain of Responsibility encadena los handlers de revision.
// State encapsula el comportamiento y las transiciones del documento.

interface DocumentState {
    void approve(Document doc);
    void reject(Document doc);
    String name();
}

class DraftState implements DocumentState {
    public void approve(Document doc) {
        doc.setState(new InReviewState());
        System.out.println("[Estado] Borrador -> En revision");
    }
    public void reject(Document doc) {
        System.out.println("[Estado] No se puede rechazar un borrador");
    }
    public String name() { return "BORRADOR"; }
}

class InReviewState implements DocumentState {
    public void approve(Document doc) {
        doc.setState(new ApprovedState());
        System.out.println("[Estado] En revision -> Aprobado");
    }
    public void reject(Document doc) {
        doc.setState(new RejectedState());
        System.out.println("[Estado] En revision -> Rechazado");
    }
    public String name() { return "EN_REVISION"; }
}

class ApprovedState implements DocumentState {
    public void approve(Document doc) {
        System.out.println("[Estado] El documento ya esta aprobado");
    }
    public void reject(Document doc) {
        System.out.println("[Estado] No se puede rechazar un documento aprobado");
    }
    public String name() { return "APROBADO"; }
}

class RejectedState implements DocumentState {
    public void approve(Document doc) {
        System.out.println("[Estado] No se puede aprobar un documento rechazado");
    }
    public void reject(Document doc) {
        System.out.println("[Estado] El documento ya esta rechazado");
    }
    public String name() { return "RECHAZADO"; }
}

class Document {
    private final String titulo;
    private DocumentState state = new DraftState();

    Document(String titulo) { this.titulo = titulo; }

    void setState(DocumentState state) { this.state = state; }
    void approve() { state.approve(this); }
    void reject() { state.reject(this); }
    String getEstadoActual() { return state.name(); }
    String getTitulo() { return titulo; }
}

abstract class DocumentHandler {
    private DocumentHandler next;

    DocumentHandler setNext(DocumentHandler next) {
        this.next = next;
        return next;
    }

    void handle(Document doc) {
        if (canHandle(doc)) {
            process(doc);
        }
        if (next != null) {
            next.handle(doc);
        }
    }

    abstract boolean canHandle(Document doc);
    abstract void process(Document doc);
}

class AutorHandler extends DocumentHandler {
    boolean canHandle(Document doc) { return doc.getEstadoActual().equals("BORRADOR"); }
    void process(Document doc) {
        System.out.println("[AutorHandler] Revisando '" + doc.getTitulo() + "'");
        doc.approve();
    }
}

class LiderHandler extends DocumentHandler {
    boolean canHandle(Document doc) { return doc.getEstadoActual().equals("EN_REVISION"); }
    void process(Document doc) {
        System.out.println("[LiderHandler] Revisando '" + doc.getTitulo() + "'");
        doc.approve();
    }
}

class JuridicoHandler extends DocumentHandler {
    boolean canHandle(Document doc) { return doc.getEstadoActual().equals("APROBADO"); }
    void process(Document doc) {
        System.out.println("[JuridicoHandler] Revision juridica OK para '" + doc.getTitulo() + "'");
    }
}

public class Main {
    public static void main(String[] args) {
        Document doc = new Document("Contrato de arrendamiento");

        AutorHandler autor = new AutorHandler();
        LiderHandler lider = new LiderHandler();
        JuridicoHandler juridico = new JuridicoHandler();
        autor.setNext(lider).setNext(juridico);

        System.out.println("Estado inicial: " + doc.getEstadoActual());
        autor.handle(doc);
        System.out.println("Estado final: " + doc.getEstadoActual());
    }
}
