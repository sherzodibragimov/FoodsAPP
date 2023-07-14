package com.example.foodsapp.core.db

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

object SaveFoods {
    private lateinit var sharedPreferences: SharedPreferences
    private const val KEY_INDEX="key_index"
    private var lastIndex=0;
    fun init(context: Context){
        sharedPreferences=context.getSharedPreferences("my_food",Context.MODE_PRIVATE)
    }
    fun saveResult (index:Int){
        lastIndex= getLastCache()
        sharedPreferences.edit().apply(){
            putInt(KEY_INDEX + lastIndex,index)
            apply()
        }
        lastIndex++
        saveCache()
    }

    private fun saveCache(){
        sharedPreferences.edit().putInt("last_index", lastIndex).apply()
    }

    private fun getLastCache():Int{
        return sharedPreferences.getInt("last_index",0)
    }
    fun getSaveResult():ArrayList<Int>{
        var array=ArrayList<Int>()
        for (i in 0 until getLastCache()){
            array.add(getIndex(i))
        }
        return array
    }

    private fun getIndex(i:Int):Int{
        val foodsIndex= sharedPreferences.getInt(KEY_INDEX+i,0)
        return foodsIndex
    }
    fun checkIndex(j:Int):Boolean{
        for (i in 0 until getSaveResult().size){
            if (j == getSaveResult()[i]){
                return true
            }
        }
        return false
    }

    fun deleteID(IdIndex:Int){
        var index=0
        for (i in 0 until getLastCache() ){

            if (IdIndex== sharedPreferences.getInt("$KEY_INDEX$i",0)){
                index=i
            }
            sharedPreferences.edit().remove("$KEY_INDEX$index").apply()
        }
        lastIndex= getLastCache()
        --lastIndex
        saveCache()
    }
}