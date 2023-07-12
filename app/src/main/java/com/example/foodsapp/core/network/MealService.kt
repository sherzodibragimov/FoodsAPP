package com.example.foodsapp.core.network

import com.example.foodsapp.core.model.allfood.AllFoodsResponse
import com.example.foodsapp.core.model.foods.FoodsByIdResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealService {

    @GET("/api/json/v1/1/search.php")
    suspend fun getFoods(@Query("s") name:String=""):Response<AllFoodsResponse?>

    @GET("/api/json/v1/1/lookup.php")
    suspend fun getFoodsByID(@Query("i") id:Int):Response<FoodsByIdResponse?>

}