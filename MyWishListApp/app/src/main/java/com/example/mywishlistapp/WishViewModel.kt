package com.example.mywishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywishlistapp.data.Wish
import com.example.mywishlistapp.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel (
    private val wishRepository: WishRepository = Graph.wishRepository
): ViewModel() {

    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")

    fun onWishTitlechanged(newString:String){
        wishTitleState=newString
    }
    fun onWishDescriptionState(newString:String){
        wishDescriptionState=newString
    }

    lateinit var getWishes: Flow<List<Wish>>
    init{
        getWishes=wishRepository.getWishes()
    }

    fun getAWishById(id:Long):Flow<Wish>{
        return wishRepository.getWishById(id)
    }

    fun addWish(wish:Wish){
        viewModelScope.launch(Dispatchers.IO){
            wishRepository.addAWish(wish)
        }
    }
    fun updateWish(wish:Wish){
        viewModelScope.launch(Dispatchers.IO){
            wishRepository.updateAWish(wish)
        }
    }
    fun deleteWish(wish:Wish){
        viewModelScope.launch(Dispatchers.IO){
            wishRepository.deleteAWish(wish)
        }
    }

}