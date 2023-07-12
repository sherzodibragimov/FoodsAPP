package com.example.foodsapp.core.network

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.example.foodsapp.core.app.App
import com.example.foodsapp.core.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object AppClient {
    fun getRetrofit()= Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client())
        .build()



    private fun client()= OkHttpClient.Builder()
        .addInterceptor(checkerInterceptor())
        .connectTimeout(1,TimeUnit.MINUTES)
        .writeTimeout(1,TimeUnit.MINUTES)
        .readTimeout(1,TimeUnit.MINUTES)
        .build()



    private fun chuckerCollector() = ChuckerCollector(
        context = App.instance!!,
        showNotification = true,
        retentionPeriod = RetentionManager.Period.ONE_HOUR
    )

    fun checkerInterceptor() = ChuckerInterceptor.Builder(App.instance!!)
        .collector(chuckerCollector())
        .maxContentLength(250_000L)
        .redactHeaders("Auth-Token", "Bearer")
        .alwaysReadResponseBody(true)
        .build()

    fun getMealService()= getRetrofit().create(MealService::class.java)
}