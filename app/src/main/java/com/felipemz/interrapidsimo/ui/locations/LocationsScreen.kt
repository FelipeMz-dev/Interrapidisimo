package com.felipemz.interrapidsimo.ui.locations

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun LocationsScreen(
    navController: NavController,
) {
    Scaffold { contentPadding ->
        Text(
            text = "Locations Screen",
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(contentPadding)
        )
    }
}