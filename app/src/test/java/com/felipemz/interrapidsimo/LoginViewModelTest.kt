package com.felipemz.interrapidsimo

import app.cash.turbine.turbineScope
import com.felipemz.interrapidsimo.domain.usecase.LoginUseCase
import com.felipemz.interrapidsimo.ui.login.LoginIntent
import com.felipemz.interrapidsimo.ui.login.LoginViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var loginUseCase: LoginUseCase
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        loginUseCase = mockk()
        viewModel = LoginViewModel(loginUseCase)
    }

    @Test
    fun `login emits loading then success`() = runTest {
        // Given
        coEvery { loginUseCase.invoke("user", "pass") } returns true

        turbineScope {
            val stateFlow = viewModel.state.testIn(this)

            // When
            viewModel.handleIntent(LoginIntent.SubmitLogin("user", "pass"))

            // Then
            stateFlow.awaitItem() // initial
            val loadingState = stateFlow.awaitItem()
            assertTrue(loadingState.isLoading)

            val successState = stateFlow.awaitItem()
            assertFalse(successState.isLoading)
            assertTrue(successState.success)
            assertNull(successState.error)

            stateFlow.cancel()
        }
    }

    @Test
    fun `login emits error on failure`() = runTest {
        // Given
        coEvery { loginUseCase.invoke("user", "wrongpass") } returns false

        turbineScope {
            val stateFlow = viewModel.state.testIn(this)

            // When
            viewModel.handleIntent(LoginIntent.SubmitLogin("user", "wrongpass"))

            // Then
            stateFlow.awaitItem() // initial
            stateFlow.awaitItem() // loading
            val errorState = stateFlow.awaitItem()

            assertFalse(errorState.isLoading)
            assertFalse(errorState.success)
            assertTrue(errorState.credentialsError)
            assertNull(errorState.error)

            stateFlow.cancel()
        }
    }
}
