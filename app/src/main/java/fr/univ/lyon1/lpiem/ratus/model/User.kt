package fr.univ.lyon1.lpiem.ratus.model

import java.util.ArrayList

data class User(
    val uid : String = "",
    val balance : Double = 0.0,
    val transactions : List<Transaction> = arrayListOf(),
) {
    fun toHashMap(): HashMap<String, Any> {
        return hashMapOf(
            "uid" to uid,
            "balance" to balance,
            "transactions" to transactions
        )
    }

    fun addTransaction(transaction: Transaction): User {
        val transactions = ArrayList<Transaction>(transactions)
        transactions.add(transaction)
        return User(
            uid,
            balance,
            transactions
        )
    }
}
