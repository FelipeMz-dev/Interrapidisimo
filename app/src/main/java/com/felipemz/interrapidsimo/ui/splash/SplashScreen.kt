package com.felipemz.interrapidsimo.ui.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.felipemz.interrapidsimo.BuildConfig

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.handleIntent(SplashIntent.ValidateVersion)
    }

    LaunchedEffect(state.versionSuccess) {
        if (state.versionSuccess) {
            viewModel.handleIntent(SplashIntent.VerifyLogin)
        }
    }

    LaunchedEffect(state.hasUserLogged) {
        state.hasUserLogged?.let {
            navController.navigate(if (it) "home" else "login") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    Scaffold {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.isLoading -> CircularProgressIndicator()
                state.error != null -> Text("Error: ${state.error}", color = Color.Red)
                state.message != null -> Text(stringResource(state.message.message))
            }

            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomCenter),
                text = "Interrapidísimo version:${BuildConfig.VERSION_NAME}",
            )
        }
    }
}

