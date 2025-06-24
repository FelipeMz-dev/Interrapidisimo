package com.felipemz.interrapidsimo.domain.usecase

import com.felipemz.interrapidsimo.domain.model.UserAccount

interface GetUserAccountUseCase {
    suspend operator fun invoke(): UserAccount?
}