package com.example.navigationsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationsample.ui.theme.NavigationSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                       MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(){
      val navControllerVar = rememberNavController()

      NavHost(navController = navControllerVar , startDestination = "firstscreen"){
          composable(route="firstscreen"){
              FirstScreen{
                    navControllerVar.navigate("secondscreen")
              }
          }
          composable(route="secondscreen"){
                 SecondScreen{
                     navControllerVar.navigate("firstScreen")
                 }
          }
      }
}