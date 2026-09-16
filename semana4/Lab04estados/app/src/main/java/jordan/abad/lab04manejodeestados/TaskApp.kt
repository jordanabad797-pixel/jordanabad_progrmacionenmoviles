package jordan.abad.lab04manejodeestados

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TaskApp() {
    var textoTarea by remember { mutableStateOf("") }
    var siguienteId by remember { mutableIntStateOf(1) }
    val tareas = rememberSaveable(saver = tasksSaver()) { mutableStateListOf<Task>() }
    var mensajeError by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        val completadas = tareas.count { it.completed }
        Text(text = "Tareas: ${tareas.size}  |  Completadas: $completadas")

        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            OutlinedTextField(
                value = textoTarea,
                onValueChange = { textoTarea = it; mensajeError = "" },
                modifier = Modifier.weight(1f),
                label = { Text("Nueva tarea") },
                isError = mensajeError.isNotEmpty()
            )
            Button(
                onClick = {
                    val texto = textoTarea.trim()
                    when {
                        texto.isEmpty() -> mensajeError = "Escribe una tarea"
                        tareas.any { it.title.equals(texto, ignoreCase = true) } ->
                            mensajeError = "Esa tarea ya existe"
                        else -> {
                            tareas.add(Task(id = siguienteId, title = texto))
                            siguienteId++
                            textoTarea = ""
                        }
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Agregar")
            }
        }

        if (mensajeError.isNotEmpty()) {
            Text(text = mensajeError, color = Color.Red)
        }

        LazyColumn {
            items(tareas, key = { it.id }) { tarea ->
                TaskItem(
                    task = tarea,
                    onToggleCompleted = { id ->
                        val index = tareas.indexOfFirst { it.id == id }
                        if (index != -1) {
                            tareas[index] = tareas[index].copy(completed = !tareas[index].completed)
                        }
                    },
                    onDelete = { id -> tareas.removeAll { it.id == id } }
                )
            }
        }
    }
}