package jordan.abad.lab04manejodeestados

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import jordan.abad.lab04manejodeestados.ui.theme.Lab04ManejoDeEstadosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab04ManejoDeEstadosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TaskApp()
                }
            }
        }
    }
}