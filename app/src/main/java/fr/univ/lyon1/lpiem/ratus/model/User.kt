package fr.univ.lyon1.lpiem.ratus.model

data class User(
    val uid: String = "",
    val thumbnail: String = "",
    val username: String = "",
    val balance: Double = 0.0,
    val transactions: List<Transaction> = arrayListOf(),
) {
    fun toHashMap(): HashMap<String, Any> {
        return hashMapOf(
            "uid" to uid,
            "balance" to balance,
            "transactions" to transactions
        )
    }
}
