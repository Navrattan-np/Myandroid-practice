package com.example.currencyconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.currencyconverter.ui.theme.CurrencyConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Converter()
                }
            }
        }
    }
}

@Composable
fun Converter(){
    Column(
        modifier=Modifier.fillMaxSize(),
        horizontalAlignment=Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        var amount by remember {mutableStateOf("")}
        var selectedCurrency by remember {mutableStateOf("Select currency")}
        var expanded by remember{mutableStateOf(false)}
        var display by remember{mutableStateOf(false)}
        var result by remember{mutableStateOf("")}
        val context= LocalContext.current

        OutlinedTextField(value = amount,
            onValueChange = {amount = it},
            label={Text("Enter amount in Rs:")}
        )

        Box{
            Button(onClick={expanded=true}){
                Text(selectedCurrency)
                Icon(Icons.Default.ArrowDropDown,contentDescription=null)
            }

            DropdownMenu(expanded = expanded, onDismissRequest = { expanded=false }) {
                DropdownMenuItem(text={Text("US dollar")},
                                onClick = { selectedCurrency="USD"
                                            expanded=false }
                )
                DropdownMenuItem(text = { Text("Euro")},
                    onClick = { selectedCurrency="Euro"
                                expanded=false}
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))
        if(display) {
            Text("Result: $result",style=MaterialTheme.typography.headlineSmall)
        }

        Button(onClick={
            Toast.makeText(context,"Coverting to $selectedCurrency ...",Toast.LENGTH_LONG).show()
            val conversionFactor=when(selectedCurrency){
                "USD"-> 0.011
                "Euro"->0.0093
                else->1.0
            }
            val amt=amount.toDoubleOrNull() ?:0.0
            result=(amt * conversionFactor).toString()
            display=true
        }){Text("Calculate")}
    }
}

@Preview(showBackground = true)
@Composable
fun ConverterPreview() {
    Converter()
}