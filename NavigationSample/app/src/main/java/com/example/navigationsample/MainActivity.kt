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
              FirstScreen(navigatetosecondscreen = {namepassed,agepassed->
                           val safeName = if (namepassed.isBlank()) "Guest" else namepassed
                           val safeAge= agepassed.toIntOrNull()?:0
                           navControllerVar.navigate("secondscreen/$safeName/$safeAge")
                         }
              )
          }
          composable(route="secondscreen/{name}/{age}"){backStack->
                 val ageString=backStack.arguments?.getString("age")?:"0"
                 SecondScreen(
                     name=backStack.arguments?.getString("name")?:"User",
                     age=ageString.toIntOrNull()?:0,
                     navigatetofirstscreen={navControllerVar.navigate("firstscreen")},
                     navigatetothirdscreen = {navControllerVar.navigate("thirdscreen")}
                 )
          }
          composable(route="thirdscreen"){
              ThirdScreen{
                  navControllerVar.navigate("firstscreen")
              }
          }
      }
}