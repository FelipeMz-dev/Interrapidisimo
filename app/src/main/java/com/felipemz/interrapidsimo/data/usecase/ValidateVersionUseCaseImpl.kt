package com.felipemz.interrapidsimo.data.usecase

import com.felipemz.interrapidsimo.data.api.VersionApi
import com.felipemz.interrapidsimo.domain.usecase.ValidateVersionUseCase
import jakarta.inject.Inject

class ValidateVersionUseCaseImpl @Inject constructor(
    private val api: VersionApi
) : ValidateVersionUseCase {

    override suspend fun invoke(): Int {
        val response = api.getVersion()
        return response
    }
}