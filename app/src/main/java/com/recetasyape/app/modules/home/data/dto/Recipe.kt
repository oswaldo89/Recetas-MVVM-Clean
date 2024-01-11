package com.recetasyape.app.modules.home.data.dto


import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("ingredients_count") val ingredientsCount: Int,
    @SerializedName("difficulty_level") val difficultyLevel: String,
    @SerializedName("time") val time: Int,
    @SerializedName("calories") val calories: Int,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("location") val location: Location,
)