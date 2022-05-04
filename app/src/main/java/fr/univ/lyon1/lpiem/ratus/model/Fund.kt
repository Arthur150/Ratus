package fr.univ.lyon1.lpiem.ratus.model

data class Fund(
    val id : String,
    val contributors : List<String>,
    val goal : Double,
    val amount : Double,
    val title : String
)
