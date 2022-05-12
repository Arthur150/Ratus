package fr.univ.lyon1.lpiem.ratus.model

import com.google.firebase.Timestamp
import java.io.Serializable

data class Recurrence(
    val start: Timestamp? = null,
    val end: Timestamp? = null,
    val type: String = ""
): Serializable {
    fun toHashMap(): HashMap<String, Any?> {
        return hashMapOf(
            "start" to start,
            "end" to end,
            "type" to type
        )
    }
}



