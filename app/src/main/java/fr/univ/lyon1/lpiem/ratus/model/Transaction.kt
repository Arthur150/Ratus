package fr.univ.lyon1.lpiem.ratus.model

import com.google.type.DateTime

data class Transaction(
    val title : String,
    val amount : Double,
    val category : String,
    val date : DateTime,
    val recurrence : Recurrence
)
