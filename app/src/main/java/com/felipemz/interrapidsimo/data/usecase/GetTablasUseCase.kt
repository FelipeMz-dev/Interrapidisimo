package com.felipemz.interrapidsimo.data.usecase

import com.felipemz.interrapidsimo.domain.model.Table
import com.felipemz.interrapidsimo.domain.repository.TableRepository
import com.felipemz.interrapidsimo.domain.usecase.GetTablesUseCase
import javax.inject.Inject

class GetTablesUseCaseImpl @Inject constructor(
    private val repository: TableRepository
) : GetTablesUseCase {
    override suspend fun invoke(): List<Table> {
        return repository.getAll().ifEmpty {
            repository.sync()
            repository.getAll()
        }
    }
}