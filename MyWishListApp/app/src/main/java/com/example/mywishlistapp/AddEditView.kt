package com.example.mywishlistapp

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mywishlistapp.data.Wish

@Composable
fun AddEditDetailView(
    id:Long,
    viewModel:WishViewModel,
    navController: NavController
){
    val context=LocalContext.current
    val scaffoldState= rememberScaffoldState()

    if(id!=0L){
        val wish=viewModel.getAWishById(id).collectAsState(initial = Wish(0L,"",""))
        viewModel.wishTitleState=wish.value.title
        viewModel.wishDescriptionState=wish.value.description
    }else{
        viewModel.wishTitleState=""
        viewModel.wishDescriptionState=""
    }


    Scaffold(
        scaffoldState=scaffoldState,
        topBar={AppBarView(
            title=if(id!=0L)  stringResource(id= R.string.update_wish)
                  else stringResource(id=R.string.add_wish),
            onBackNavClicked = {navController.navigateUp()}
        )}
    ){paddingValues->
        Column(
            modifier= Modifier
                .padding(paddingValues)
                .wrapContentSize(),
            horizontalAlignment=Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Spacer(Modifier.height(10.dp))
            WishTextField(label="Title",
                          value=viewModel.wishTitleState,
                          onValueChanged={
                              viewModel.onWishTitlechanged(it)
                          }
            )

            Spacer(Modifier.height(10.dp))
            WishTextField(label="Description",
                          value=viewModel.wishDescriptionState,
                          onValueChanged={
                              viewModel.onWishDescriptionState(it)
                          }
            )

            Spacer(Modifier.height(10.dp))
            Button(
                onClick={
                    if(viewModel.wishTitleState.isNotEmpty() &&
                        viewModel.wishDescriptionState.isNotEmpty()){
                        if(id!=0L) {
                             viewModel.updateWish(
                                 Wish(id=id,
                                     title=viewModel.wishTitleState,
                                     description = viewModel.wishDescriptionState
                                 )
                             )
                            Toast.makeText(context,"Wish has been updated",Toast.LENGTH_SHORT).show()
                        }else{
                            viewModel.addWish(
                                Wish(title=viewModel.wishTitleState.trim(),
                                    description = viewModel.wishDescriptionState.trim()
                                )
                            )
                            viewModel.onWishTitlechanged("")
                            viewModel.onWishDescriptionState("")
                            Toast.makeText(context,"Wish has been created",Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Toast.makeText(context,"Error:Enter fields to create a wish",Toast.LENGTH_LONG).show()
                    }
                    navController.navigateUp()
                }
            ){
                Text(
                    text=if(id!=0L) stringResource(id=R.string.update_wish)
                         else stringResource(id=R.string.add_wish)
                )
            }

        }
    }
}

@Composable
fun WishTextField(
    label:String,
    value:String,
    onValueChanged: (String)->Unit
){
    OutlinedTextField(
        value=value,
        onValueChange = onValueChanged,    //{val-> onValueChanged(val)}
        label= {Text(label,color= Color.Black)},
        modifier=Modifier.fillMaxWidth(),
        keyboardOptions= KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor=Color.Black,
            focusedBorderColor= colorResource(id = R.color.black),
            unfocusedBorderColor = Color.DarkGray,
            cursorColor = colorResource(id= R.color.black),
            focusedLabelColor=colorResource(id=R.color.black),
            unfocusedLabelColor = colorResource(id=R.color.black)
        )
    )
}

