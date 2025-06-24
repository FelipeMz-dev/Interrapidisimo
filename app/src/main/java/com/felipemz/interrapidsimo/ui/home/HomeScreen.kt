package com.felipemz.interrapidsimo.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.felipemz.interrapidsimo.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val user = "pam.meredy21"
    val pass = "Inter2021"

    val state = viewModel.state.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.handleIntent(HomeIntent.LoadUser)
    }

    LaunchedEffect(state.isLoggedOut) {
        if (state.isLoggedOut) {
            navController.navigate("login") {
                popUpTo("home") { inclusive = true }
            }
        }
    }

    Scaffold(
        topBar = {
            HomeAppBar(viewModel)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            when {
                state.isLoading -> CircularProgressIndicator()
                state.error != null -> Text(
                    text = "Error: ${state.error}",
                    color = MaterialTheme.colorScheme.error
                )
                state.user != null -> with(state.user) {
                    HomeBody(
                        user = user,
                        id = id,
                        name = name,
                        onTablesClick = {
                            navController.navigate(Screen.Tables.route)
                        },
                        onLocationsClick = {
                            navController.navigate(Screen.Locations.route)
                        }
                    )
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun HomeAppBar(viewModel: HomeViewModel) {
    TopAppBar(
        title = {
            Row {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Logout Icon",
                    modifier = Modifier.padding(end = 8.dp)
                )

                Text("Home")
            }
        },
        actions = {
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = { viewModel.handleIntent(HomeIntent.Logout) },
            ) {
                Text("Logout")

                Icon(
                    modifier = Modifier.padding(start = 8.dp),
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Logout Icon",
                )
            }
        }
    )
}

@Composable
private fun HomeBody(
    user: String,
    id: String,
    name: String,
    onTablesClick: () -> Unit,
    onLocationsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Default.AccountBox,
            contentDescription = "Home Icon",
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Text("Usuario: $user", fontWeight = FontWeight.Bold)
        Text("Identificación: $id")
        Text("Nombre: $name")

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = onTablesClick, modifier = Modifier.fillMaxWidth(0.8f)) {
            Text("Tablas")
        }

        Spacer(modifier = Modifier.weight(0.5f))

        Button(onClick = onLocationsClick, modifier = Modifier.fillMaxWidth(0.8f)) {
            Text("Localidades")
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}