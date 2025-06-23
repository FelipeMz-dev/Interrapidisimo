package com.felipemz.interrapidsimo.ui.splash

data class SplashState(
    val isLoading: Boolean = false,
    val message: ResultMessageType? = null,
    val error: String? = null,
    val success: Boolean = false
)