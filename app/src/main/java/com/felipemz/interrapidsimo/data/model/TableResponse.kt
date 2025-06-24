package com.felipemz.interrapidsimo.data.model

import com.felipemz.interrapidsimo.data.db.entity.TableEntity

data class TableResponse(
    val NombreTabla: String? = null,
    val Pk: String? = null,
    val QueryCreacion: String? = null,
    val BatchSize: Int? = null,
    val Filtro: String? = null,
    val Error: String? = null,
    val NumeroCampos: Int? = null,
    val MetodoApp: String? = null,
    val FechaActualizacionSincro: String? = null,
)

fun TableResponse.toEntity(): TableEntity {
    return TableEntity(
        pk = Pk.orEmpty(),
        nameTable = NombreTabla.orEmpty(),
        queryCreation = QueryCreacion.orEmpty(),
        batchSize = BatchSize ?: 0,
        filter = Filtro.orEmpty(),
        error = Error.orEmpty(),
        numberFields = NumeroCampos ?: 0,
        methodApp = MetodoApp.orEmpty(),
        lastSyncUpdate = FechaActualizacionSincro.orEmpty()
    )
}