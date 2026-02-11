package com.example.fitpulse

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun mainScreen(viewmodel:MainViewModel){
     val viewState by viewmodel.mealPlanState
    val daysCount = remember{mutableStateOf("7")}

    Column(
        modifier= Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment=Alignment.CenterHorizontally
    ){
        TextField(
            value = daysCount.value,
            onValueChange = { daysCount.value =it },
            label= {Text("How many days?")},
            modifier=Modifier.fillMaxWidth()
        )

        Button(
            onClick={
                    val request=
            },
            modifier=Modifier.padding(vertical = 16.dp)
        ){
            Text("Generate plan")
        }
    }
}