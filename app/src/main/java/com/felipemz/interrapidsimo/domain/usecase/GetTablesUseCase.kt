package com.felipemz.interrapidsimo.domain.usecase

import com.felipemz.interrapidsimo.domain.model.Table

interface GetTablesUseCase {
    suspend operator fun invoke(): List<Table>
}