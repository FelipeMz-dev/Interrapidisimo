package com.felipemz.interrapidsimo.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Home : Screen("home")
    object Tables : Screen("tables")
    object Locations : Screen("locations")
}
