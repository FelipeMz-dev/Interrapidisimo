package com.felipemz.interrapidsimo.data.api

import com.felipemz.interrapidsimo.data.model.TableResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface TableApi {
    @GET("apicontrollerpruebas/api/SincronizadorDatos/ObtenerEsquema/true")
    @Headers(
        "Usuario: pam.meredy21",
        "Identificacion: 987204545",
        "Accept: text/json",
        "IdUsuario: pam.meredy21",
        "IdCentroServicio: 1295",
        "NombreCentroServicio: PTO/BOGOTA/CUND/COL/OF PRINCIPAL - CRA 30 # 7-45",
        "IdAplicativoOrigen: 9",
        "Content-Type: application/json"
    )
    suspend fun getTables(): List<TableResponse>
}