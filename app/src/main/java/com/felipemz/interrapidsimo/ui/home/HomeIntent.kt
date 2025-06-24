package com.felipemz.interrapidsimo.ui.home

sealed class HomeIntent {

    object LoadUser : HomeIntent()

    object Logout : HomeIntent()
}