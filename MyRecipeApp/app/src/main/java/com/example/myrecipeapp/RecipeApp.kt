package com.example.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navControllerVar:NavHostController){
        val recipeViemodel:MainViewModel = viewModel()
        val viewstate by recipeViemodel.categoriesState

    NavHost(navController = navControllerVar, startDestination = Screen.RecipeScreen.route ){
           composable(Screen.RecipeScreen.route){
                  RecipeScreen(viewState =viewstate,
                               navigatetocategorydetail = {
                                   navControllerVar.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                                   navControllerVar.navigate(Screen.DetailScreen.route)
                               }
                  )
           }
           composable(Screen.DetailScreen.route){
               val cat=navControllerVar.previousBackStackEntry?.savedStateHandle?.get<Category>("cat")?: Category("","","","")
               CategoryDetailScreen(category = cat)
           }
    }
}