package com.example.foodsapp.core.model.categoriy


import com.google.gson.annotations.SerializedName

data class MealCategory(
    @SerializedName("meals")
    val meals: List<Meal>
)