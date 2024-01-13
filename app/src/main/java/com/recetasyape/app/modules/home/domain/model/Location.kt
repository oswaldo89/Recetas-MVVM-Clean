package com.recetasyape.app.modules.home.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Location(
    val latitude: Double,
    val longitude: Double
) : Parcelable