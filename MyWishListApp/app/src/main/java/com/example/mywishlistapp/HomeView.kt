package com.example.mywishlistapp

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mywishlistapp.data.Wish

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(viewModel:WishViewModel ,navController: NavController){
    val context=LocalContext.current
    Scaffold(
        topBar={AppBarView(title="WishList",
            onBackNavClicked={ Toast.makeText(context,"Button Clicked",Toast.LENGTH_LONG).show() }
        )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick={Toast.makeText(context,"FAB clicked",Toast.LENGTH_LONG).show()
                    navController.navigate(Screen.AddScreen.route +"/0")
                },
                contentColor= Color.White,
                modifier=Modifier.padding(all=20.dp),
                containerColor=Color.Black
            ){
                Icon(imageVector = Icons.Default.Add, contentDescription =null )
            }
        }
    ) {paddingValues->

        val wishList= viewModel.getWishes.collectAsState(initial = listOf())

        LazyColumn(modifier= Modifier
            .fillMaxSize()
            .padding(paddingValues)){
          items(wishList.value){wish->
              WishItem(wish = wish,
                  onClick={val id=wish.id
                      navController.navigate(Screen.AddScreen.route+"/$id")
                  }
              )
          }
        }
    }
}

@Composable
fun WishItem(wish: Wish, onClick: ()->Unit={}){
    Card(
        modifier= Modifier
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = 10.dp,
        backgroundColor=Color.White
    ) {
        Column(modifier=Modifier.padding(16.dp)){
            Text(wish.title,fontWeight= FontWeight.ExtraBold)
            Text(wish.description)
        }
    }
}


