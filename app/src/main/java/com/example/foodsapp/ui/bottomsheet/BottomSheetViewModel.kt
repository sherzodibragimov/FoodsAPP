package com.example.foodsapp.ui.bottomsheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodsapp.core.model.categoriy.MealCategory
import com.example.foodsapp.core.model.foods.FoodsByIdResponse
import com.example.foodsapp.core.repostoriy.MealRepository
import com.example.foodsapp.core.util.ResultWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BottomSheetViewModel:ViewModel() {
    private var repo=MealRepository()
    /** Success Data */
    private val dataLiveSuccess:MutableLiveData<MealCategory?> = MutableLiveData<MealCategory?>()
    val successResponse:LiveData<MealCategory?>
        get()=dataLiveSuccess
    /** Error Data*/
    private val dataLiveError:MutableLiveData<String?> = MutableLiveData<String?>()
    val errorDataLive:LiveData<String?>
        get()=dataLiveError

    fun getCategory(){
        CoroutineScope(Dispatchers.IO).launch {
            val result= repo.getCategory()
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