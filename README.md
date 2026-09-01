# TECH CUP FÚTBOL — Planeación Ágil (Taller 6)

link: https://mail-team-k7xzbq5y.atlassian.net/jira/software/projects/TEC/boards/3/backlog?epics=visible

## 1. Diagrama de contexto (C4 Nivel 1)

Se modeló el sistema **TECH CUP FÚTBOL** como caja central, junto con:
- **5 actores tipo persona**: Estudiante/Jugador, Capitán, Árbitro, Organizador y Graduado/Familiar.
- **3 sistemas externos**: servidor de correo (SMTP, para envío de OTP), servicio de mapas (ubicación de canchas) y servicio de almacenamiento de archivos (fotos y comprobantes de pago).

Cada actor y sistema externo tiene su descripción y las flechas indican dirección y propósito de la interacción. El archivo fuente está en `docs/uml/Diagrama_Contexto_TECH_CUP.drawio`.

## 2. Jerarquía del backlog

Se siguió la jerarquía ágil estándar adaptada a Jira:

```
Épica (TCH-EPIC-01)
  └── Feature (FEAT-XX)          — contiene las Historias de Usuario y criterios de aceptación en su descripción
        └── Tarea técnica (TASK-XX) — issue tipo Sub-task, ejecutable, con puntos y definición de terminado
```

Las Historias de Usuario **no son issues independientes** en Jira: viven como texto dentro de la descripción de cada feature.

## 3. Épica — TCH-EPIC-01

**Nombre:** Plataforma digital TECH CUP FÚTBOL
**Duración:** 08/08/2026 (Sprint 1) — 01/10/2026 (cierre Sprint 7)

**Problema que resuelve:** el torneo se organiza hoy con WhatsApp, formularios y hojas de cálculo, generando retrasos, errores de inscripción y resultados inconsistentes.

**Objetivo:** una plataforma web centralizada que gestione todo el ciclo del torneo: inscripciones, equipos, calendario, arbitraje en vivo, estadísticas y comunicaciones.

**Alcance incluido:** identidad y autenticación, perfil deportivo, equipos, torneos e inscripciones, calendario, competencia y alineaciones, arbitraje en vivo, logística, estadísticas, comunicaciones, dashboard del organizador.

**Alcance excluido:** pagos en línea dentro de la plataforma, torneos de otros deportes, app móvil nativa, integración con sistemas externos de la Escuela.

**Criterios de éxito:**
- El torneo se gestiona completamente desde la plataforma, sin hojas de cálculo.
- El árbitro gestiona un partido en vivo desde su celular.
- Todos los actores completan su flujo principal sin asistencia.
- La plataforma cumple WCAG 2.1 AA.

## 4. Features (17 en total)

**12 features funcionales** (FEAT-01 a FEAT-12), cada uno con mínimo 2 Historias de Usuario y mínimo 2 criterios de aceptación (Dado/Cuando/Entonces, incluyendo un caso de error):

| Código | Feature | Sprint |
|---|---|---|
| FEAT-01 | Registro e identidad de usuarios | 1 |
| FEAT-02 | Perfil deportivo y gestión de jugadores | 2 |
| FEAT-03 | Creación y administración de equipos | 2 |
| FEAT-04 | Inscripción al torneo y verificación de pago | 3 |
| FEAT-05 | Gestión del torneo y calendario de partidos | 3 |
| FEAT-06 | Alineaciones y competencia | 3–4 |
| FEAT-07 | Módulo de arbitraje en vivo | 4 |
| FEAT-08 | Logística (refrigerios y dotación) | 4–5 |
| FEAT-09 | Estadísticas individuales y por equipo | 5 |
| FEAT-10 | Comunicaciones y chats | 5–6 |
| FEAT-11 | Llaves eliminatorias y tabla de posiciones | 5–7 |
| FEAT-12 | Dashboard del organizador | 6–7 |

**5 features de infraestructura** (FEAT-INF-01 a FEAT-INF-05), sin Historias de Usuario, orientados a entregables técnicos:

| Código | Feature | Sprint |
|---|---|---|
| FEAT-INF-01 | Configuración del repositorio y GitHub Flow | 1 |
| FEAT-INF-02 | Documentación técnica (README, arquitectura, APIs) | 1 y 7 |
| FEAT-INF-03 | Manual de identidad visual | 1 |
| FEAT-INF-04 | Configuración de Jira (épica, features, sprints, tablero) | 1 |
| FEAT-INF-05 | Presentación final y demo | 7 |

## 5. Tareas técnicas

Cada feature se descompuso en tareas técnicas ejecutables (título en infinitivo + objeto técnico, descripción sin ambigüedad, puntos de 1 a 3, definición de terminado). El detalle completo de las ~50 tareas, organizadas por sprint, está en [`Tareas_Tecnicas_TechCup.md`](./Tareas_Tecnicas_TechCup.md).

Escala de estimación usada:
- **1 punto** — tarea simple, sin incertidumbre.
- **2 puntos** — con algo de complejidad o decisión técnica.
- **3 puntos** — integración de componentes o tecnología nueva.

## 6. Planificación de sprints

7 sprints semanales del 08/08 al 01/10/2026, con capacidad de **20 puntos** cada uno (el Sprint 7 tiene **28 puntos** por durar dos semanas). La distribución de features y tareas por sprint respeta esa capacidad; cualquier excedente se mueve al sprint siguiente.

## 7. Estado en Jira

El backlog fue replicado en Jira siguiendo la ruta `Proyecto (Scrum) → Backlog → Create Issue`:
1. Proyecto Scrum creado.
2. Épica TCH-EPIC-01 creada con todos sus campos.
3. Los 17 features creados como hijos de la épica.
4. Las tareas técnicas creadas como Sub-tasks de cada feature, con Story Points asignados.
5. Los 7 sprints configurados con sus fechas.
6. Tareas distribuidas en cada sprint respetando la capacidad.
7. Sprint 1 iniciado.

## Autoría

Documento elaborado por el equipo DOSW como parte del Taller 6 — Planeación Ágil, TECH CUP FÚTBOL.
