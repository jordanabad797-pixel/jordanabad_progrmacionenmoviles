package jordan.abad.registrodenotasabad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jordan.abad.registrodenotasabad.ui.theme.RegistroDeNotasABADTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegistroDeNotasABADTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaRegistroNotas(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun PantallaRegistroNotas(modifier: Modifier = Modifier) {
    // Estados para las notas de los 4 cursos
    var notaFundamentos by remember { mutableFloatStateOf(0f) }
    var notaPoo by remember { mutableFloatStateOf(0f) }
    var notaMoviles by remember { mutableFloatStateOf(0f) }
    var notaBd by remember { mutableFloatStateOf(0f) }

    // Estados para Switch y Checkbox
    var redondearPromedio by remember { mutableStateOf(false) }
    var confirmado by remember { mutableStateOf(false) }

    val moradoPrincipal = Color(0xFF6750A4)
    val fondoGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFF3EDF7), Color(0xFFE8DEF8))
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(fondoGradient)
    ) {
        // Barra Superior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(moradoPrincipal)
                .padding(vertical = 16.dp, horizontal = 20.dp)
        ) {
            Text(
                text = "Registro de Notas",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Notas del ciclo",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "Desliza para asignar cada nota (0 a 20)",
                fontSize = 13.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Sliders para los 4 cursos
            ItemCurso(nombre = "Fundamentos de Programación", peso = "20%", nota = notaFundamentos, onNotaChange = { notaFundamentos = it })
            ItemCurso(nombre = "Programación Orientada a Objetos", peso = "25%", nota = notaPoo, onNotaChange = { notaPoo = it })
            ItemCurso(nombre = "Programación en Móviles", peso = "30%", nota = notaMoviles, onNotaChange = { notaMoviles = it })
            ItemCurso(nombre = "Base de Datos", peso = "25%", nota = notaBd, onNotaChange = { notaBd = it })

            Spacer(modifier = Modifier.height(16.dp))

            // Switch: Redondear promedio final
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Redondear promedio final", fontSize = 14.sp)
                Switch(
                    checked = redondearPromedio,
                    onCheckedChange = { redondearPromedio = it }
                )
            }

            // Checkbox: Confirmar notas
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = confirmado,
                    onCheckedChange = { confirmado = it }
                )
                Text(text = "Confirmo que las notas son correctas", fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón Calcular (deshabilitado si confirmado es false)
            Button(
                onClick = { /* Lógica de cálculo en el Commit 3 */ },
                enabled = confirmado,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = moradoPrincipal
                )
            ) {
                Text("CALCULAR PROMEDIO", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Asigna las notas y confirma para calcular",
                color = Color.Gray,
                fontSize = 13.sp
            )
        }
    }
}

@Composable
fun ItemCurso(nombre: String, peso: String, nota: Float, onNotaChange: (Float) -> Unit) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = nombre, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "($peso)", fontSize = 12.sp, color = Color(0xFF6750A4))
            }

            Surface(
                color = Color(0xFFE8DEF8),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "${nota.toInt()}",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF21005D)
                )
            }
        }

        Slider(
            value = nota,
            onValueChange = onNotaChange,
            valueRange = 0f..20f,
            steps = 19,
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFF6750A4),
                activeTrackColor = Color(0xFF6750A4)
            )
        )
    }
}