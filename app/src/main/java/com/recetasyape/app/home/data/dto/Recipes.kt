package com.recetasyape.app.home.data.dto


import com.google.gson.annotations.SerializedName

data class Recipes(
    @SerializedName("recipes") val recipes: List<Recipe>
)