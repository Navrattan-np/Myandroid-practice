package com.example.stocktracker

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit= Retrofit.Builder()
                   .baseUrl("https://api.coingecko.com/api/v3/")
                   .addConverterFactory(GsonConverterFactory.create()).build()

val   CoinService=retrofit.create(ApiService::class.java)

interface ApiService {
    @GET("coins/markets")
    suspend fun getDetail(
        @Query("vs_currency") vs_currency:String="usd",
        @Query("order") order:String="market_cap_desc",
        @Query("per_page") per_page:Int=10,
        @Query("page") page:Int=1,
        @Query("sparkline") sparkline:Boolean=false
    ):List<CryptoCurrency>
}