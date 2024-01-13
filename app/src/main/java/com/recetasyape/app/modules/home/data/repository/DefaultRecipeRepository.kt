package com.recetasyape.app.modules.home.data.repository

import com.recetasyape.app.modules.home.data.datasource.RecipeRemoteDataSource
import com.recetasyape.app.modules.home.data.mappers.toDomainEntity
import com.recetasyape.app.modules.home.domain.model.Categories
import com.recetasyape.app.modules.home.domain.repository.RecipesRepository

class DefaultRecipeRepository(private val remoteDataSource: RecipeRemoteDataSource) : RecipesRepository {
    override suspend fun getCategories(): Categories {
        return remoteDataSource.getCategories().toDomainEntity()
    }
}