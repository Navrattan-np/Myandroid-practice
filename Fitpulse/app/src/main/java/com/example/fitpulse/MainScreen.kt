package com.example.fitpulse

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun mainScreen(viewmodel:MainViewModel){
     val viewState by viewmodel.mealPlanState
    val daysCount = remember{mutableStateOf("7")}

    Column(
        modifier= Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment=Alignment.CenterHorizontally
    ){
        TextField(
            value = daysCount.value,
            onValueChange = { daysCount.value =it },
            label= {Text("How many days?")},
            modifier=Modifier.fillMaxWidth()
        )

        Button(
            onClick={
                    val request=generateRequest(daysCount.value.toIntOrNull() ?:7)
                    viewmodel.fetchDetail(request=request)
            },
            modifier=Modifier.padding(vertical = 16.dp)
        ){
            Text("Generate plan")
        }

        when{
            viewState.loading-> CircularProgressIndicator()
            viewState.error!=null ->{
                Text(text=viewState.error ?:"Ünknown error",color= MaterialTheme.colorScheme.error)
            }
            else ->{
                LazyColumn{
                    items(viewState.mealPlan!!.selection){
                           DisplayDayItem(it)
                    }
                }
            }
        }
    }
}
@Composable
fun DisplayDayItem(selection:Selection){
    Card (modifier=Modifier.fillMaxWidth().padding(vertical=8.dp)){
          Column(modifier=Modifier.padding(16.dp)){
              Text(text="Day ${selection.day}",style=MaterialTheme.typography.titleLarge)

              selection.sections.forEach{(name,mealSection)->
                   Text(text="$name :${mealSection.assigned}")
              }
          }
    }
}

fun generateRequest(days:Int):MealRequest{
    return MealRequest(
               size=days,
               plan=PlanConfig(
                   accept= AcceptCriteria(all= emptyList()),
                   fit= emptyMap(),
                   sections=mapOf(
                       "Breakfast" to SectionConfig(
                           accept= AcceptCriteria(all=listOf(FilterGroup(meal=listOf("breakfast"),health = listOf("alcohol-free"),dish=emptyList()))),
                           fit= emptyMap()
                       ),
                       "Lunch" to SectionConfig(
                           accept= AcceptCriteria(all=listOf(FilterGroup(meal=listOf("lunch"),health = listOf("alcohol-free"),dish=emptyList()))),
                           fit= emptyMap()
                       )
                   )
               )
    )
}