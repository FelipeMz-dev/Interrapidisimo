package com.felipemz.interrapidsimo.data.model

data class LoginRequest(
    val Mac: String = "",
    val NomAplicacion: String = "Controller APP",
    val Password: String,
    val Path: String = "",
    val Usuario: String
)