package com.felipemz.interrapidsimo.ui.login

sealed class LoginIntent {

    object ClearError : LoginIntent()

    data class SubmitLogin(val username: String, val password: String) : LoginIntent()
}