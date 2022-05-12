package fr.univ.lyon1.lpiem.ratus.model

import android.os.Parcelable
import com.google.firebase.Timestamp
import fr.univ.lyon1.lpiem.ratus.model.Transaction.Companion.fromParcelableHashMap
import java.io.Serializable
import java.util.*
import kotlin.collections.HashMap


data class Transaction (
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

    fun toParcelableHashMap() : HashMap<String, Any?> {
        return hashMapOf(
            "amount" to amount,
            "category" to category,
            "date" to date?.toDate(),
            "title" to title,
            "recurrence" to recurrence?.toParcelableHashMap()
        )
    }

    companion object {
        fun fromParcelableHashMap(map : HashMap<String, Any?>) : Transaction {
            var recurrence : Recurrence? = null
            if (map["recurrence"] != null) {
                recurrence = Recurrence.fromParcelableHashMap(map["recurrence"] as HashMap<String, Any?>)
            }
            return Transaction(
                amount = map["amount"] as Double,
                category = map["category"] as String,
                date = Timestamp(map["date"] as Date),
                title = map["title"] as String,
                recurrence = recurrence
            )
        }
    }

}
