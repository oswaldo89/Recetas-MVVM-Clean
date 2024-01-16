package com.recetasyape.app.modules.home.domain.usecases

import com.recetasyape.app.modules.home.domain.model.Categories
import com.recetasyape.app.modules.home.domain.model.Category
import com.recetasyape.app.modules.home.domain.model.Location
import com.recetasyape.app.modules.home.domain.model.Recipe
import com.recetasyape.app.utils.CoroutineTestExtension
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
        val expectedCategories = dummyData()

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
        val expectedCategories = dummyData()

        every { data.categories } returns expectedCategories

        // Act
        val result = getRecipesByQueryFilter(expectedCategories, searchTerm)

        // Assert
        assertEquals(0, result.size)
    }

    private fun dummyData(): List<Category> {
        return listOf(
            Category(
                1, "Desayunos FIT", listOf(
                    Recipe(
                        1,
                        "Tortilla de claras con espinacas",
                        "Una opción deliciosa y baja en calorías para empezar el día.",
                        4,
                        "BAJA",
                        15,
                        200,
                        "https://contenido.quesosdeeuropa.com/uploads/quiche_b88618d2d8.jpg",
                        Location(19.4326, -99.1332),
                        5f,
                        listOf("Ingrediente 1", "Ingrediente 2")
                    )
                )
            )
        )
    }


}