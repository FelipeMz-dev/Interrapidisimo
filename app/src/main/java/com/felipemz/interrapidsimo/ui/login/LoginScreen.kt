package com.felipemz.interrapidsimo.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.felipemz.interrapidsimo.R

@Composable
fun LoginScreen(
    controller: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsState().value
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    LaunchedEffect(state.success) {
        if (state.success) {
            controller.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    LaunchedEffect(state.credentialsError) {
        if (state.credentialsError) {
            user = ""
            pass = ""
        }
    }

    state.error?.let{
        AlertDialog(
            onDismissRequest = { viewModel.handleIntent(LoginIntent.ClearError) },
            title = { Text("Error") },
            text = { Text(it) },
            confirmButton = {
                Button(onClick = { viewModel.handleIntent(LoginIntent.ClearError) }) {
                    Text("Acceptar")
                }
            }
        )
    }

    Scaffold {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(it),
                text = stringResource(R.string.copy_login),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = user,
                onValueChange = { user = it },
                label = { Text(stringResource(R.string.copy_user)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            OutlinedTextField(
                value = pass,
                onValueChange = { pass = it },
                label = { Text(stringResource(R.string.copy_password)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )

            when {
                state.isLoading -> CircularProgressIndicator()
                state.credentialsError -> Text(
                    text = stringResource(R.string.copy_error_credential),
                    color = MaterialTheme.colorScheme.error
                )
            }

            Button(
                modifier = Modifier.padding(top = 16.dp),
                enabled = !state.isLoading && user.isNotBlank() && pass.isNotBlank(),
                onClick = { viewModel.handleIntent(LoginIntent.SubmitLogin(user, pass)) }
            ) {
                Text(stringResource(R.string.copy_login_title))
            }
        }
    }
}