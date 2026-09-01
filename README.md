# Taller DOSW — Patrones de Diseño Combinados

**Asignatura:** DOSW · **Tema:** Patrones de Diseño Combinados · **Modalidad:** Individual

- Nombre y Apellido: Santiago garcia

Cada ejercicio combina exactamente 2 patrones de diseño para resolver un caso real. La estructura de cada carpeta sigue: rol de cada patrón → cómo interactúan → esquema de clases → demostración de ejecución.

## Cómo ejecutar cada ejercicio

Cada ejercicio es independiente, vive en su propio paquete y trae una clase `Main` con un `main()` que demuestra la ejecución funcional de la combinación de patrones.

```bash
cd src
javac dosw/patrones/ej01_pagos/Main.java
java dosw.patrones.ej01_pagos.Main
```

Repite cambiando `ej01_pagos` por el paquete del ejercicio que quieras correr (`ej02_notificaciones`, `ej03_reportes`, … `ej10_imagenes`).

Para compilar y correr todos de una vez:

```bash
cd src
for pkg in ej01_pagos ej02_notificaciones ej03_reportes ej04_videojuegos ej05_banco \
           ej06_recomendaciones ej07_documentos ej08_restaurante ej09_autenticacion ej10_imagenes; do
  echo "=== $pkg ==="
  javac "dosw/patrones/$pkg/Main.java"
  java "dosw.patrones.$pkg.Main"
  echo
done
```

---

## Ejercicio 01 — Plataforma de Pagos Inteligentes

**Patrones:** Strategy + Factory Method
**Paquete:** `dosw.patrones.ej01_pagos`

- **Strategy** encapsula cada medio de pago (`TarjetaStrategy`, `PseStrategy`, `NequiStrategy`, `PayPalStrategy`, `StripeStrategy`) detrás de la interfaz `PaymentStrategy`. El `Checkout` solo conoce esa interfaz.
- **Factory Method** (`ColombiaPaymentFactory`, `UsaPaymentFactory`) decide qué `Strategy` concreta construir según el país del usuario, sin que el `Checkout` lo sepa.
- **Interacción:** el usuario selecciona su país → la Factory construye el gateway correcto → ese gateway implementa `PaymentStrategy` → el `Checkout` llama `strategy.process(amount)`.
- **Por qué es superior a no usar patrones:** sin Strategy, `Checkout` tendría un `if/else` gigante por cada medio de pago. Sin Factory, `Checkout` tendría que conocer y decidir qué clase concreta instanciar según el país, acoplándolo a la lógica geográfica. Separar ambas responsabilidades permite agregar un nuevo medio de pago o un nuevo país sin tocar `Checkout`.

## Ejercicio 02 — Sistema de Notificaciones Multicanal

**Patrones:** Observer + Factory Method
**Paquete:** `dosw.patrones.ej02_notificaciones`

- **Observer** desacopla el `Pedido` (Subject) de los canales de notificación (`EmailNotifier`, `SmsNotifier`, `PushNotifier` = Observers). Agregar un canal nuevo no modifica `Pedido`.
- **Factory Method** (`EmailMessageFactory`, `SmsMessageFactory`, `PushMessageFactory`) construye el mensaje correcto para cada canal (HTML, texto de 160 caracteres, JSON).
- **Interacción:** el `Pedido` cambia de estado → notifica a todos sus Observers activos → cada Observer usa su propia Factory para construir el mensaje adecuado a su canal → envía.
- **Por qué es superior:** sin Observer, `Pedido` tendría referencias directas a cada servicio de notificación. Sin Factory, cada Observer tendría lógica de formateo dispersa y duplicada dentro de sí mismo.

## Ejercicio 03 — Sistema de Reportes Empresariales

**Patrones:** Template Method + Factory Method
**Paquete:** `dosw.patrones.ej03_reportes`

- **Template Method**: `ReportGenerator.generate()` es `final` y fija el orden de los 4 pasos (`fetchData → processData → applyFormat → exportFile`). Las subclases solo sobreescriben los pasos variables.
- **Factory Method**: `ReportFactory.create(tipo)` decide qué subclase (`PdfReport`, `ExcelReport`, `CsvReport`) instanciar; el cliente nunca hace `new PdfReport()` directamente.
- **Interacción:** el cliente pide un tipo de reporte → la Factory construye la instancia concreta → el cliente llama `generate()` → el Template Method ejecuta los 4 pasos usando la implementación específica del formato para los pasos variables.
- **Por qué es superior:** sin Template Method, cada formato duplicaría los pasos fijos (obtener y procesar datos). Sin Factory, el cliente tendría que saber instanciar la clase correcta y mantendría un `switch` propio.

## Ejercicio 04 — Plataforma de Videojuegos — Personajes

**Patrones:** Builder + Decorator
**Paquete:** `dosw.patrones.ej04_videojuegos`

- **Builder** (`Warrior.WarriorBuilder`, con `CharacterDirector` para arquetipos predefinidos) construye el personaje base paso a paso al inicio de la partida, evitando un constructor con muchos parámetros.
- **Decorator** (`ShieldDecorator`, `SpeedDecorator`, `InvisibilityDecorator`) envuelve al personaje para añadir poderes temporales en tiempo de ejecución, sin tocar la clase base.
- **Interacción:** Builder crea el personaje configurable → durante la partida, cada poder temporal envuelve al personaje con un Decorator → al terminar el efecto, el wrapper se descarta sin afectar la clase base.
- **Por qué es superior:** sin Decorator, cada combinación de poderes requeriría una subclase (explosión combinatoria: 2⁵ = 32 clases para 5 poderes). Con Decorator son 5 wrappers + 1 base = 6 clases.

## Ejercicio 05 — Integración con Sistema Bancario Antiguo

**Patrones:** Adapter + Facade
**Paquete:** `dosw.patrones.ej05_banco`

- **Adapter** (`LegacyBankAdapter`) traduce la interfaz moderna `PaymentProcessor` a las llamadas incompatibles de `LegacyBankService` (montos en centavos, `executeTransaction`, etc.).
- **Facade** (`BankFacade`) expone un único método `procesarPago(monto)` que internamente orquesta los pasos de inicialización (conectar, autenticar, preparar contexto) y delega en el Adapter.
- **Interacción:** el desarrollador llama `BankFacade.procesarPago(monto)` → la Facade inicializa la sesión → delega al `LegacyBankAdapter` → el Adapter traduce al formato legacy → `LegacyBankService` ejecuta. El desarrollador nunca toca `LegacyBankService` directamente.
- **Por qué es superior:** Adapter resuelve la incompatibilidad de interfaces; Facade resuelve la complejidad de uso. Son complementarios: la Facade usa el Adapter internamente, y ninguno de los dos por sí solo resolvería ambos problemas (incompatibilidad + complejidad).

## Ejercicio 06 — Motor de Recomendaciones

**Patrones:** Strategy + Observer
**Paquete:** `dosw.patrones.ej06_recomendaciones`

- **Strategy** (`GenreStrategy`, `HistoryStrategy`, `PopularityStrategy`) permite intercambiar el algoritmo de recomendación en tiempo de ejecución sin reiniciar el motor.
- **Observer** (`HomePageComponent`, `SuggestedListComponent`, `NotificationService`) notifica automáticamente a todos los componentes de UI cuando cambian las preferencias del usuario.
- **Interacción:** el usuario cambia sus preferencias → el `UserProfile` (Subject) notifica a sus Observers → cada Observer re-obtiene sus recomendaciones usando la nueva Strategy configurada → la UI se actualiza sin *polling*.
- **Por qué es superior:** son responsabilidades ortogonales — Strategy responde "cómo recomendar", Observer responde "a quién avisar que cambió el cómo". Sin Observer, cada componente tendría que consultar activamente si las preferencias cambiaron.

## Ejercicio 07 — Flujo de Aprobación de Documentos

**Patrones:** Chain of Responsibility + State
**Paquete:** `dosw.patrones.ej07_documentos`

- **Chain of Responsibility** (`AutorHandler → LiderHandler → JuridicoHandler`) encadena los validadores; cada handler decide si procesa el documento o lo pasa al siguiente.
- **State** (`DraftState`, `InReviewState`, `ApprovedState`, `RejectedState`) encapsula el comportamiento y las transiciones del `Document`, eliminando los `switch/if` de estado.
- **Interacción:** un handler de la cadena procesa el documento → invoca `document.approve()` o `document.reject()` → el `DocumentState` actual ejecuta la transición correspondiente → el documento nunca tiene un `switch` de estados.
- **Por qué es superior:** sin State, cada método de `Document` tendría un `switch(estado){...}`. Sin Chain, el flujo de aprobación estaría *hardcodeado* en un único método gigante en vez de handlers independientes y reconfigurables.

## Ejercicio 08 — Sistema de Pedidos en Restaurante

**Patrones:** Builder + Observer
**Paquete:** `dosw.patrones.ej08_restaurante`

- **Builder** (`Order.OrderBuilder`) construye el pedido personalizado paso a paso (`setSize`, `setMeat`, `addTopping`, `addSide`) y produce un `Order` inmutable.
- **Observer** (`KitchenService`, `BillingService`, `DeliveryService`) notifica a los subsistemas cuando el pedido se confirma, sin que `Order` los conozca directamente.
- **Interacción:** el cliente configura el pedido con el Builder → `build()` retorna un `Order` inmutable → el sistema llama `order.confirm()` → el `Order` notifica a todos sus Observers → cada subsistema reacciona de forma independiente.
- **Por qué es superior:** Builder garantiza que el pedido esté completo y válido antes de existir (invariantes en `build()`); Observer garantiza que la confirmación desencadene reacciones sin acoplar `Order` a `KitchenService`, `BillingService` o `DeliveryService`.

## Ejercicio 09 — Sistema de Autenticación Empresarial

**Patrones:** Strategy + Chain of Responsibility
**Paquete:** `dosw.patrones.ej09_autenticacion`

- **Strategy** (`PasswordStrategy`, `GoogleStrategy`, `BiometricStrategy`) selecciona el mecanismo de autenticación según el tipo de usuario.
- **Chain of Responsibility** (`CredentialValidator → PermissionValidator → LocationValidator → TimeValidator`) procesa las validaciones post-autenticación en secuencia, lanzando `AccessDeniedException` si alguna falla.
- **Interacción:** el usuario intenta acceder → `AuthService` selecciona la Strategy correcta → si la autenticación es exitosa, el resultado pasa por la cadena de validadores → si todos aprueban, se concede acceso.
- **Por qué es superior:** son fases distintas del proceso — autenticación (quién eres, Strategy) vs. autorización (qué puedes hacer, Chain). Mezclar ambas responsabilidades en una sola clase produciría un método con múltiples condicionales anidados difíciles de extender.

## Ejercicio 10 — Aplicación de Edición de Imágenes

**Patrones:** Decorator + Command
**Paquete:** `dosw.patrones.ej10_imagenes`

- **Decorator** (`GrayscaleDecorator`, `SepiaDecorator`, `BrightnessDecorator`) aplica filtros de forma acumulativa envolviendo la imagen; se pueden apilar en cualquier orden sin modificar los filtros existentes.
- **Command** (`ApplyGrayscaleCommand`, `ApplySepiaCommand`, `ApplyBrightnessCommand`) encapsula cada operación del usuario como un objeto con `execute()`/`undo()`, permitiendo deshacer cualquier filtro individualmente mediante un historial.
- **Interacción:** el usuario aplica un filtro → se crea un Command que envuelve la imagen actual con un Decorator → el Command se agrega al historial → al hacer *undo*, el Command retira su Decorator y restaura la imagen anterior.
- **Por qué es superior:** la imagen base nunca cambia, solo se envuelve (Decorator), y el *undo* individual (Command) sería imposible de implementar limpiamente sin que cada operación recuerde cómo deshacerse a sí misma.

---
