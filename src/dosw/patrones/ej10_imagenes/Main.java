package dosw.patrones.ej10_imagenes;

import java.util.ArrayDeque;
import java.util.Deque;

// Patrones combinados: Decorator + Command
// Decorator aplica filtros de forma acumulativa envolviendo la imagen.
// Command encapsula cada operacion permitiendo undo individual.

interface Image {
    String render();
}

class BaseImage implements Image {
    private final String name;
    BaseImage(String name) { this.name = name; }
    public String render() { return "Imagen[" + name + "]"; }
}

abstract class ImageDecorator implements Image {
    protected final Image wrapped;
    ImageDecorator(Image wrapped) { this.wrapped = wrapped; }
    Image getWrapped() { return wrapped; }
}

class GrayscaleDecorator extends ImageDecorator {
    GrayscaleDecorator(Image wrapped) { super(wrapped); }
    public String render() { return wrapped.render() + " + ByN"; }
}

class SepiaDecorator extends ImageDecorator {
    SepiaDecorator(Image wrapped) { super(wrapped); }
    public String render() { return wrapped.render() + " + Sepia"; }
}

class BrightnessDecorator extends ImageDecorator {
    BrightnessDecorator(Image wrapped) { super(wrapped); }
    public String render() { return wrapped.render() + " + Brillo"; }
}

interface ImageCommand {
    void execute();
    void undo();
}

class ImageEditor {
    Image current;
    ImageEditor(Image base) { this.current = base; }
}

class ApplyGrayscaleCommand implements ImageCommand {
    private final ImageEditor editor;
    ApplyGrayscaleCommand(ImageEditor editor) { this.editor = editor; }
    public void execute() { editor.current = new GrayscaleDecorator(editor.current); }
    public void undo() {
        if (editor.current instanceof ImageDecorator dec) editor.current = dec.getWrapped();
    }
}

class ApplySepiaCommand implements ImageCommand {
    private final ImageEditor editor;
    ApplySepiaCommand(ImageEditor editor) { this.editor = editor; }
    public void execute() { editor.current = new SepiaDecorator(editor.current); }
    public void undo() {
        if (editor.current instanceof ImageDecorator dec) editor.current = dec.getWrapped();
    }
}

class ApplyBrightnessCommand implements ImageCommand {
    private final ImageEditor editor;
    ApplyBrightnessCommand(ImageEditor editor) { this.editor = editor; }
    public void execute() { editor.current = new BrightnessDecorator(editor.current); }
    public void undo() {
        if (editor.current instanceof ImageDecorator dec) editor.current = dec.getWrapped();
    }
}

public class Main {
    public static void main(String[] args) {
        ImageEditor editor = new ImageEditor(new BaseImage("foto.jpg"));
        Deque<ImageCommand> history = new ArrayDeque<>();

        ImageCommand grayscale = new ApplyGrayscaleCommand(editor);
        grayscale.execute();
        history.push(grayscale);
        System.out.println("Despues de ByN: " + editor.current.render());

        ImageCommand sepia = new ApplySepiaCommand(editor);
        sepia.execute();
        history.push(sepia);
        System.out.println("Despues de Sepia: " + editor.current.render());

        ImageCommand brightness = new ApplyBrightnessCommand(editor);
        brightness.execute();
        history.push(brightness);
        System.out.println("Despues de Brillo: " + editor.current.render());

        System.out.println();
        System.out.println("=== Deshaciendo el ultimo comando (Brillo) ===");
        ImageCommand last = history.pop();
        last.undo();
        System.out.println("Resultado tras undo: " + editor.current.render());
    }
}
