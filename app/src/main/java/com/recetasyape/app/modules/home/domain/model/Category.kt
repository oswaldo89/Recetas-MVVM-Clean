package com.recetasyape.app.modules.home.domain.model

data class Category(
    val categoryId: Int,
    val categoryName: String,
    val recipes: List<Recipe>
)