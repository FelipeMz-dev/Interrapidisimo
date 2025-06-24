package com.felipemz.interrapidsimo.data.api

import com.felipemz.interrapidsimo.data.model.LocationResponse
import retrofit2.http.GET

interface LocationApi {
    @GET("apicontrollerpruebas/api/ParametrosFramework/ObtenerLocalidadesRecogidas")
    suspend fun getLocations(): List<LocationResponse>
}