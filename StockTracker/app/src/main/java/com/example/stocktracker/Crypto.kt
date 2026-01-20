package com.example.stocktracker

data class CryptoCurrency(
          val symbol:String,
          val name:String,
          val image:String,
          val current_price:Double,
          val price_change_24h:Double
)
