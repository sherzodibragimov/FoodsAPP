package com.example.foodsapp.core.repostoriy

import com.example.foodsapp.core.model.allfood.AllFoodsResponse
import com.example.foodsapp.core.model.foods.FoodsByIdResponse
import com.example.foodsapp.core.network.AppClient
import com.example.foodsapp.core.util.ResultWrapper
import com.example.foodsapp.core.util.parseResponse
import kotlinx.coroutines.Dispatchers

class MealRepository {
    private val service = AppClient.getMealService()

    suspend fun getFoodRepo():ResultWrapper<AllFoodsResponse?,Any?>{
        val response=service.getFoods()
        return parseResponse(Dispatchers.IO){
            service.getFoods()
        }
    }
    suspend fun getFoodByIDRepo(a:Int):ResultWrapper<FoodsByIdResponse?,Any?>{
        val response=service.getFoodsByID(a)
        return parseResponse(Dispatchers.IO){
            service.getFoodsByID(a)
        }
    }

}