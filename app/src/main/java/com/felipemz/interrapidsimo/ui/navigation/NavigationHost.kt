package com.felipemz.interrapidsimo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.felipemz.interrapidsimo.ui.home.HomeScreen
import com.felipemz.interrapidsimo.ui.locations.LocationsScreen
import com.felipemz.interrapidsimo.ui.login.LoginScreen
import com.felipemz.interrapidsimo.ui.splash.SplashScreen
import com.felipemz.interrapidsimo.ui.tables.TablesScreen

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) { SplashScreen(navController) }

        composable(Screen.Login.route) { LoginScreen(navController) }

        composable(Screen.Home.route) { HomeScreen(navController) }

        composable(Screen.Tables.route) { TablesScreen(navController) }

        composable(Screen.Locations.route) { LocationsScreen(navController) }
    }
}