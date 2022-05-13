package fr.univ.lyon1.lpiem.ratus.model

import java.io.Serializable

data class Fund(
    val id: String = "",
    val contributors: List<User> = arrayListOf(),
    val goal: Double = 0.0,
    val amount: Double = 0.0,
    val title: String = ""
) : Serializable
