package com.recetasyape.app.modules.home.domain.repository

import com.recetasyape.app.modules.home.domain.model.Categories

interface RecipesRepository {
    suspend fun getCategories() : Categories
}