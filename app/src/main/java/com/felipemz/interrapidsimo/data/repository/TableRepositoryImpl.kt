package com.felipemz.interrapidsimo.data.repository

import com.felipemz.interrapidsimo.data.api.TableApi
import com.felipemz.interrapidsimo.data.db.dao.TableDao
import com.felipemz.interrapidsimo.data.db.entity.toDomain
import com.felipemz.interrapidsimo.data.model.toEntity
import com.felipemz.interrapidsimo.domain.model.Table
import com.felipemz.interrapidsimo.domain.repository.TableRepository
import javax.inject.Inject

class TableRepositoryImpl @Inject constructor(
    private val api: TableApi,
    private val dao: TableDao
) : TableRepository {

    override suspend fun sync() {
        val response = api.getTables()
        val entities = response.map { it.toEntity() }
        dao.insertAll(entities)
    }

    override suspend fun getAll(): List<Table> = dao.getAll().map { it.toDomain() }
}
