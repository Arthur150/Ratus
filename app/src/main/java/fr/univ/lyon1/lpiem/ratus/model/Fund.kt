package fr.univ.lyon1.lpiem.ratus.model

import com.google.firebase.firestore.DocumentReference

data class Fund(
    val id: String = "",
    val contributors: List<User> = arrayListOf(),
    val goal: Double = 0.0,
    val amount: Double = 0.0,
    val title: String = ""
) {
    fun toFirebaseFund(contributorList: List<DocumentReference>): FirebaseFund {
        return FirebaseFund(
            contributors = contributorList,
            goal = goal,
            amount = amount,
            title = title
        )
    }
}
