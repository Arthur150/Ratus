package fr.univ.lyon1.lpiem.ratus.model

data class User(
    val uid: String = "",
    val budget: Budget = Budget()
) {
    fun toHashMap(): HashMap<String, Any> {
        return hashMapOf(
            "uid" to uid,
            "budget" to budget.toHashMap()
        )
    }
}
