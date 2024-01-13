package com.recetasyape.app.modules.home.data.datasource

import com.recetasyape.app.modules.home.data.dto.DataCategories
import retrofit2.http.GET

interface RecipeRemoteDataSource {
    @GET("recetas")
    suspend fun getCategories(): DataCategories
}