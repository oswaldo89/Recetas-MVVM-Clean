package com.recetasyape.app.modules.home.data.repository

import com.recetasyape.app.modules.home.data.datasource.RecipeRemoteDataSource
import com.recetasyape.app.modules.home.data.dto.DataCategories
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DefaultRecipeRepositoryTest {

    private lateinit var repository: DefaultRecipeRepository

    @MockK
    lateinit var remoteDataSource: RecipeRemoteDataSource

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository = DefaultRecipeRepository(remoteDataSource)
    }

    @Test
    fun `getCategories should return data from remote data source`() = runBlocking {
        val movies = mockk<DataCategories>(relaxed = true)
        coEvery { remoteDataSource.getCategories() } returns movies

        val resp = repository.getCategories()

        Assertions.assertNotNull(resp)
        coVerify(exactly = 1) { remoteDataSource.getCategories() }
    }
}