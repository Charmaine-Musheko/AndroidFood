package com.example.foodtrackerandroid.interfaces

import com.example.foodtrackerandroid.entities.Category
import com.example.foodtrackerandroid.entities.MealResponse
import com.example.foodtrackerandroid.entities.Meal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataService {
    @GET("categories.php")
    fun getCategoryList(): Call<Category>

    @GET("filter.php")
    fun getMealList(@Query("c") category: String): Call<Meal>

    @GET("lookup.php")
    fun getSpecificItem(@Query("i") id: String): Call<MealResponse>

}