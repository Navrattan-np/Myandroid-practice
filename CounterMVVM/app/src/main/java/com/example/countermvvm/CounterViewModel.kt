package com.example.countermvvm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _repository:CounterRepository=CounterRepository()
    private val _count=mutableStateOf(_repository.getcounter().count)
    val count: State<Int> = _count


    fun increment(){
        _repository.getcounter().count++
        _count.value=_repository.getcounter().count
    }
    fun decrement(){
        _repository.getcounter().count--
        _count.value=_repository.getcounter().count
    }
}