package com.example.navigationsample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
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
fun SecondScreen(navigatetofirstscreen:()->Unit){
    val name = remember{ mutableStateOf("") }

    Column(
        modifier= Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment= Alignment.CenterHorizontally,
        verticalArrangement= Arrangement.Center
    ){
        Text("This is Second screen", fontSize = 24.sp)
        Text("Welcome",fontSize=24.sp)
        Button(onClick = {
               navigatetofirstscreen()
        }) {
            Text("Go to first screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview(){
    SecondScreen({})
}