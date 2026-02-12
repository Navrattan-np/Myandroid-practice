package com.example.fitpulse

// This is the "Result" format provided by the Edamam Documentation
data class MealResponse (
    val selection:List<Selection>
)

data class Selection(
    val day:Int,
    val sections:Map<String,MealSelection> // This map will have keys like "Breakfast", "Lunch"
)

data class MealSelection(
    // This is the ID (URI) of the meal the API picked
    val assigned:String     //meal id picked for plan

)