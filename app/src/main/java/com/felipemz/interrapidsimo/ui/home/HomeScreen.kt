package com.felipemz.interrapidsimo.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold {
        Text(
            modifier = Modifier.padding(it),
            text = "Home Screen"
        )
        // TODO: Implement the HomeScreen UI
    }
}