package fr.univ.lyon1.lpiem.ratus.model

data class Trick(
    val id: String = "",
    val description: String = "",
    val image: String = "",
    val title: String = "",
    val content_url: String = "",
    var color : Int = 0
)
