package fr.univ.lyon1.lpiem.ratus.model

import java.util.ArrayList

data class User(
    val uid : String = "",
    val budget : Budget = Budget()
) {
    fun toHashMap(): HashMap<String, Any> {
        return hashMapOf(
            "uid" to uid,
            "budget" to budget.toHashMap()
        )
    }

    fun addTransaction(transaction: Transaction): User {
        val transactions = ArrayList<Transaction>(budget.transactions)
        transactions.add(transaction)
        return User(
            uid,
            Budget(
                budget.balance,
                transactions
            )
        )
    }
}
