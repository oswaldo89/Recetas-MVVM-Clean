package com.recetasyape.app.modules.home.domain.usecases

import com.recetasyape.app.modules.home.domain.model.Category
import com.recetasyape.app.modules.home.domain.model.Recipe
import javax.inject.Inject

class GetRecipesByQueryFilterUseCase @Inject constructor() {
    operator fun invoke(categories: List<Category>, query: String): List<Recipe> {
        val recipes = categories.flatMap { it.recipes }
        return recipes.filter { it.title.contains(query, ignoreCase = true) }
    }
}