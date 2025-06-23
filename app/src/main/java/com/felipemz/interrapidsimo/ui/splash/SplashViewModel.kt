package com.felipemz.interrapidsimo.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipemz.interrapidsimo.BuildConfig
import com.felipemz.interrapidsimo.R
import com.felipemz.interrapidsimo.domain.usecase.GetUserAccountUseCase
import com.felipemz.interrapidsimo.domain.usecase.LoginUseCase
import com.felipemz.interrapidsimo.domain.usecase.ValidateVersionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val validateVersionUseCase: ValidateVersionUseCase,
    private val getUserAccountUseCase: GetUserAccountUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SplashState())
    val state: StateFlow<SplashState> = _state.asStateFlow()

    fun handleIntent(intent: SplashIntent) {
        when (intent) {
            is SplashIntent.ValidateVersion -> validateVersion()
            is SplashIntent.SubmitLogin -> loginDefault()
            else -> Unit
        }
    }

    private fun validateVersion() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        try {
            val remoteVersion = validateVersionUseCase()
            val localVersion = BuildConfig.VERSION_NAME.filter { it.isDigit() }.toIntOrNull() ?: 0

            val resultMessage = when {
                remoteVersion > localVersion -> ResultMessageType.NEW_VERSION
                remoteVersion == localVersion -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            versionSuccess = true
                        )
                    }
                    return@launch
                }
                else -> ResultMessageType.LOCAL_NEWER
            }

            _state.update { it.copy(isLoading = false, message = resultMessage) }

        } catch (e: Exception) {
            _state.update {
                it.copy(isLoading = false, error = "Error validating version: ${e.localizedMessage}")
            }
        }
    }

    private fun loginDefault() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            try {
                val result = getUserAccountUseCase()
                _state.update { it.copy(isLoading = false, hasUserLogged = result != null) }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "Error login: ${e.message ?: "Unknown"}"
                    )
                }
            }
        }
    }
}

enum class ResultMessageType(val message: Int) {
    NEW_VERSION(R.string.copy_previous_version),
    LOCAL_NEWER(R.string.copy_newer_version)
}