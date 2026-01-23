package com.example.navigationsample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ThirdScreen(navigatetofirstscreen:()->Unit){
      Column(
          modifier= Modifier.fillMaxSize().padding(16.dp),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment= Alignment.CenterHorizontally
      ){
          Text("This is the Third Screen. Go to First Screen")
          Button(onClick={navigatetofirstscreen()}){
              Text("Go to first screen")
          }
      }
}

@Preview(showBackground=true)
@Composable
fun ThirdScreenPreview(){
    ThirdScreen({})
}