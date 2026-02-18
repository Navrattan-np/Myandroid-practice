package com.example.mywishlistapp

sealed class Screen(val route:String) {
    object HomeScreen:Screen("homescreen")
    object AddScreen:Screen("addscreen")
}