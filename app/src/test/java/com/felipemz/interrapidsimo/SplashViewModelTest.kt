package com.felipemz.interrapidsimo

import app.cash.turbine.turbineScope
import com.felipemz.interrapidsimo.data.usecase.ValidateVersionUseCaseImpl
import com.felipemz.interrapidsimo.ui.splash.ResultMessageType
import com.felipemz.interrapidsimo.ui.splash.SplashIntent
import com.felipemz.interrapidsimo.ui.splash.SplashViewModel
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SplashViewModelTest {

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    private lateinit var useCase: ValidateVersionUseCaseImpl
    private lateinit var viewModel: SplashViewModel

    @Before
    fun setUp() {
        useCase = mockk()
        viewModel = SplashViewModel(useCase)
    }

    @Test
    fun `emit loading and correct message for all version scenarios`() = runTest {
        // Given
        val scenarios = listOf(
            Pair(101, ResultMessageType.NEW_VERSION),
            Pair(100, ResultMessageType.UPDATED),
            Pair(99, ResultMessageType.LOCAL_NEWER)
        )

        for ((remoteVersion, expectedType) in scenarios) {
            every { runBlocking { useCase.invoke() } } returns remoteVersion

            turbineScope {
                val stateFlow = viewModel.state.testIn(this)

                // When
                viewModel.handleIntent(SplashIntent.ValidateVersion)

                // Then
                stateFlow.awaitItem()
                val loadingState = stateFlow.awaitItem()
                assertTrue(loadingState.isLoading)

                val successState = stateFlow.awaitItem()
                assertFalse(successState.isLoading)
                assertEquals(expectedType.message, successState.message)

                stateFlow.cancel()
            }
        }
    }

    @Test
    fun `emit error state when exception is thrown`() = runTest {
        // Given
        val messageError = "Network error"
        every { runBlocking { useCase.invoke() } } throws Exception(messageError)

        turbineScope {
            val stateFlow = viewModel.state.testIn(this)

            // When
            viewModel.handleIntent(SplashIntent.ValidateVersion)

            // Then
            stateFlow.awaitItem()
            stateFlow.awaitItem() // loading
            val errorState = stateFlow.awaitItem()

            assertFalse(errorState.isLoading)
            assertTrue(errorState.error?.contains(messageError) == true)

            stateFlow.cancel()
        }
    }
}