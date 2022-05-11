package fr.univ.lyon1.lpiem.ratus.model

import com.google.firebase.firestore.DocumentReference

data class FirebaseFund(
    val id: String = "",
    val contributorsRef: List<DocumentReference> = arrayListOf(),
    val goal: Double = 0.0,
    val amount: Double = 0.0,
    val title: String = ""
) {
    fun toFund(contributors: ArrayList<User>): Fund {
        return Fund(
            id = this.id,
            contributors = contributors,
            goal = goal,
            amount = amount,
            title = title
        )
    }
}
