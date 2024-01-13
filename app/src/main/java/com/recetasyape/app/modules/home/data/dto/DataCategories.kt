package com.recetasyape.app.modules.home.data.dto


import com.google.gson.annotations.SerializedName

data class DataCategories(
    @SerializedName("recipes") val categories: List<DataCategory>
)