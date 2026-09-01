package dosw.patrones.ej04_videojuegos;

// Patrones combinados: Builder + Decorator
// Builder construye el personaje base al inicio de la partida.
// Decorator agrega poderes temporales dinamicamente durante el juego.

interface Character {
    String getName();
    int getPower();
    String describe();
}

class Warrior implements Character {
    private final String armor;
    private final String weapon;
    private final String skill;

    private Warrior(WarriorBuilder builder) {
        this.armor = builder.armor;
        this.weapon = builder.weapon;
        this.skill = builder.skill;
    }

    public String getName() { return "Warrior"; }
    public int getPower() { return 50; }

    public String describe() {
        return "Warrior[armor=" + armor + ", weapon=" + weapon + ", skill=" + skill + ", power=" + getPower() + "]";
    }

    static class WarriorBuilder {
        private String armor = "cuero";
        private String weapon = "espada corta";
        private String skill = "ninguna";

        public WarriorBuilder setArmor(String armor) { this.armor = armor; return this; }
        public WarriorBuilder setWeapon(String weapon) { this.weapon = weapon; return this; }
        public WarriorBuilder setSkill(String skill) { this.skill = skill; return this; }
        public Warrior build() { return new Warrior(this); }
    }
}

class CharacterDirector {
    static Warrior guerreroElite() {
        return new Warrior.WarriorBuilder()
                .setArmor("acero")
                .setWeapon("espada larga")
                .setSkill("furia de batalla")
                .build();
    }
}

abstract class CharacterDecorator implements Character {
    protected final Character wrapped;

    CharacterDecorator(Character wrapped) {
        this.wrapped = wrapped;
    }

    Character getWrapped() { return wrapped; }
    public String getName() { return wrapped.getName(); }
}

class ShieldDecorator extends CharacterDecorator {
    ShieldDecorator(Character wrapped) { super(wrapped); }
    public int getPower() { return wrapped.getPower() + 10; }
    public String describe() { return wrapped.describe() + " + Escudo de hielo(+10)"; }
}

class SpeedDecorator extends CharacterDecorator {
    SpeedDecorator(Character wrapped) { super(wrapped); }
    public int getPower() { return wrapped.getPower() + 5; }
    public String describe() { return wrapped.describe() + " + Velocidad extra(+5)"; }
}

class InvisibilityDecorator extends CharacterDecorator {
    InvisibilityDecorator(Character wrapped) { super(wrapped); }
    public int getPower() { return wrapped.getPower(); }
    public String describe() { return wrapped.describe() + " + Invisibilidad(evasion)"; }
}

public class Main {
    public static void main(String[] args) {
        Warrior warrior = CharacterDirector.guerreroElite();
        System.out.println("Personaje base: " + warrior.describe());

        Character powered = new ShieldDecorator(new SpeedDecorator(warrior));
        System.out.println("Con poderes temporales: " + powered.describe());
        System.out.println("Poder total: " + powered.getPower());

        Character withInvisibility = new InvisibilityDecorator(powered);
        System.out.println("Con invisibilidad tambien: " + withInvisibility.describe());
    }
}
