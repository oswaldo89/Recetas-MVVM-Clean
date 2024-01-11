package com.recetasyape.app


import com.google.gson.annotations.SerializedName

data class Recipes(
    @SerializedName("recipes")
    val recipes: List<Recipe>
)