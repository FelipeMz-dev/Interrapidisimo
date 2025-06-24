package com.felipemz.interrapidsimo.ui.tables

import com.felipemz.interrapidsimo.domain.model.Table

data class TablesState(
    val isLoading: Boolean = false,
    val tables: List<Table>? = null,
    val error: String? = null
)