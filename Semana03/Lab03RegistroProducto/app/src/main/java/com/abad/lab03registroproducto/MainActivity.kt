package com.abad.lab03registroproducto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abad.lab03registroproducto.ui.theme.Lab03RegistroProductoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab03RegistroProductoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaRegistro(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun PantallaRegistro(modifier: Modifier = Modifier) {
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }

    var mostrarResumen by remember { mutableStateOf(false) }
    var mensajeError by remember { mutableStateOf("") }

    val doublePrecio = precio.toDoubleOrNull()
    val intCantidad = cantidad.toIntOrNull()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Nuevo producto",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "Completa los datos y presiona Agregar",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = {
                nombre = it
                mostrarResumen = false
                mensajeError = ""
            },
            label = { Text("Nombre del producto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = precio,
            onValueChange = {
                precio = it
                mostrarResumen = false
                mensajeError = ""
            },
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = cantidad,
            onValueChange = {
                cantidad = it
                mostrarResumen = false
                mensajeError = ""
            },
            label = { Text("Cantidad") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    if (nombre.isBlank() || precio.isBlank() || cantidad.isBlank()) {
                        mensajeError = "Por favor, completa todos los campos."
                        mostrarResumen = false
                    } else if (doublePrecio == null || doublePrecio <= 0) {
                        mensajeError = "El precio debe ser un número válido mayor a 0."
                        mostrarResumen = false
                    } else if (intCantidad == null || intCantidad <= 0) {
                        mensajeError = "La cantidad debe ser un número entero mayor a 0."
                        mostrarResumen = false
                    } else {
                        mensajeError = ""
                        mostrarResumen = true
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Agregar")
            }

            OutlinedButton(
                onClick = {
                    nombre = ""
                    precio = ""
                    cantidad = ""
                    mostrarResumen = false
                    mensajeError = ""
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Limpiar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (mensajeError.isNotEmpty()) {
            Text(
                text = mensajeError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        if (mostrarResumen && doublePrecio != null && intCantidad != null) {
            val total = doublePrecio * intCantidad
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Resumen", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Producto: $nombre")
                    Text(text = "Precio unitario: S/ %.2f".format(doublePrecio))
                    Text(text = "Cantidad: $intCantidad")
                    Text(
                        text = "Total: S/ %.2f".format(total),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}