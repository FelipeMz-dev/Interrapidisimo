package com.felipemz.interrapidsimo.ui.login

data class LoginState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val credentialsError: Boolean = false,
    val error: String? = null
)