package jordan.abad.lab05navegacion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import jordan.abad.lab05navegacion.screens.DetailScreen
import jordan.abad.lab05navegacion.screens.HomeScreen
import jordan.abad.lab05navegacion.screens.ListScreen
import jordan.abad.lab05navegacion.screens.ProfileScreen
import jordan.abad.lab05navegacion.screens.LoginScreen

// Desarrollado por: Jordan Abad
// rememberNavController() crea y mantiene el controlador de navegación
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // NavHost es el contenedor del grafo de navegación
    // startDestination = Screen.Login.route -> la app inicia en "login"
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        // Pantalla de Login inicial
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }

        // composable registra cada pantalla dentro del grafo
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.List.route) {
            ListScreen(navController)
        }

        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }

        // composable con argumento: Screen.Detail.route -> "detail/{itemId}"
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("itemId") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: 0
            DetailScreen(navController, itemId)
        }
    }
}