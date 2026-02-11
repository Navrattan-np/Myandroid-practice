package com.example.fitpulse

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _mealPlanState:MutableState<ResponseState> =mutableStateOf(ResponseState())
    val mealPlanState:State<ResponseState> = _mealPlanState

    fun fetchDetail(request:MealRequest){
         viewModelScope.launch {
             try{
                 val response= mealService.generatePlan(request=request)
                 _mealPlanState.value=_mealPlanState.value.copy(
                                                         mealPlan=response,
                                                          error=null,
                                                          loading=false
                                                     )
             }catch(e:Exception){
                 _mealPlanState.value=_mealPlanState.value.copy(
                     error="Error: ${e.message}",
                     loading=false
                 )
             }
         }
    }

    data class ResponseState(
        val mealPlan:MealResponse?=null,
        val error:String?=null,
        val loading:Boolean=true
    )
}