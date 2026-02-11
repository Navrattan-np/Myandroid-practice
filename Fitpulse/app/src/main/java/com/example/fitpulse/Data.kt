package com.example.fitpulse

data class MealRequest(
    val size:Int,   //no. of days
    val plan:PlanConfig
)

data class PlanConfig(
    val accept:AcceptCriteria,
    val fit: Map<String,Range>,
    val sections:Map<String,SectionConfig>
)

data class SectionConfig(
    val accept:AcceptCriteria,
    val fit:Map<String,Range>
)

data class AcceptCriteria(
    val all:List<FilterGroup>   //health,meal,dish
)
data class FilterGroup(
    val health:List<String>,
    val dish:List<String>,
    val meal:List<String>
)

data class Range(
    val min:Int? =null,
    val max:Int? =null
)