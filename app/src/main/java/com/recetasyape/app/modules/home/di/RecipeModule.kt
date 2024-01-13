package com.recetasyape.app.modules.home.di

import com.recetasyape.app.modules.home.data.datasource.RecipeRemoteDataSource
import com.recetasyape.app.modules.home.data.repository.DefaultRecipeRepository
import com.recetasyape.app.modules.home.domain.repository.RecipesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RecipeModule {

    @Provides
    fun provideDefaultRecipeRepository(apiService: RecipeRemoteDataSource): RecipesRepository = DefaultRecipeRepository(apiService)
}