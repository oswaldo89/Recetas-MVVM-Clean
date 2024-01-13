package com.recetasyape.app.modules.home.data.dto


import com.google.gson.annotations.SerializedName

data class DataCategory(
    @SerializedName("category_id") val categoryId: Int,
    @SerializedName("category_name") val categoryName: String,
    @SerializedName("dishes") val recipes: List<DataRecipe>
)