# DOSW_BITACORA

Repositorio de la Bitácora Pokémon — Taller de Streams, Lambdas y Programación Funcional en Java.

## Datos personales

- Nombre y Apellido: _____________________________
- Código de Estudiante: _____________________________
- Curso: DOSW

---

# SEMANA No 1 — DOSW Manejo de Streams

`src/main/dosw/semana_1/streams/`

### Ejercicio 01 — Números Pares mayores a diez

Dada una lista de números enteros, se obtiene una nueva lista solo con los números pares mayores a 10.

**Código implementado:**
```java
List<Integer> resultado = numeros.stream()
        .filter(n -> n % 2 == 0 && n > 10)
        .collect(Collectors.toList());
```

**Captura de ejecución:** (agregar imagen de la consola aquí)

**Explicación:** Se usa `filter()` con una condición compuesta (número par y mayor a 10) para quedarnos únicamente con los elementos que cumplen ambas reglas.

### Ejercicio 02 — Cantidad de Palabras con más de 4 caracteres

**Código implementado:**
```java
List<String> resultado = palabras.stream()
        .filter(p -> p.length() > 4)
        .map(String::toUpperCase)
        .sorted()
        .collect(Collectors.toList());
```

**Captura de ejecución:** (agregar imagen aquí)

**Explicación:** Primero se filtran las palabras por longitud, luego se transforman a mayúsculas con `map()` y finalmente se ordenan alfabéticamente con `sorted()`. El tamaño de la lista resultante da la cantidad de palabras.

### Ejercicio 03 — Obtener nombres de los Usuarios

**Código implementado:**
```java
List<String> sortedUsers = users.stream()
        .filter(User::isActive)
        .map(User::getName)
        .map(String::toUpperCase)
        .sorted()
        .collect(Collectors.toList());
```

**Captura de ejecución:** (agregar imagen aquí)

**Explicación:** Se filtran los usuarios activos, se extrae su nombre con `map()`, se transforma a mayúsculas y se ordena alfabéticamente.

### Ejercicio 04 — Personas mayores de edad

**Código implementado:**
```java
List<String> mayoresDeEdad = users.stream()
        .filter(u -> u.getAge() >= 18)
        .map(User::getName)
        .collect(Collectors.toList());
```

**Captura de ejecución:** (agregar imagen aquí)

**Explicación:** `filter()` selecciona los usuarios con edad mayor o igual a 18 y `map()` extrae únicamente el nombre.

### Ejercicio 05 — Transacciones Bancarias

**Código implementado:**
```java
boolean existeNoAprobada = transactions.stream()
        .peek(t -> System.out.println("Procesando transaccion " + t.id))
        .anyMatch(t -> !t.approved);
```

**Captura de ejecución:** (agregar imagen aquí)

**Explicación:** `peek()` permite observar cada transacción a medida que se procesa (sin modificar el stream) y `anyMatch()` evalúa si existe al menos una transacción no aprobada, determinando si el lote es válido.

---

# SEMANA No 2 — Bitácora Pokémon

`src/main/dosw/semana_2/pokemon/`

## Datos de Entrenador

- Nombre y Apellido: _____________________________
- Código de Estudiante: _____________________________
- Curso: DOSW

### Nivel 1 — Entrenador Novato

**Ejercicio 01 — Pokémon Tipo Fuego (`filter()`)**
Se filtra la lista de Pokémon dejando únicamente los de tipo "Fuego".

**Ejercicio 02 — Pokédex Gritona (`map()`)**
Se transforma cada nombre a mayúsculas con `map(String::toUpperCase)`.

**Ejercicio 03 — Poder Total del Equipo (`reduce()`)**
Se suman todos los niveles con `reduce(0, Integer::sum)`.

**Ejercicio 04 — Pokémon Alfa (`max(Comparator)`)**
Se obtiene el Pokémon de mayor nivel con `max(Comparator.comparingInt(...))`.

**Ejercicio 05 — Pokémon Legendarios (`filter()` + `count()`)**
Se filtran los Pokémon con nivel superior a 80 y se cuenta cuántos cumplen la condición.

*(Para cada ejercicio: pegar código, captura de ejecución y una breve explicación.)*

### Nivel 2 — Entrenador Intermedio

**Ejercicio 06 — Pokédex Sin Duplicados (`distinct()`)**
Elimina los nombres repetidos conservando el primer orden de aparición.

**Ejercicio 07 — Orden del Profesor Oak (`sorted()`)**
Ordena alfabéticamente los nombres de los Pokémon.

**Ejercicio 08 — Evoluciones Preparadas (`filter()`)**
Filtra los Pokémon cuyo atributo `puedeEvolucionar` es `true`.

### Nivel 3 — Líder de Gimnasio

A partir de este nivel se usa la clase `Pokemon` (`id`, `nombre`, `tipo`, `nivel`, `poderCombate`, `region`, `legendario`).

**Ejercicio 09 — Equipo Élite (`filter()`)**
Filtra los Pokémon con `poderCombate` superior a 500.

**Ejercicio 10 — Pokédex Compacta (`map()` + `collect()`)**
Extrae únicamente los nombres de todos los Pokémon del equipo.

**Ejercicio 11 — Poder Promedio (`mapToDouble()` + `average()`)**
Calcula el promedio de `poderCombate` de todo el equipo.

**Ejercicio 12 — Campeón Regional (`max(Comparator)`)**
Obtiene el Pokémon con mayor `poderCombate`.

**Ejercicio 13 — Organizar por Tipo (`groupingBy()`)**
Agrupa los nombres de los Pokémon según su tipo.

**Ejercicio 14 — Organizar por Región (`groupingBy()`)**
Agrupa los nombres de los Pokémon según su región de origen.

### Nivel 4 — Alto Mando

A partir de este nivel se usa la clase `Entrenador` (`id`, `nombre`, `medallas`, `equipo: List<Pokemon>`).

**Ejercicio 15 — Maestro de Gimnasios (`max(Comparator)`)**
Encuentra el entrenador con más medallas.

**Ejercicio 16 — Entrenadores Experimentados (`filter()`)**
Filtra los entrenadores con más de 5 medallas.

**Ejercicio 17 — Equipo Más Poderoso (`mapToDouble()` + `sum()`)**
Calcula, para cada entrenador, la suma del `poderCombate` de su equipo y determina cuál es la más alta.

### Nivel 5 — Campeón de la Liga Pokémon DOSW

**Ejercicio 18 — Top 5 Pokémon Más Fuertes (`sorted()` + `limit(5)`)**
Ordena la Pokédex de mayor a menor `poderCombate` y toma los 5 primeros.

**Ejercicio 19 — Top 3 Entrenadores (`sorted()` + `limit(3)`)**
Ordena por medallas (desc), luego por poder acumulado (desc) y finalmente por nombre (asc) como desempate; toma los 3 primeros.

**Ejercicio 20 — Pokédex Analítica (`groupingBy()` + `counting()`)**
Construye, en una sola pasada de Streams: cantidad de Pokémon por tipo, por región, cantidad de legendarios, promedio de nivel y el Pokémon más fuerte.

*(Para cada ejercicio de la Semana 2: pegar código implementado, captura de ejecución y explicación breve, siguiendo la misma estructura de la Semana 1.)*

---

## Retos Especiales (si aplica)

- [ ] Reto Legendario — Method References
- [ ] Reto Shiny — Buenas prácticas de commits
- [ ] Reto Mewtwo — Ejercicio propuesto

---

## Estrategia de ramas (Git Flow aplicado)

- Ramas principales obligatorias: `main` y `develop`.
- Por cada semana: `feature/semana-n-dosw`.
- Por cada ejercicio: `feature/semana-n-dosw-ejercicio-n`, creada a partir de la rama de la semana.
- Al terminar un ejercicio: mergear a la rama de la semana y eliminar la rama del ejercicio.
- Al terminar todos los ejercicios de la semana: Pull Request de `feature/semana-n-dosw` hacia `develop` (esta rama semanal **no se elimina**).
- Al cierre de cada ciclo: sincronizar `develop` hacia `main`.
