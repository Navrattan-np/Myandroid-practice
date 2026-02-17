package com.example.mywishlistapp

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun HomeView(){
   val context= LocalContext.current
    Scaffold(
         topBar={ AppBarView(title="WishList",
                             onBackNavClicked={
                                Toast.makeText(context,"button clicked",Toast.LENGTH_LONG).show()}
                  )
              },
        floatingActionButton = { FloatingActionButton(
                                        onClick = { Toast.makeText(context,"Add button clicked",Toast.LENGTH_LONG).show() },
                                        modifier=Modifier.padding(all=20.dp),
                                        contentColor= Color.White,
                                        backgroundColor= Color.Black
                                  ) {
                                      Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }}
    ){
        LazyColumn(modifier= Modifier
            .fillMaxSize()
            .padding(it)){

        }
    }
}