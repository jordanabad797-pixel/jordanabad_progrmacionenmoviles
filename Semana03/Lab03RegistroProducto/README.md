# Lab 03: Registro de Producto - Rama Mejora IA

Este repositorio contiene la implementación mejorada de la aplicación móvil de registro de productos en Android utilizando Jetpack Compose.

## Prompts utilizados y Decisiones de Desarrollo

### 1. Manejo de Estado y Validaciones (Commit B1)
* **Prompt/Decisión:** Se definió la lógica para validar tres escenarios antes de procesar el cálculo:
    - Campos de texto vacíos o con espacios en blanco.
    - Validación del precio como valor numérico flotante estricto (`toDoubleOrNull`) mayor a 0.
    - Validación de la cantidad como número entero estricto (`toIntOrNull`) mayor a 0.

### 2. Feedback de Usuario y Limpieza (Commit B2)
* **Prompt/Decisión:** Se integraron dos elementos clave de UX:
    - Un mensaje de error dinámico en color rojo (`MaterialTheme.colorScheme.error`) cuando falla alguna validación.
    - Un botón de tipo `OutlinedButton` ("Limpiar") que restablece todas las variables de estado (`nombre`, `precio`, `cantidad`, `mostrarResumen`, `mensajeError`) a sus valores iniciales.

### 3. Estructura y Limpieza de Código (Commit B3)
* **Prompt/Decisión:** Se refactorizaron las importaciones generales para evitar duplicidad de paquetes de Jetpack Compose y se adaptó el proyecto para compilar sin problemas en la API 34.