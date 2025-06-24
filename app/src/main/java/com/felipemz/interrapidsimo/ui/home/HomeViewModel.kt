package com.felipemz.interrapidsimo.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipemz.interrapidsimo.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun handleIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.LoadUser -> loadUser()
            HomeIntent.Logout -> logout()
        }
    }

    private fun loadUser() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            try {
                val user = userRepository.getUser()
                    ?: throw Exception("No hay usuario guardado")

                _state.update {
                    it.copy(
                        isLoading = false,
                        user = user
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(isLoading = false, error = e.message)
                }
            }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            userRepository.deleteUser()
            _state.update { it.copy(isLoggedOut = true) }
        }
    }
}
