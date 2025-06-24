package com.felipemz.interrapidsimo.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.felipemz.interrapidsimo.domain.model.Table

@Entity(tableName = "tables")
data class TableEntity(
    @PrimaryKey val pk: String = "",
    val nameTable: String = "",
    val queryCreation: String = "",
    val batchSize: Int = 0,
    val filter: String = "",
    val error: String = "",
    val numberFields: Int = 0,
    val methodApp: String = "",
    val lastSyncUpdate: String = ""
)

fun TableEntity.toDomain(): Table {
    return Table(
        pk = pk,
        nameTable = nameTable,
        queryCreation = queryCreation,
        batchSize = batchSize,
        filter = filter,
        error = error,
        numberFields = numberFields,
        methodApp = methodApp,
        lastSyncUpdate = lastSyncUpdate
    )
}