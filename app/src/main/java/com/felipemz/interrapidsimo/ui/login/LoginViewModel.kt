package com.felipemz.interrapidsimo.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipemz.interrapidsimo.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.ClearError -> _state.update { it.copy(error = null) }
            is LoginIntent.SubmitLogin -> login(intent.username, intent.password)
        }
    }

    private fun login(
        user: String,
        pass: String
    ) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            try {
                val result = loginUseCase(user.trim(), pass.trim())
                _state.update {
                    it.copy(
                        isLoading = false,
                        success = result,
                        credentialsError = !result
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "Error ${e.message ?: "unknown"}"
                    )
                }
            }
        }
    }
}
