package jordan.abad.lab04manejodeestados

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import jordan.abad.lab04manejodeestados.ui.theme.Lab04ManejoDeEstadosTheme

@Preview(showBackground = true)
@Composable
fun TaskAppPreview() {
    Lab04ManejoDeEstadosTheme {
        TaskApp()
    }
}

@Preview(showBackground = true)
@Composable
fun TemperatureDisplayPreview() {
    Lab04ManejoDeEstadosTheme {
        TemperatureDisplay()
    }
}