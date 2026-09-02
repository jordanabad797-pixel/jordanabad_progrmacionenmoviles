# Laboratorio 03: Registro de Producto

**Estudiante:** Abad Jordan  
**Curso:** Programación en Móviles  
**Institución:** Tecsup

## Descripción
Aplicación Android desarrollada en Jetpack Compose para el registro de productos. Permite ingresar nombre, precio y cantidad, calculando de manera automática el importe total y mostrando un resumen dinámico en pantalla mediante la gestión de estados con `remember` y `mutableStateOf`.

## Capturas de Pantalla

| Formulario Inicial | Producto Registrado |
| :---: | :---: |
| ![Pantalla Inicial](Captura%201%20(Pantalla%20vac%C3%ADa).png) | ![Producto Registrado](Captura%202%20(Producto%20registrado).png) |

## Pregunta de Reflexión

**¿Qué pasaría si declaras las variables de los campos SIN `remember`?**

Si se declaran las variables de estado únicamente usando `mutableStateOf("")` sin envolverlas en `remember`, el valor de las variables se reiniciará a su estado inicial (cadena vacía `""`) cada vez que la pantalla realice un proceso de recomposición. En la práctica, el usuario no podría escribir en los campos de texto (`OutlinedTextField`), ya que cada pulsación de tecla activaría una recomposición y el valor volvería a estar vacío[cite: 1]. `remember` es indispensable porque preserva el valor del estado a través de las recomposiciones de Compose[cite: 1].