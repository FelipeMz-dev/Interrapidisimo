package com.felipemz.interrapidsimo.ui.locations

sealed class LocationIntent {
    object LoadLocations : LocationIntent()
}