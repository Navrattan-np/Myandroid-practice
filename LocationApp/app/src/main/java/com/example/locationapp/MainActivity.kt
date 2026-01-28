package com.example.locationapp

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.locationapp.ui.theme.LocationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewmodel:LocationViewModel=viewModel()
            LocationAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                     MyApp(viewmodel)
                }
            }
        }
    }
}


@Composable
fun MyApp(viemodel:LocationViewModel){
    val context= LocalContext.current
    val localutils=LocationUtils(context)
    LocationDisplay(localutils,viemodel,context)
}


@Composable
fun  LocationDisplay(locationutils:LocationUtils,viemodel:LocationViewModel,context:Context){

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
                
                if(rationaleRequired){
                    Toast.makeText(context,
                           "Location Permission is required for this feature to work",
                        Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(context,
                           "Location permission is required. Please enable it in the android setting",
                        Toast.LENGTH_LONG).show()
                }
            }

        })

    Column(modifier=Modifier.fillMaxSize(),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center
    ){
         Text("Location not available")

        Button(onClick= {
                 if (locationutils.hasLocationPermission(context))
                    //if permission has granted then display
                 else{
                     requestPermissionLauncher.launch(
                         arrayOf(
                             Manifest.permission.ACCESS_FINE_LOCATION,
                             Manifest.permission.ACCESS_COARSE_LOCATION
                         )
                     )
                 }
        }
        ){Text("Get location")}
    }
}