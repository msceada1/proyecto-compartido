# 🚀 Proyecto: NIGHT CLASS (Versión Final)

**Miembros del Equipo:**
* [Nombre Alumno/a 1]
* [Nombre Alumno/a 2]

---

## 📖 Nuestra Historia (El "Skin")

**Temática del Juego:** Escape / Misterio en el Instituto.

**Premisa:**
> No sabes qué ha pasado. Justo cuando terminabas las clases te quedaste el último como siempre recogiendo tus cosas. Pero algo pasó. Lo último que recuerdas es que sentiste mucho frío y todo se volvió oscuro. Ahora estás en tu clase, pero es de noche y el instituto está cerrado.

**Objetivo:**
Descubrir qué ha pasado y encontrar una forma de salir del instituto con vida.

---

## ⚙️ Arquitectura y Evolución del Motor

Este proyecto ha sido desarrollado de forma incremental, evolucionando el "núcleo" a través de los contenidos de 1º DAM:

### 🟢 Fase 1: Motor Básico (UD1-UD3)
* **Paradigma:** Programación Procedural.
* **Estructuras:** Uso de Arrays estáticos para el mapa y el inventario. Control de flujo mediante `switch`.

### 🟡 Fase 2: Refactorización a POO (UD4-UD5)
* **Paradigma:** Programación Orientada a Objetos.
* **Diseño:** Creación del paquete `domain` con clases `Jugador`, `Habitacion` y `Objeto`. Aplicación de herencia y excepciones personalizadas.

### 🔴 Fase 3: Dinamismo y Persistencia (UD6-UD7) - [VERSIÓN FINAL]
* **Colecciones:** Uso de `HashMap` para el mapa y `ArrayList` para el inventario.
* **Persistencia (NIO + JSON):** El "Skin" se carga desde un archivo externo `aventura.json` usando la API `java.nio.file`.
* **Guardado:** Implementación de persistencia para salvar el progreso del jugador.

---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java (JDK 17+)
* **Estructuras:** Java Collections Framework (`List`, `Map`)
* **Ficheros:** Java NIO.2 (`Files`, `Path`)
* **Formatos:** JSON
* **Control de Versiones:** Git y GitHub

---

## 🎮 Comandos Soportados

* `ir [dirección]`: Moverse entre salas.
* `mirar`: Ver descripción de la sala y objetos.
* `examinar`: Muestra la descripción de un objeto específico.
* `coger [objeto]`: Añadir al inventario.
* `inventario`: Listar tus pertenencias.
* `abrir`: Intenta abrir un contenedor (cajón, cofre, taquilla, etc.).
* `combinar`: Intenta combinar dos objetos para crear uno nuevo.
* **`guardar`: Salvar la partida en un archivo.**
* `ayuda`: Listado de comandos.
* `salir`: Cerrar el juego.