package com.example.locationapp

import android.Manifest
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.example.locationapp.ui.theme.LocationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocationAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}



@Composable
fun  LocationDisplay(locationutils:LocationUtils,context:Context){

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = {permissions->
            if(permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ==true
                && permissions[Manifest.permission.ACCESS_FINE_LOCATION]==true)
                //i have access to location
            else{
                val rationaleRequired= ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity,
                    Manifest.permission.ACCESS_COARSE_LOCATION)   ||
                        ActivityCompat.shouldShowRequestPermissionRationale(
                            context as MainActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                

            }

        })

    Column(modifier=Modifier.fillMaxSize(),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center
    ){
         Text("Location not available")

        Button(onClick= {
                 if (locationutils.hasLocationPermission(context))
                    //if permission is granted then display
                 else
                     //permission not granted ask for permission
        }
        ){Text("Get location")}
    }
}