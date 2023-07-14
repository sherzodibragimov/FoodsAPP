package com.example.foodsapp.core.db

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

object ThemeCache {
    private lateinit var sharedPreferences: SharedPreferences
    private var lastIndex=0;
    fun init(context: Context){
        sharedPreferences=context.getSharedPreferences("theme",Context.MODE_PRIVATE)
    }

    fun changeDay(){
        sharedPreferences.edit().apply(){
            putInt("my_theme",0)
            apply()
        }
    }


    fun changeNight(){
        sharedPreferences.edit().apply(){
            putInt("my_theme",1)
            apply()
        }
    }
    fun getIndex():Int{
        val foodsIndex= ThemeCache.sharedPreferences.getInt("my_theme",0)
        return foodsIndex
    }
}