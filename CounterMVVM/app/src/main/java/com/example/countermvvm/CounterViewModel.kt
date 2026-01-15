package com.example.countermvvm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _count=mutableStateOf(0)
    val count: State<Int> = _count


    fun increment(){
        _count.value++
    }
    fun decrement(){
        _count.value--
    }
}