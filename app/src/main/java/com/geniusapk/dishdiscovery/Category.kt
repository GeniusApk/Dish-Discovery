package com.geniusapk.dishdiscovery

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(                          //This is data class of each category , it should be as in api
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
):Parcelable
// Data class representing API response containing list of categories

data class CategoriesResponse(val categories: List<Category>)