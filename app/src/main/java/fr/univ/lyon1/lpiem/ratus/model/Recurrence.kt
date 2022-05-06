package fr.univ.lyon1.lpiem.ratus.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude
import java.util.*
import kotlin.collections.HashMap

data class Recurrence(
    val start : Timestamp? = null,
    val end : Timestamp? = null,
    val type : String = ""
) {
    fun toHashMap() : HashMap<String, Any?> {
        return hashMapOf(
            "start" to start,
            "end" to end,
            "type" to type
        )
    }
}



