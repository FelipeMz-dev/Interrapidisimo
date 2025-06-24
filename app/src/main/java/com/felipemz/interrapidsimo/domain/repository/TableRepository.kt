package com.felipemz.interrapidsimo.domain.repository

import com.felipemz.interrapidsimo.domain.model.Table

interface TableRepository {
    suspend fun sync()
    suspend fun getAll(): List<Table>
}
