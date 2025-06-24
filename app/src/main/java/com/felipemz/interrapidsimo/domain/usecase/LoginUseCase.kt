package com.felipemz.interrapidsimo.domain.usecase

interface LoginUseCase {
    suspend operator fun invoke(user: String, pass: String): Boolean
}