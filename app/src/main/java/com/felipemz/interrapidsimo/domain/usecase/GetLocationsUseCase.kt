package com.felipemz.interrapidsimo.domain.usecase

import com.felipemz.interrapidsimo.data.model.LocationResponse

interface GetLocationsUseCase{
    suspend operator fun invoke(): List<LocationResponse>
}