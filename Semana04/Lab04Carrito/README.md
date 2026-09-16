# Laboratorio 04: Carrito de Compras TECSUP

**Estudiante:** Abad Jordan  
**Institución:** TECSUP  

---

## Capturas de Pantalla

### 1. Estado Vacío del Carrito
<img width="1600" height="1000" alt="image" src="https://github.com/user-attachments/assets/2663ba7d-c945-45bb-a64c-e73422a366e7" />



### 2. Carrito con Productos y Totales Calculados
<img width="1600" height="1000" alt="image" src="https://github.com/user-attachments/assets/a317c9fa-f1da-41e3-8956-03a9a3b9eda9" />


---

## Cuestionario de Reflexión

### 1. ¿Por qué se usó `mutableStateListOf` en lugar de `mutableStateOf(listOf(...))`?
`mutableStateListOf` crea una lista observable cuyos cambios internos (agregar, eliminar o modificar elementos) son detectados directamente por Jetpack Compose. Esto permite recomponer dinámicamente los elementos de la `LazyColumn` de manera eficiente sin tener que recrear o reasignar manualmente toda la lista en memoria.

### 2. ¿Qué sucede si el usuario ingresa un texto no válido en los campos numéricos de Precio o Cantidad?
Se emplean funciones de conversión segura como `toDoubleOrNull()` y `toIntOrNull()`. Si el usuario ingresa texto no numérico o deja un campo en blanco, estas funciones devuelven `null`, lo cual es manejado mediante el operador Elvis (`?:`) asignando un valor predeterminado (`0.0` o `0`). De esta manera se evitan excepciones en tiempo de ejecución (`NumberFormatException`) y la aplicación no sufre caídas (crashes).


