package com.example.navigationsample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SecondScreen(name:String,age:Int,navigatetofirstscreen:()->Unit,navigatetothirdscreen:()->Unit){

    Column(
        modifier= Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment= Alignment.CenterHorizontally,
        verticalArrangement= Arrangement.Center
    ){
        Text("This is Second screen", fontSize = 24.sp)
        Text("Welcome $name",fontSize=24.sp)
        Text("Your age: $age",fontSize=24.sp)
        Spacer(modifier=Modifier.height(8.dp))
        Button(onClick = {
               navigatetofirstscreen()
        }) {
            Text("Go to first screen")
        }
        Button(onClick={
            navigatetothirdscreen()
        }){
            Text("Go to third screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview(){
    SecondScreen("denis",0,{},{})
}