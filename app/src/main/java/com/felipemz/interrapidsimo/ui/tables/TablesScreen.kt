package com.felipemz.interrapidsimo.ui.tables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.felipemz.interrapidsimo.data.model.TableResponse
import com.felipemz.interrapidsimo.domain.model.Table
import com.felipemz.interrapidsimo.ui.common.CommonAppBar
import com.felipemz.interrapidsimo.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TablesScreen(
    navController: NavController,
    viewModel: TablesViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.handleIntent(TablesIntent.LoadTables)
    }

    Scaffold(
        topBar = {
            CommonAppBar("Tablas") {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Tables.route) { inclusive = true }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                state.isLoading -> CircularProgressIndicator()
                state.error != null -> Text("Error: ${state.error}", color = MaterialTheme.colorScheme.error)
                state.tables?.isEmpty() == true -> Text("No hay tablas disponibles")
                state.tables != null -> {
                    LazyColumn {
                        items(state.tables) { TableItem(it) }
                    }
                }
            }
        }
    }
}

@Composable
fun TableItem(table: Table) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Text(
            text = "Tabla: ${table.nameTable}",
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "Primary key: ${table.pk}",
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "QueryCreacion: ${table.queryCreation}",
            modifier = Modifier.padding(8.dp),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "BatchSize: ${table.batchSize}",
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "Filtro: ${table.filter}",
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "Error: ${table.error}",
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "NumeroCampos: ${table.numberFields}",
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "MetodoApp: ${table.methodApp}",
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "FechaActualizacionSincro: ${table.lastSyncUpdate}",
            modifier = Modifier.padding(8.dp)
        )
    }
}