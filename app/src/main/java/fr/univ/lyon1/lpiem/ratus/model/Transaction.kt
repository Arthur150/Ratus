package fr.univ.lyon1.lpiem.ratus.model

import com.google.firebase.Timestamp
import java.io.Serializable


data class Transaction(
    val title: String = "",
    val amount: Double = 0.0,
    val category: String = "",
    val date: Timestamp? = null,
    val recurrence: Recurrence? = Recurrence()
) : Serializable {
    fun toHashMap(): HashMap<String, Any?> {
        return hashMapOf(
            "amount" to amount,
            "category" to category,
            "date" to date,
            "title" to title,
            "recurrence" to recurrence?.toHashMap()
        )
    }
}
