package com.felipemz.interrapidsimo.data.usecase

import com.felipemz.interrapidsimo.data.model.LocationResponse
import com.felipemz.interrapidsimo.domain.repository.LocationsRepository
import com.felipemz.interrapidsimo.domain.usecase.GetLocationsUseCase
import javax.inject.Inject

class GetLocationsUseCaseImpl @Inject constructor(
    private val repo: LocationsRepository
) : GetLocationsUseCase {
    override suspend operator fun invoke(): List<LocationResponse> = repo.getLocations()
}