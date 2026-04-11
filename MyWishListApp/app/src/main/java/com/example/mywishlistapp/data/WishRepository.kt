package com.example.mywishlistapp.data

import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao:WishDao) {

    suspend fun addAWish(wish:Wish){
        wishDao.addAWish(wish)
    }
    suspend fun updateAWish(wish:Wish){
        wishDao.updateAWish(wish)
    }
    suspend fun deleteAWish(wish:Wish){
        wishDao.deleteAWish(wish)
    }

    fun getWishes():Flow<List<Wish>> = wishDao.getAllWishes()
    fun getWishById(id:Long):Flow<Wish> = wishDao.getWishById(id)
}