package com.example.fitpulse

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


private val retrofit:Retrofit= Retrofit.Builder()
    .baseUrl("https://api.edamam.com/")
    .addConverterFactory(GsonConverterFactory.create()).build()

val mealService:ApiService=retrofit.create(ApiService::class.java)
interface ApiService{
    @POST("api/meal-planner/v1/{app_id}/select")
    suspend fun generatePlan(
       @Path("app_id") app_id:String="ff476745",
       @Query("app_key") appKey:String="d78a28739e80f5288b9a757c07104595",
       @Body request: MealRequest
    ):MealResponse

}