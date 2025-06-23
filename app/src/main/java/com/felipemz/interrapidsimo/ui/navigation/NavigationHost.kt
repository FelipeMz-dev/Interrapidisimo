package com.felipemz.interrapidsimo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.felipemz.interrapidsimo.ui.home.HomeScreen
import com.felipemz.interrapidsimo.ui.splash.SplashScreen

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }

        composable("home") { HomeScreen(navController) }
    }
}