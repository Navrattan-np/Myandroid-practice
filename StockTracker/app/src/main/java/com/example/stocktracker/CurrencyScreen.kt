package com.example.stocktracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun CurrencyScreen(modifier: Modifier =Modifier){
      val vm:MainViewModel =viewModel()
      val viewState by vm.currencyListState

     Box(modifier=Modifier.fillMaxSize()) {
          when{
              viewState.loading ->{
                  CircularProgressIndicator(Modifier.align(Alignment.Center))
              }
              viewState.error!=null ->{
                  Text("Error Occured: ${viewState.error}")
              }
              else ->{
                    displayCurrency(viewState.list)
              }
          }
     }
}

@Composable
fun displayCurrency(list:List<CryptoCurrency>){
     LazyVerticalGrid(GridCells.Fixed(2),modifier=Modifier.fillMaxSize()){
         items(list){
               displayItem(it)
         }
     }
}

@Composable
fun displayItem(it :CryptoCurrency){
       Column(
           modifier= Modifier
               .fillMaxSize()
               .padding(8.dp),
           horizontalAlignment=Alignment.CenterHorizontally
       ){
             Image(painter = rememberAsyncImagePainter(model= it.image),
                   contentDescription = null,
                   modifier=Modifier.fillMaxSize().aspectRatio(1f)
             )

             Text("${it.name} ${it.symbol}")

             val colorChange= if(it.price_change_24h <0) Color.Red else Color.Green
             Text("Price= $${"%.2f".format(it.current_price)} ")
             Text("Change :${"%.2f".format(it.price_change_24h)}",color= colorChange)
       }
}