package jordan.abad.lab05navegacion.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import jordan.abad.lab05navegacion.navigation.Screen

// Desarrollado por: Jordan Abad
// ProfileScreen: pantalla de perfil optimizada con estética UX premium de Material3
@Composable
fun ProfileScreen(navController: NavController) {
    val gradientColors = listOf(
        Color(0xFF5E35B1), // Morado medio-oscuro
        Color(0xFF311B92)  // Morado profundo
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
    ) {
        // Tarjeta superior con degradado morado
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(
                    brush = Brush.verticalGradient(gradientColors),
                    shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Avatar circular
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .background(Color.White.copy(alpha = 0.2f), CircleShape)
                        .padding(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Avatar de Usuario",
                            tint = Color(0xFF5E35B1),
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Text(
                    text = "Jordan Abad",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                )
                
                Text(
                    text = "Ingeniería de Software",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White.copy(alpha = 0.7f)
                    )
                )
            }
        }

        // Contenido con la información del perfil
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(24.dp)
        ) {
            Text(
                text = "INFORMACIÓN PERSONAL",
                style = MaterialTheme.typography.labelLarge.copy(
                    color = Color(0xFF5E35B1),
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            ProfileInfoItem(
                icon = Icons.Filled.Person,
                label = "Nombre Completo",
                value = "Jordan Abad"
            )
            
            ProfileInfoItem(
                icon = Icons.Filled.Email,
                label = "Correo",
                value = "jordan.abad@tecsup.edu.pe"
            )
            
            ProfileInfoItem(
                icon = Icons.Filled.Phone,
                label = "Teléfono",
                value = "+51 987 654 321"
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "ACADÉMICO",
                style = MaterialTheme.typography.labelLarge.copy(
                    color = Color(0xFF5E35B1),
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            ProfileInfoItem(
                icon = Icons.Filled.School,
                label = "Carrera",
                value = "Ingeniería de Software"
            )
            
            ProfileInfoItem(
                icon = Icons.Filled.Person,
                label = "Ciclo Actual",
                value = "VI Ciclo"
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Botón con ícono de salida "Cerrar Sesión" que mantiene la navegación existente al inicio
            Button(
                onClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFCE4EC), // Rosado pastel sutil
                    contentColor = Color(0xFFD81B60)    // Color magenta/rojo premium para salida
                ),
                shape = RoundedCornerShape(25.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = "Icono Cerrar Sesión",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Cerrar Sesión",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}

// Composable secundario reutilizable para los ítems informativos tipo lista
@Composable
fun ProfileInfoItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .background(Color(0xFFEEEEEE), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF616161),
                modifier = Modifier.size(22.dp)
            )
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.Gray
                )
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212121)
                )
            )
        }
    }
}
