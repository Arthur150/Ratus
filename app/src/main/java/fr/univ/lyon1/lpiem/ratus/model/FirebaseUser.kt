package fr.univ.lyon1.lpiem.ratus.model

import com.google.firebase.firestore.DocumentReference

data class FirebaseUser(
    val uid: String = "",
    val thumbnail: String = "",
    val username: String = "",
    val balance: Double = 0.0,
    val transactions: List<DocumentReference> = arrayListOf()
) {
    fun toUser(transactionsList: List<Transaction>): User {
        return User(
            uid = uid,
            balance = balance,
            transactions = transactionsList,
            thumbnail = thumbnail,
            username = username
        )
    }
}
