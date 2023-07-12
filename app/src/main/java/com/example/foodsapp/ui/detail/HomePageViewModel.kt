package com.example.foodsapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodsapp.core.model.allfood.AllFoodsResponse
import com.example.foodsapp.core.model.foods.FoodsByIdResponse
import com.example.foodsapp.core.repostoriy.MealRepository
import com.example.foodsapp.core.util.ResultWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailVIewModel:ViewModel() {
    private var repo=MealRepository()
    /** Success Data */
    private val dataLiveSuccess:MutableLiveData<FoodsByIdResponse?> = MutableLiveData<FoodsByIdResponse?>()
    val successResponse:LiveData<FoodsByIdResponse?>
        get()=dataLiveSuccess
    /** Error Data*/
    private val dataLiveError:MutableLiveData<String?> = MutableLiveData<String?>()
    val errorDataLive:LiveData<String?>
        get()=dataLiveError

    fun getFoods(a:Int){
        CoroutineScope(Dispatchers.IO).launch {
            val result= repo.getFoodByIDRepo(a)
            when(result){
                is ResultWrapper.Success->{
                    dataLiveSuccess.postValue(result.data)
                }
                is ResultWrapper.ErrorResponse->{
                    dataLiveError.postValue(result.message.toString())
                }
                is ResultWrapper.NetworkError->{

                }
            }
        }
    }
}