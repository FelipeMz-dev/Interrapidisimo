package com.felipemz.interrapidsimo.data.repository

import com.felipemz.interrapidsimo.data.api.LocationApi
import com.felipemz.interrapidsimo.data.model.LocationResponse
import com.felipemz.interrapidsimo.domain.repository.LocationsRepository
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(
    private val locationApi: LocationApi
) : LocationsRepository {

    override suspend fun getLocations(): List<LocationResponse> {
        return locationApi.getLocations()
    }
}