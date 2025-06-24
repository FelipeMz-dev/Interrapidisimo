package com.felipemz.interrapidsimo.ui.locations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.felipemz.interrapidsimo.ui.common.CommonAppBar
import com.felipemz.interrapidsimo.ui.navigation.Screen

@Composable
fun LocationsScreen(
    navController: NavController,
    viewModel: LocationsViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.handleIntent(LocationIntent.LoadLocations)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CommonAppBar("Localidades") {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Locations.route) { inclusive = true }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                state.isLoading -> CircularProgressIndicator()
                state.error != null -> Text("Error: ${state.error}", color = MaterialTheme.colorScheme.error)
                state.locations?.isEmpty() == true -> Text("No hay localidades disponibles")
                state.locations != null -> LazyColumn {
                    items(state.locations) { loc ->
                        Column(Modifier.padding(8.dp)) {
                            Text("📍 ${loc.AbreviacionCiudad}")
                            Text(loc.NombreCompleto, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}