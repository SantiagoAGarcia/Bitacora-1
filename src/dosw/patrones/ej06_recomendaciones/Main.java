package dosw.patrones.ej06_recomendaciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Patrones combinados: Strategy + Observer
// Strategy resuelve "como recomendar". Observer avisa "a quien" cuando
// cambian las preferencias del usuario.

class Content {
    private final String title;
    Content(String title) { this.title = title; }
    public String toString() { return title; }
}

class User {
    String name;
    String preferredAlgorithm;

    User(String name, String preferredAlgorithm) {
        this.name = name;
        this.preferredAlgorithm = preferredAlgorithm;
    }
}

interface RecommendationAlgorithm {
    List<Content> recommend(User user);
}

class GenreStrategy implements RecommendationAlgorithm {
    public List<Content> recommend(User user) {
        return List.of(new Content("Accion 1"), new Content("Accion 2"));
    }
}

class HistoryStrategy implements RecommendationAlgorithm {
    public List<Content> recommend(User user) {
        return List.of(new Content("Basado en tu historial A"), new Content("Basado en tu historial B"));
    }
}

class PopularityStrategy implements RecommendationAlgorithm {
    public List<Content> recommend(User user) {
        return List.of(new Content("Top 1 popular"), new Content("Top 2 popular"));
    }
}

class RecommendationEngine {
    private static final Map<String, RecommendationAlgorithm> ALGORITHMS = Map.of(
            "genero", new GenreStrategy(),
            "historial", new HistoryStrategy(),
            "popularidad", new PopularityStrategy()
    );

    static RecommendationAlgorithm getStrategy(User user) {
        return ALGORITHMS.getOrDefault(user.preferredAlgorithm, new PopularityStrategy());
    }
}

interface PreferenceObserver {
    void onPreferenceChanged(User user);
}

class HomePageComponent implements PreferenceObserver {
    public void onPreferenceChanged(User user) {
        List<Content> recomendaciones = RecommendationEngine.getStrategy(user).recommend(user);
        System.out.println("[HomePage] Actualizando portada con: " + recomendaciones);
    }
}

class SuggestedListComponent implements PreferenceObserver {
    public void onPreferenceChanged(User user) {
        List<Content> recomendaciones = RecommendationEngine.getStrategy(user).recommend(user);
        System.out.println("[SuggestedList] Actualizando sugeridos con: " + recomendaciones);
    }
}

class NotificationService implements PreferenceObserver {
    public void onPreferenceChanged(User user) {
        System.out.println("[Notification] Enviando push: 'Tus recomendaciones se actualizaron, " + user.name + "'");
    }
}

class UserProfile {
    private final User user;
    private final List<PreferenceObserver> observers = new ArrayList<>();

    UserProfile(User user) { this.user = user; }

    void addObserver(PreferenceObserver o) { observers.add(o); }

    void cambiarPreferencia(String nuevoAlgoritmo) {
        user.preferredAlgorithm = nuevoAlgoritmo;
        for (PreferenceObserver o : observers) {
            o.onPreferenceChanged(user);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        User user = new User("Camila", "genero");
        UserProfile profile = new UserProfile(user);
        profile.addObserver(new HomePageComponent());
        profile.addObserver(new SuggestedListComponent());
        profile.addObserver(new NotificationService());

        System.out.println("=== Usuario cambia preferencia a 'historial' ===");
        profile.cambiarPreferencia("historial");

        System.out.println();
        System.out.println("=== Usuario cambia preferencia a 'popularidad' ===");
        profile.cambiarPreferencia("popularidad");
    }
}
