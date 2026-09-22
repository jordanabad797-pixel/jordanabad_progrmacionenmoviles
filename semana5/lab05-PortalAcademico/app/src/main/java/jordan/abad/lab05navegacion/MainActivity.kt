package jordan.abad.lab05navegacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import jordan.abad.lab05navegacion.navigation.AppNavigation
import jordan.abad.lab05navegacion.ui.theme.Lab05NavegacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab05NavegacionTheme {
                AppNavigation()
            }
        }
    }
}