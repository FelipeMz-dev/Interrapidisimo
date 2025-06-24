package com.felipemz.interrapidsimo.ui.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipemz.interrapidsimo.domain.usecase.GetLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val getLocations: GetLocationsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LocationsState())
    val state: StateFlow<LocationsState> = _state.asStateFlow()

    fun handleIntent(intent: LocationIntent) {
        when (intent) {
            LocationIntent.LoadLocations -> load()
        }
    }

    private fun load() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        try {
            val result = getLocations()
            _state.update { LocationsState(locations = result) }
        } catch (e: Exception) {
            _state.update { LocationsState(error = e.message) }
        }
    }
}
