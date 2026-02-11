package com.example.fitpulse

data class MealResponse (
    val selection:List<Selection>
)

data class Selection(
    val day:Int,
    val sections:Map<String,MealSelection>
)

data class MealSelection(
    val assigned:String     //meal id picked for plan
)