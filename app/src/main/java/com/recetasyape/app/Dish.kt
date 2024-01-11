package com.recetasyape.app


import com.google.gson.annotations.SerializedName

data class Dish(
    @SerializedName("calories") val calories: Int,
    @SerializedName("description") val description: String,
    @SerializedName("difficulty_level") val difficultyLevel: String,
    @SerializedName("id") val id: Int,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("ingredients_count") val ingredientsCount: Int,
    @SerializedName("location") val location: Location,
    @SerializedName("time") val time: Int,
    @SerializedName("title") val title: String
)