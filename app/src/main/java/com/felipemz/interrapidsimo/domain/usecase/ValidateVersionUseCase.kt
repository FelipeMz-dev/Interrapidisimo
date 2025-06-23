package com.felipemz.interrapidsimo.domain.usecase

interface ValidateVersionUseCase {
    suspend operator fun invoke(): Int
}