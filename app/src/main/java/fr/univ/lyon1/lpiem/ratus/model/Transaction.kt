package fr.univ.lyon1.lpiem.ratus.model

import com.google.firebase.Timestamp

data class Transaction(
    val title : String = "",
    val amount : Double = 0.0,
    val category : String = "",
    val date : Timestamp? = null,
    val recurrence : Recurrence = Recurrence()
)
