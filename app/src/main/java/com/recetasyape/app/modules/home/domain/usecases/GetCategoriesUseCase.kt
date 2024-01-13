package com.recetasyape.app.modules.home.domain.usecases

import com.recetasyape.app.modules.home.domain.model.Categories
import com.recetasyape.app.modules.home.domain.repository.RecipesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val repository: RecipesRepository) {
    suspend operator fun invoke(): Categories {
        return withContext(Dispatchers.IO) {
            repository.getCategories()
        }
    }
}