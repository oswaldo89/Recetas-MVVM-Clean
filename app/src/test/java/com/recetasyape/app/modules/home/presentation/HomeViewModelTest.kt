package com.recetasyape.app.modules.home.presentation

import androidx.lifecycle.Observer
import com.recetasyape.app.modules.home.domain.model.Categories
import com.recetasyape.app.modules.home.domain.model.Recipe
import com.recetasyape.app.modules.home.domain.usecases.GetCategoriesUseCase
import com.recetasyape.app.modules.home.domain.usecases.GetRecipesByQueryFilterUseCase
import com.recetasyape.app.utils.CoroutineTestExtension
import com.recetasyape.app.utils.InstantExecutorExtension
import com.recetasyape.app.utils.OneTimeEvent
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@HiltAndroidTest
@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class, CoroutineTestExtension::class)
class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel

    @MockK
    lateinit var getCategories: GetCategoriesUseCase

    @MockK
    lateinit var getRecipesByQueryFilter: GetRecipesByQueryFilterUseCase

    @RelaxedMockK
    private lateinit var stateObserver: Observer<HomeViewModel.State>

    @RelaxedMockK
    private lateinit var navigationObserver: Observer<OneTimeEvent<HomeViewModel.Navigation>>

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        homeViewModel = HomeViewModel(getCategories, getRecipesByQueryFilter)

        homeViewModel.navigationLiveData.observeForever(navigationObserver)
        homeViewModel.stateLiveData.observeForever(stateObserver)

    }

    @AfterEach
    fun tearDown() {
        confirmVerified(
            getCategories, getRecipesByQueryFilter
        )
    }

    @Test
    fun `init sets correct states on successful execution`() = runBlocking {
        // Arrange
        val data = mockk<Categories>(relaxed = true)
        coEvery { getCategories() } returns data

        // Act
        homeViewModel.init()

        coVerify {
            getCategories()
        }
    }

    @Test
    fun `onRecipeClicked sets correct navigation state`() = runBlocking {
        // Arrange
        val recipe = mockk<Recipe>(relaxed = true)

        // Act
        homeViewModel.onRecipeClicked(recipe)

        // Assert
        coVerify { navigationObserver.onChanged(any()) }
        val slot = slot<OneTimeEvent<HomeViewModel.Navigation>>()
        coVerify { navigationObserver.onChanged(capture(slot)) }

        // Verifica que el contenido no sea nulo antes de hacer el cast
        val content = slot.captured.getContentIfNotHandled()
        assertNotNull(content)

        // Verifica el estado deseado en función de la navegación realizada
        assertTrue(content is HomeViewModel.Navigation.GoToDetail)
        val navigation = content as HomeViewModel.Navigation.GoToDetail
        assertEquals(recipe, navigation.recipe)

        confirmVerified(navigationObserver)
    }

}