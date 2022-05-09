package fr.univ.lyon1.lpiem.ratus.model

data class Fund(
    val id: String = "",
    val contributors: List<String> = arrayListOf(),
    val goal: Double = 0.0,
    val amount: Double = 0.0,
    val title: String = ""
)
