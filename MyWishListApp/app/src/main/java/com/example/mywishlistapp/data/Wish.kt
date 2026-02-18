package com.example.mywishlistapp.data

data class Wish (
    val id:Long=0L,
    val title:String="",
    val description:String=""
)

object DummyWish {
    val wishList = listOf(
        Wish(title = "Google watch 2", description = "An android watch"),
        Wish(title = "A sci-fi Book", description = "A science friction book from any seller"),
        Wish(title = "Oculus Quest 2", description = "A vr headset for playing games")
    )
}