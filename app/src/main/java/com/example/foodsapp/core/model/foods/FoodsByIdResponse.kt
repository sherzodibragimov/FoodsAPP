package com.example.foodsapp.core.model.foods


import com.google.gson.annotations.SerializedName

data class FoodsByIdResponse(
    @SerializedName("meals")
    val meals: List<Meal>
)