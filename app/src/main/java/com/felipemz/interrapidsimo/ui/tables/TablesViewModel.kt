package com.felipemz.interrapidsimo.ui.tables

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipemz.interrapidsimo.domain.usecase.GetTablesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TablesViewModel @Inject constructor(
    private val getTablesUseCase: GetTablesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(TablesState())
    val state: StateFlow<TablesState> = _state.asStateFlow()

    fun handleIntent(intent: TablesIntent) {
        when (intent) {
            TablesIntent.LoadTables -> load()
        }
    }

    private fun load() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        try {
            val tables = getTablesUseCase()
            _state.update { TablesState(tables = tables) }
        } catch (e: Exception) {
            _state.update { TablesState(error = e.message) }
        }
    }
}
