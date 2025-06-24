package com.felipemz.interrapidsimo.domain.repository

import com.felipemz.interrapidsimo.data.model.LocationResponse

interface LocationsRepository{
        suspend fun getLocations(): List<LocationResponse>
}