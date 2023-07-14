package com.example.foodsapp.core.model.categoriy


import com.google.gson.annotations.SerializedName

data class Meal(
    @SerializedName("strCategory")
    val strCategory: String
)