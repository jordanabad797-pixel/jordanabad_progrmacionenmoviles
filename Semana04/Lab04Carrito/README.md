# Laboratorio 04: Carrito de Compras TECSUP

**Estudiante:** Abad Jordan  
**Institución:** TECSUP  

---

## Capturas de Pantalla

### 1. Estado Vacío del Carrito
*(Inserta tu Captura 1)*

### 2. Carrito con Productos y Totales Calculados
*(Inserta tu Captura 2)*

---

## Cuestionario de Reflexión

### 1. ¿Por qué se usó `mutableStateListOf` en lugar de `mutableStateOf(listOf(...))`?
`mutableStateListOf` crea una lista observable cuyos cambios internos (agregar, eliminar o modificar elementos) son detectados directamente por Jetpack Compose. Esto permite recomponer dinámicamente los elementos de la `LazyColumn` de manera eficiente sin tener que recrear o reasignar manualmente toda la lista en memoria.

### 2. ¿Qué sucede si el usuario ingresa un texto no válido en los campos numéricos de Precio o Cantidad?
Se emplean funciones de conversión segura como `toDoubleOrNull()` y `toIntOrNull()`. Si el usuario ingresa texto no numérico o deja un campo en blanco, estas funciones devuelven `null`, lo cual es manejado mediante el operador Elvis (`?:`) asignando un valor predeterminado (`0.0` o `0`). De esta manera se evitan excepciones en tiempo de ejecución (`NumberFormatException`) y la aplicación no sufre caídas (crashes).
