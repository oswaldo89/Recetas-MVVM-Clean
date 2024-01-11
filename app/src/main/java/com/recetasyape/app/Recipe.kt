package com.recetasyape.app


import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("category_id") val categoryId: Int,
    @SerializedName("category_name") val categoryName: String,
    @SerializedName("dishes") val dishes: List<Dish>
)