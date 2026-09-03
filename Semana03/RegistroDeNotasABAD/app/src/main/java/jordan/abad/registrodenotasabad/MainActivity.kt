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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jordan.abad.registrodenotasabad.ui.theme.RegistroDeNotasABADTheme
import java.util.Locale
import kotlin.math.roundToInt

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
    // Estados para las notas
    var notaFundamentos by remember { mutableFloatStateOf(0f) }
    var notaPoo by remember { mutableFloatStateOf(0f) }
    var notaMoviles by remember { mutableFloatStateOf(0f) }
    var notaBd by remember { mutableFloatStateOf(0f) }

    // Estados para controles
    var redondearPromedio by remember { mutableStateOf(false) }
    var confirmado by remember { mutableStateOf(false) }

    // Estado del resultado
    var mostrarTarjeta by remember { mutableStateOf(false) }
    var promedioPonderado by remember { mutableDoubleStateOf(0.0) }
    var promedioFinalTexto by remember { mutableStateOf("") }
    var observacionTexto by remember { mutableStateOf("") }
    var colorChipBg by remember { mutableStateOf(Color.Unspecified) }
    var colorChipText by remember { mutableStateOf(Color.Unspecified) }

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

            // Switch Redondear
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

            // Checkbox Confirmar
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

            // Botones de Acción (Calcular y Limpiar)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        val ponderado = (notaFundamentos * 0.20) + (notaPoo * 0.25) + (notaMoviles * 0.30) + (notaBd * 0.25)
                        promedioPonderado = ponderado

                        val promFinalNum: Double
                        if (redondearPromedio) {
                            val roundedInt = ponderado.roundToInt()
                            promFinalNum = roundedInt.toDouble()
                            promedioFinalTexto = "$roundedInt"
                        } else {
                            promFinalNum = ponderado
                            promedioFinalTexto = String.format(Locale.US, "%.2f", ponderado)
                        }

                        when {
                            promFinalNum >= 17.0 -> {
                                observacionTexto = "EXCELENTE"
                                colorChipBg = Color(0xFFC8E6C9)
                                colorChipText = Color(0xFF1B5E20)
                            }
                            promFinalNum >= 13.0 -> {
                                observacionTexto = "APROBADO"
                                colorChipBg = Color(0xFFE8F5E9)
                                colorChipText = Color(0xFF2E7D32)
                            }
                            promFinalNum >= 10.0 -> {
                                observacionTexto = "EN RECUPERACIÓN"
                                colorChipBg = Color(0xFFFFF9C4)
                                colorChipText = Color(0xFFF57F17)
                            }
                            else -> {
                                observacionTexto = "DESAPROBADO"
                                colorChipBg = Color(0xFFFFCDD2)
                                colorChipText = Color(0xFFC62828)
                            }
                        }

                        mostrarTarjeta = true
                    },
                    enabled = confirmado,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = moradoPrincipal)
                ) {
                    Text("CALCULAR PROMEDIO", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }

                OutlinedButton(
                    onClick = {
                        // Restablecer estados a 0
                        notaFundamentos = 0f
                        notaPoo = 0f
                        notaMoviles = 0f
                        notaBd = 0f
                        redondearPromedio = false
                        confirmado = false
                        mostrarTarjeta = false
                    },
                    modifier = Modifier
                        .weight(0.5f)
                        .height(50.dp)
                ) {
                    Text("Limpiar", fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (!mostrarTarjeta) {
                Text(
                    text = "Asigna las notas y confirma para calcular",
                    color = Color.Gray,
                    fontSize = 13.sp
                )
            } else {
                // Tarjeta de Resultados
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Promedio ponderado:  ${String.format(Locale.US, "%.2f", promedioPonderado)}",
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "Promedio final:  $promedioFinalTexto",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = moradoPrincipal
                            )
                            if (redondearPromedio) {
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "(redondeado)",
                                    fontSize = 11.sp,
                                    color = Color.Gray
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Chip de Observación
                        Surface(
                            color = colorChipBg,
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(
                                text = observacionTexto,
                                color = colorChipText,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "✓  Promedio calculado correctamente",
                    color = Color(0xFF2E7D32),
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Pie de página exigido
            Text(
                text = "Desarrollado por: Jordan Abad",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = Color.Gray
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