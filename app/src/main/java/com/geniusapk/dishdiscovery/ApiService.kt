package com.geniusapk.dishdiscovery

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET



// Retrofit setup for making API calls

private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
// Interface defining API endpoints

    val recipeService: ApiService = retrofit.create(ApiService::class.java)

interface ApiService {

    @GET("categories.php")
    suspend fun getCategories():CategoriesResponse
}