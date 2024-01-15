package com.recetasyape.app.modules.home.domain.usecases

import com.recetasyape.app.modules.home.domain.model.Categories
import com.recetasyape.app.modules.home.domain.model.Category
import com.recetasyape.app.modules.home.domain.repository.RecipesRepository
import com.recetasyape.app.utils.CoroutineTestExtension
import com.recetasyape.app.utils.InstantExecutorExtension
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class, CoroutineTestExtension::class)
class GetCategoriesUseCaseTest {
    private lateinit var getCategories: GetCategoriesUseCase
    private var recipesRepository: RecipesRepository = mockk(relaxUnitFun = true)

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getCategories = GetCategoriesUseCase(recipesRepository)
    }

    @Test
    fun `invoke should return categories from repository`() = runBlocking {
        // Arrange
        val data = mockk<Categories>(relaxed = true)
        val expectedCategories = mockk<List<Category>>(relaxed = true)

        coEvery { data.categories } returns expectedCategories
        coEvery { recipesRepository.getCategories() } returns data

        // Act
        val result = getCategories()

        // Assertt
        assertTrue(result.categories.isEmpty())
    }

    @Test
    fun `invoke should handle empty list of categories from repository`() = runBlocking {
        // Arrange
        val data = mockk<Categories>(relaxed = true)
        coEvery { data.categories } returns emptyList()
        coEvery { recipesRepository.getCategories() } returns data

        // Act
        val result = getCategories()

        // Assert
        assertTrue(result.categories.isEmpty())
    }

    @Test
    fun `invoke should handle error from repository`() = runBlocking {
        // Arrange
        val expectedErrorMessage = "Error fetching categories"
        coEvery { recipesRepository.getCategories() } throws RuntimeException(expectedErrorMessage)

        // Act
        val result = runCatching { getCategories() }

        // Assert
        assertTrue(result.isFailure)
        assertEquals(expectedErrorMessage, result.exceptionOrNull()?.message)
    }

}