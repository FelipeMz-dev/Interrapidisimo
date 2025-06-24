package com.felipemz.interrapidsimo.ui.home

import com.felipemz.interrapidsimo.domain.model.UserAccount

data class HomeState(
    val isLoading: Boolean = false,
    val user: UserAccount? = null,
    val error: String? = null,
    val isLoggedOut: Boolean = false
)