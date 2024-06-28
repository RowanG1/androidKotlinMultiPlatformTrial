package com.rlg.consumekmm

import com.rlg.play_kotlin_multi_plat.FetchDataUseCase
import com.rlg.play_kotlin_multi_plat.SharedViewModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SharedViewModelTest {

    private val useCase = mockk<FetchDataUseCase>()
    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var viewModel: SharedViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SharedViewModel(useCase, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `fetchData should update state with success result`() = runTest {
        // Arrange
        val expectedData = "test data"
        val result = Result.success(expectedData)
        coEvery { useCase.invoke() } returns result

        println(useCase.invoke())

        // Act
        viewModel.fetchData { }

        // Assert
        assertEquals(expectedData, viewModel.state.getLatestValue()?.data)
        assertEquals(false, viewModel.state.getLatestValue()?.isLoading)
        assertNull(viewModel.state.getLatestValue()?.error)
    }

    @Test
    fun `fetchData should update state with error result`() = runTest {
        // Arrange
        val expectedError = Throwable("test error")
        val result = Result.failure<String>(expectedError)
        coEvery { useCase.invoke() } returns result

        var actualError: Throwable? = null

        // Act
        viewModel.fetchData { error -> actualError = error }

        // Assert
        assertEquals(expectedError, viewModel.state.getLatestValue()?.error)
        assertEquals(false, viewModel.state.getLatestValue()?.isLoading)
        assertEquals("", viewModel.state.getLatestValue()?.data)
        assertEquals(expectedError, actualError)
    }

    @Test
    fun `incrementCounter should increase counter by 1`() {
        // Arrange
        val initialCounter = viewModel.state.getLatestValue()?.counter ?: 0

        // Act
        viewModel.incrementCounter()

        // Assert
        assertEquals(initialCounter + 1, viewModel.state.getLatestValue()?.counter)
    }
}