package com.felipemz.interrapidsimo.domain.model

data class Table(
    val pk: String = "",
    val nameTable: String = "",
    val queryCreation: String = "",
    val batchSize: Int = 0,
    val filter: String = "",
    val error: String = "",
    val numberFields: Int = 0,
    val methodApp: String = "",
    val lastSyncUpdate: String = ""
)