package com.felipemz.interrapidsimo.data.api

import retrofit2.http.GET

interface VersionApi {
    @GET("apicontrollerpruebas/api/ParametrosFramework/ConsultarParametrosFramework/VPStoreAppControl")
    suspend fun getVersion(): Int
}