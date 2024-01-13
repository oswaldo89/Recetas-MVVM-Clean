package com.recetasyape.app.modules.home.data.mappers

import com.recetasyape.app.modules.home.data.dto.DataCategories
import com.recetasyape.app.modules.home.data.dto.DataCategory
import com.recetasyape.app.modules.home.data.dto.DataLocation
import com.recetasyape.app.modules.home.data.dto.DataRecipe
import com.recetasyape.app.modules.home.domain.model.Categories
import com.recetasyape.app.modules.home.domain.model.Category
import com.recetasyape.app.modules.home.domain.model.Location
import com.recetasyape.app.modules.home.domain.model.Recipe

fun DataCategories.toDomainEntity(): Categories {
    return Categories(
        categories = categories.map { it.toDomainEntity() }
    )
}

fun Categories.toDto(): DataCategories {
    return DataCategories(
        categories = categories.map { it.toDto() }
    )
}

fun DataCategory.toDomainEntity(): Category {
    return Category(
        categoryId,
        categoryName,
        recipes.map { it.toDomainEntity() }
    )
}

fun Category.toDto(): DataCategory {
    return DataCategory(
        categoryId,
        categoryName,
        recipes.map { it.toDto() }
    )
}

//

fun DataRecipe.toDomainEntity(): Recipe {
    return Recipe(
        id,
        title,
        description,
        ingredientsCount,
        difficultyLevel,
        time,
        calories,
        imageUrl,
        location.toDomainEntity()
    )
}

fun Recipe.toDto(): DataRecipe {
    return DataRecipe(
        id,
        title,
        description,
        ingredientsCount,
        difficultyLevel,
        time,
        calories,
        imageUrl,
        location.toDto()
    )
}

fun DataLocation.toDomainEntity(): Location {
    return Location(
        latitude, longitude
    )
}

fun Location.toDto(): DataLocation {
    return DataLocation(
        latitude, longitude
    )
}