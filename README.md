# Proyecto Final
## Objetivo:
La implementación actual del sistema de biblioteca, enfocada en las clases `Usuario`, `Libro` y `Prestamo`, para dejar claras sus responsabilidades, atributos, métodos y reglas de negocio.

## Introducción:
El sistema está desarrollado bajo Programación Orientada a Objetos (POO) y organiza la lógica en tres entidades principales: `Libro` (inventario), `Usuario` (control de préstamos por persona) y `Prestamo` (transacción).  
Cada clase encapsula su estado interno y expone métodos para validar y actualizar el proceso de préstamo de forma consistente.

### Objetivos Específicos:
* Definir Entidades: Explicar atributos y métodos clave de `Usuario`, `Libro` y `Prestamo`.
* Establecer Relaciones: Describir cómo `Prestamo` conecta `Usuario` con `Libro`.
* Gestionar Lógica de Negocio: Documentar validaciones como disponibilidad de stock y límite de préstamos por usuario.
* Garantizar Mantenibilidad: Dejar una base clara para futuras mejoras (devoluciones con fecha límite, multas, persistencia, etc.).

### 1. Especificaciones de la Clase Libro (`Libro.java`)
Es la entidad que representa cada libro y controla sus unidades disponibles.

* Atributos de Estado:
  * `titulo`: Nombre del libro.
  * `autor`: Autor del libro.
  * `stockTotal`: Cantidad total registrada del libro.
  * `stockPrestado`: Cantidad actualmente prestada.

* Lógica de los Métodos:
  * `estaDisponible()`: Retorna `true` si `stockPrestado < stockTotal`.
  * `actualizarStock(boolean prestar)`:  
    * Si `prestar` es `true`, incrementa `stockPrestado`.  
    * Si `prestar` es `false`, decrementa `stockPrestado` solo si es mayor que 0.
  * `getStock()`: Retorna `stockTotal - stockPrestado` (unidades disponibles reales).
  * `getTitulo()` y `getAutor()`: Devuelven datos de identificación del libro.

### 2. Especificaciones de la Clase Usuario (`Usuario.java`)
Gestiona los datos del usuario y su capacidad de solicitar libros.

* Atributos de Identificación y Control:
  * `id`: Identificador único del usuario (matrícula).
  * `nombre`: Nombre del usuario.
  * `prestamosActivos`: Contador de préstamos vigentes del usuario.
  * `MAX_PRESTAMOS`: Límite máximo permitido (3 préstamos).

* Lógica de los Métodos:
  * `puedePedir()`: Retorna `true` si `prestamosActivos < MAX_PRESTAMOS`.
  * `agregarPrestamo()`: Incrementa en 1 los préstamos activos.
  * `quitarPrestamo()`: Decrementa en 1 los préstamos activos si el contador es mayor que 0.
  * `getId()` y `getNombre()`: Devuelven datos del usuario.

### 3. Especificaciones de la Clase Prestamo (`Prestamo.java`)
Coordina la transacción entre un usuario y un libro.

* Atributos de Asociación y Estado:
  * `libro`: Referencia al objeto `Libro` prestado.
  * `usuario`: Referencia al objeto `Usuario` que solicita.
  * `fechaSalida`: Fecha en que se registra el préstamo.
  * `activo`: Estado del préstamo (`true` si sigue vigente, `false` si no está activo o ya finalizó).

* Lógica de los Métodos:
  * `registrar()`:
    * Valida que `libro` y `usuario` no sean nulos.
    * Verifica que el préstamo no esté ya activo.
    * Verifica reglas de negocio: `usuario.puedePedir()` y `libro.estaDisponible()`.
    * Registra `fechaSalida`, actualiza contadores (`usuario.agregarPrestamo()`, `libro.actualizarStock(true)`), marca `activo = true` y retorna `true`.
    * Si falla alguna validación, muestra mensaje y retorna `false`.
  * `finProceso()`:
    * Solo ejecuta la devolución si `activo` es `true`.
    * Actualiza contadores (`libro.actualizarStock(false)`, `usuario.quitarPrestamo()`), marca `activo = false`.
    * Si no está activo, informa que no se puede finalizar.

### 4. Flujo de Operación del Sistema
Para registrar y cerrar préstamos correctamente, el flujo es:

1. Validación previa:
   * Usuario dentro del límite (`puedePedir()`).
   * Libro con unidades disponibles (`estaDisponible()`).
2. Registro:
   * Se crea `Prestamo` y se ejecuta `registrar()`.
3. Actualización de estado:
   * Aumenta `prestamosActivos` del usuario.
   * Aumenta `stockPrestado` del libro.
4. Finalización:
   * `finProceso()` revierte contadores y cierra el préstamo.

### Definición de Relaciones de Objetos y Clases
En este diseño las relaciones principales son:

### Asociación
`Prestamo` se asocia con `Usuario` y `Libro` para ejecutar la transacción.

### Herencia
Actualmente no se utiliza herencia entre estas tres clases; la arquitectura es directa y basada en asociación.

## Conclusión
La implementación actual del sistema cumple con una estructura clara de POO al separar responsabilidades entre `Usuario`, `Libro` y `Prestamo`. Esta separación permite controlar de forma consistente las reglas principales del negocio: límite de préstamos por usuario, disponibilidad real del inventario y ciclo de vida de cada transacción (registro y finalización).  
### Composición (potencial futura)
Si en una versión futura se agregan clases dependientes de `Prestamo` (por ejemplo, una multa ligada al préstamo), podrían modelarse con composición.

---
## Modificaciones finales

### Clase Prestamo:

__Se eliminaron los siguientes atributos y metodos ya que no son requeridos:__

-  Se elimino el atributo __private LocalDate fetchSalida__ porque se utiliza la fecha asignada a una variable dentro de main.
- Se elimino atributo __private boolean activo;__ porque este es utilizado solo en un metodo el cual no se utiliza.
- Se elimno el metodo __public void finProceso()__ porque en ninguna parte del codigo es utilizado.

### Clase Usuario:

__Se modificaron y eliminaron los siguientes atributos y metodos ya que no son requeridos:__

- Se modificaron los atributos __private String id y private String nombre__ y se declararon como final ya que una vez tienen un valor asignado, no se vuelve a modificar.
- Se elimino el metodo __public void quitarPrestamo()__ porque en ninguna parte del codigo es requerido.

