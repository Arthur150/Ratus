package fr.univ.lyon1.lpiem.ratus.model

data class Budget(
    val balance : Double = 0.0,
    val transactions : List<Transaction> = arrayListOf(),
)
