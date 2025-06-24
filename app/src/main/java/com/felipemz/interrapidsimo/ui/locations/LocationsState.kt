package com.felipemz.interrapidsimo.ui.locations

import com.felipemz.interrapidsimo.data.model.LocationResponse

data class LocationsState(
    val isLoading: Boolean = false,
    val locations: List<LocationResponse>? = null,
    val error: String? = null
)
