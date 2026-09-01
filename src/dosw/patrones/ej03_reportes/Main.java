package dosw.patrones.ej03_reportes;

// Patrones combinados: Template Method + Factory Method
// Template Method fija el esqueleto de generacion (4 pasos).
// Factory Method decide que subclase concreta instanciar.

abstract class ReportGenerator {
    public final void generate() {
        fetchData();
        processData();
        applyFormat();
        exportFile();
    }

    protected void fetchData() {
        System.out.println("Obteniendo datos...");
    }

    protected void processData() {
        System.out.println("Procesando informacion...");
    }

    protected abstract void applyFormat();

    protected abstract void exportFile();
}

class PdfReport extends ReportGenerator {
    protected void applyFormat() { System.out.println("Aplicando formato PDF"); }
    protected void exportFile() { System.out.println("Exportando archivo .pdf"); }
}

class ExcelReport extends ReportGenerator {
    protected void applyFormat() { System.out.println("Aplicando formato Excel (hojas y estilos)"); }
    protected void exportFile() { System.out.println("Exportando archivo .xlsx"); }
}

class CsvReport extends ReportGenerator {
    protected void applyFormat() { System.out.println("Aplicando formato CSV (separado por comas)"); }
    protected void exportFile() { System.out.println("Exportando archivo .csv"); }
}

class ReportFactory {
    static ReportGenerator create(String tipo) {
        return switch (tipo.toUpperCase()) {
            case "PDF" -> new PdfReport();
            case "EXCEL" -> new ExcelReport();
            case "CSV" -> new CsvReport();
            default -> throw new IllegalArgumentException("Tipo de reporte no soportado: " + tipo);
        };
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Reporte PDF ===");
        ReportGenerator pdf = ReportFactory.create("PDF");
        pdf.generate();

        System.out.println();
        System.out.println("=== Reporte CSV ===");
        ReportGenerator csv = ReportFactory.create("CSV");
        csv.generate();
    }
}
