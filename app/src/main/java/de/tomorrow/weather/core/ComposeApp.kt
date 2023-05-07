package de.tomorrow.weather.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.tomorrow.weather.presentation.screens.weather.ui.CurrentWeatherScreen

@Composable
fun ComposeApp() {
    val navController = rememberNavController()
    ApplicationNavHost(
        navController = navController
    )
}

@Composable
fun ApplicationNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "current"
    ) {
        composable("current") {
            CurrentWeatherScreen()
        }
    }
}