package com.example.foodsapp.core.app

import android.app.Application
import com.example.foodsapp.core.db.SaveFoods
import com.example.foodsapp.core.db.ThemeCache

class App : Application(){

    companion object{
        lateinit var instance:Application
    }

    override fun onCreate() {
        super.onCreate()
        instance= this
        SaveFoods.init(this)
        ThemeCache.init(this)
    }
}