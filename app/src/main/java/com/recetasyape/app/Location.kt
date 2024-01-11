package com.recetasyape.app


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double
)