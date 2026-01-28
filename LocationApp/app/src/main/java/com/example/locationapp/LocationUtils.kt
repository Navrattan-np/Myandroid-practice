package com.example.locationapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

class LocationUtils(val context:Context) {

    private val _fusedLocationClient:FusedLocationProviderClient=
                              LocationServices.getFusedLocationProviderClient(context)

    SupressLint("Missing Permission")
    fun requestLocationUpdates(viewmodel: LocationViewModel){
        val locationcallback=object:LocationCallback(){
               override fun onLocationResult(locationresult: LocationResult) {
                    super.onLocationResult(locationresult)
                    locationresult.lastLocation?.let{
                         val location=LocationData(it.latitude,it.longitude)
                         viewmodel.updateLocation(location)
                    }
               }
           }

        val locationrequest=LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY,1000).build()

        _fusedLocationClient.requestLocationUpdates(locationrequest,locationcallback, Looper.getMainLooper())
    }

   fun hasLocationPermission(context:Context):Boolean{

      return ContextCompat.checkSelfPermission(
           context, Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED
              &&
            ContextCompat.checkSelfPermission(
                context,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED
    }
}