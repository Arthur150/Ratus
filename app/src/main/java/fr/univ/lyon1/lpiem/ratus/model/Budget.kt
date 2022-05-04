package fr.univ.lyon1.lpiem.ratus.model

data class Budget(
    val balance : Double,
    val transactions : List<Transaction>,
)
