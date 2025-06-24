package com.felipemz.interrapidsimo.data.model

import com.felipemz.interrapidsimo.data.db.entity.UserEntity

data class LoginResponse(
    val Usuario: String? = null,
    val Identificacion: String? = null,
    val Nombre: String? = null,
)

fun LoginResponse.toEntity(): UserEntity {
    return UserEntity(
        id = Identificacion.orEmpty(),
        user = Usuario.orEmpty(),
        name = Nombre.orEmpty()
    )
}