package com.recetasyape.app.modules.home.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Recipe(
    val id: Int,
    val title: String,
    val description: String,
    val ingredientsCount: Int,
    val difficultyLevel: String,
    val time: Int,
    val calories: Int,
    val imageUrl: String,
    val location: Location,
    val rating : Float,
    val ingredients: List<String>
) : Parcelable