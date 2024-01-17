package com.recetasyape.app.modules.home.domain.usecases

import com.recetasyape.app.modules.home.domain.model.Categories
import com.recetasyape.app.modules.home.domain.model.Category
import com.recetasyape.app.modules.home.domain.model.Location
import com.recetasyape.app.modules.home.domain.model.Recipe
import com.recetasyape.app.utils.CoroutineTestExtension
import com.recetasyape.app.utils.DummyData
import com.recetasyape.app.utils.InstantExecutorExtension
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class, CoroutineTestExtension::class)
class GetRecipesByQueryFilterUseCaseTest {
    private lateinit var getRecipesByQueryFilter: GetRecipesByQueryFilterUseCase

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getRecipesByQueryFilter = GetRecipesByQueryFilterUseCase()
    }

    @Test
    fun `invoke should return filtered recipes by query`() {
        // Arrange
        val searchTerm = "Tortilla"
        val data = mockk<Categories>(relaxed = true)
        val expectedCategories = DummyData.get()

        every { data.categories } returns expectedCategories

        // Act
        val result = getRecipesByQueryFilter(expectedCategories, searchTerm)

        // Assert
        assertEquals(1, result.size)
        assertEquals("Tortilla de claras con espinacas", result[0].title)
    }

    @Test
    fun `invoke should return empty list when no matching recipes found`() {
        // Arrange
        val searchTerm = "NonexistentRecipe"
        val data = mockk<Categories>(relaxed = true)
        val expectedCategories = DummyData.get()

        every { data.categories } returns expectedCategories

        // Act
        val result = getRecipesByQueryFilter(expectedCategories, searchTerm)

        // Assert
        assertEquals(0, result.size)
    }


}