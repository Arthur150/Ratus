package fr.univ.lyon1.lpiem.ratus.model

import com.google.firebase.firestore.DocumentReference

data class FirebaseFund(
    val id: String = "",
    val contributors: List<DocumentReference> = arrayListOf(),
    val goal: Double = 0.0,
    val amount: Double = 0.0,
    val title: String = ""
) {
    fun toFund(contributorList: List<User>): Fund {
        return Fund(
            id = this.id,
            contributors = contributorList,
            goal = this.goal,
            amount = this.amount,
            title = this.title
        )
    }

    fun toHashMap(): HashMap<String, Any?> {
        return hashMapOf(
            "contributors" to contributors,
            "goal" to goal,
            "amount" to amount,
            "title" to title
        )
    }
}
