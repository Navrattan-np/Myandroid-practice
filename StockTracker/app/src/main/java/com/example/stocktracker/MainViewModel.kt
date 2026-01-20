package com.example.stocktracker

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _currencyListState: MutableState<CurrencyState> =mutableStateOf(CurrencyState())

    val currencyListState: State<CurrencyState> = _currencyListState

    init{
        fetchDetail()
    }

    private fun fetchDetail(){
        viewModelScope.launch(){
              try{
                  val response=CoinService.getDetail()
                  _currencyListState.value=_currencyListState.value.copy(
                                                              loading=false,
                                                              error=null,
                                                              list=response
                                                            )
              }catch(e:Exception){
                  _currencyListState.value=_currencyListState.value.copy(
                                                                loading=false,
                                                                error=e.message
                                                               )

              }
        }
    }

     data class CurrencyState(
         val loading:Boolean=true,
         val error:String?=null,
         val list:List<CryptoCurrency> =emptyList()
     )
}