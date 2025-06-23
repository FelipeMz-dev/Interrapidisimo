package com.felipemz.interrapidsimo.data.usecase

import android.util.Base64
import com.felipemz.interrapidsimo.data.api.LoginApi
import com.felipemz.interrapidsimo.data.model.LoginRequest
import com.felipemz.interrapidsimo.data.model.toEntity
import com.felipemz.interrapidsimo.domain.repository.UserRepository
import com.felipemz.interrapidsimo.domain.usecase.LoginUseCase
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val api: LoginApi,
    private val userRepo: UserRepository
) : LoginUseCase {

    override suspend fun invoke(user: String, pass: String): Boolean {
        val request = LoginRequest(
            Usuario = Base64.encodeToString(user.toByteArray(), Base64.NO_WRAP),
            Password = Base64.encodeToString(pass.toByteArray(), Base64.NO_WRAP)
        )

        val response = api.login(request)

        val body = response.body() ?: throw Exception("Empty response")

        if (body.Identificacion.isNullOrEmpty()) return false

        userRepo.saveUser(body.toEntity())

        return true
    }
}