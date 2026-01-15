package com.example.countermvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.countermvvm.ui.theme.CounterMVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm:CounterViewModel = viewModel()
            CounterMVVMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CounterApp(vm)
                }
            }
        }
    }
}

@Composable
fun CounterApp(vm: CounterViewModel){
    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement= Arrangement.Center,
        horizontalAlignment= Alignment.CenterHorizontally
    ){
        Text(
            text="Count: ${vm.count.value}",
            fontSize=24.sp,
            fontWeight= FontWeight.Bold
        )
        
        Spacer(modifier=Modifier.height(16.dp))

        Row{
            Button(onClick={vm.increment()}){
                Text("Increase")
            }
            Spacer(modifier=Modifier.width(16.dp))
            Button(onClick={vm.decrement()}){
                Text("Decrease")
            }
        }
    }
}