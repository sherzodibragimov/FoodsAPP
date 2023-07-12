package com.example.foodsapp.core.db

import android.content.Context
import android.content.SharedPreferences

object SaveFoods {
    private lateinit var sharedPreferences: SharedPreferences
    const val KEY_INDEX="key_index"
    private var lastIndex=0;
    fun init(context: Context){
        sharedPreferences=context.getSharedPreferences("my_food",Context.MODE_PRIVATE)
    }
    fun saveResult (index:Int){
        lastIndex= getSaveCache()
        sharedPreferences.edit().apply(){
            putInt(KEY_INDEX + lastIndex,index)
            apply()
        }
    }

    private fun saveCache(){
        sharedPreferences.edit().putInt("last_index", lastIndex).apply()
    }

    private fun getSaveCache():Int{
        return sharedPreferences.getInt("last_index",0)
    }
    fun getSaveResult():ArrayList<Int>{
        var array=ArrayList<Int>()
        for (i in 0 until getSaveCache()){
            array.add(getIndex(i))
        }
        return array
    }

    private fun getIndex(i:Int):Int{
        val foodsIndex= sharedPreferences.getInt(KEY_INDEX+i,0)
        return foodsIndex
    }
}