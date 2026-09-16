package jordan.abad.lab04manejodeestados

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TemperatureDisplay() {
    var temperatura by remember { mutableIntStateOf(20) }

    val colorTexto = when {
        temperatura > 30 -> Color.Red
        temperatura < 10 -> Color.Blue
        else -> Color.Black
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Temperatura: $temperatura°", color = colorTexto)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { temperatura++ }) { Text("Subir") }
            Button(onClick = { temperatura-- }) { Text("Bajar") }
            Button(onClick = { temperatura = 20 }) { Text("Resetear") }
        }
    }
}