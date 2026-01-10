package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){
    var inputValue by remember{mutableStateOf("")}
    var outputValue by remember{mutableStateOf("")}
    var inputField by remember{mutableStateOf("m")}
    var outputField by remember{mutableStateOf("m")}
    var iExpand by remember{mutableStateOf(false)}
    var oExpand by remember{mutableStateOf(false)}
    var iconversionFactor by remember{mutableStateOf(1.0)}
    var oconversionFactor by remember{mutableStateOf(1.0)}

    fun conversionCalculate(){
          val input=inputValue.toDoubleOrNull() ?:0.0
          val result=(input*iconversionFactor/oconversionFactor*100).roundToInt() /100.0
          outputValue=result.toString()
    }


    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement= Arrangement.Top,
        horizontalAlignment= Alignment.CenterHorizontally

    ) {
        Spacer(modifier=Modifier.height(125.dp))

       Text("Unit converter",style=MaterialTheme.typography.headlineLarge)
        Spacer(modifier=Modifier.height(16.dp))

       OutlinedTextField(
           value = inputValue,
           onValueChange = {
                            inputValue=it
                            conversionCalculate()
                           },
           label={Text("Enter value:")},
           keyboardOptions=KeyboardOptions(keyboardType= KeyboardType.Decimal)
       )
        Spacer(modifier=Modifier.height(16.dp))

       Row {
           //input box
          Box {
              Button(onClick = { iExpand=true }) {
                  Text(inputField)
                  Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow down")
              }
              DropdownMenu(expanded=iExpand,onDismissRequest={iExpand=false}){
                  DropdownMenuItem(
                      text={Text("cm")},
                      onClick={
                          iExpand=false
                          inputField="cm"
                          iconversionFactor=.01
                          conversionCalculate()
                      }
                  )
                  DropdownMenuItem(
                      text={Text("m")},
                      onClick={
                          iExpand=false
                          inputField="m"
                          iconversionFactor=1.0
                          conversionCalculate()
                      }
                  )
                  DropdownMenuItem(
                      text={Text("feet")},
                      onClick={
                          iExpand=false
                          inputField="feet"
                          iconversionFactor=0.3048
                          conversionCalculate()
                      }
                  )
                  DropdownMenuItem(
                      text={Text("millimeter")},
                      onClick={
                          iExpand=false
                          inputField="millimeter"
                          iconversionFactor=0.001
                          conversionCalculate()
                      }
                  )
              }
          }
           Spacer(modifier=Modifier.width(3.dp))
           //output box
          Box{
              Button(onClick={ oExpand=true }){
                  Text(outputField)
                  Icon(Icons.Default.ArrowDropDown,contentDescription="Arrow down 2")
              }
              DropdownMenu(expanded=oExpand,onDismissRequest={oExpand=false}){
                  DropdownMenuItem(
                      text={Text("cm")},
                      onClick={
                          oExpand=false
                          outputField="cm"
                          oconversionFactor=.01
                          conversionCalculate()
                      }
                  )
                  DropdownMenuItem(
                      text={Text("m")},
                      onClick={
                          oExpand=false
                          outputField="m"
                          oconversionFactor=1.0
                          conversionCalculate()
                      }
                  )
                  DropdownMenuItem(
                      text={Text("feet")},
                      onClick={
                          oExpand=false
                          outputField="feet"
                          oconversionFactor=.3048
                          conversionCalculate()
                      }
                  )
                  DropdownMenuItem(
                      text={Text("millimeter")},
                      onClick={
                          oExpand=false
                          outputField="millimeter"
                          oconversionFactor=.001
                          conversionCalculate()
                      }
                  )
              }
          }
       }
        Spacer(modifier=Modifier.height(12.dp))
       Text("Result: $outputValue $outputField",style= MaterialTheme.typography.headlineMedium,fontFamily= FontFamily.Monospace)
    }
}


@Preview(showBackground=true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}