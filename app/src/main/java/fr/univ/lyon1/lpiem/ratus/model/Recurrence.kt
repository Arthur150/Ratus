package fr.univ.lyon1.lpiem.ratus.model

import com.google.firebase.Timestamp
import java.io.Serializable
import java.util.*
import kotlin.collections.HashMap

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

    fun toParcelableHashMap(): HashMap<String, Any?> {
        return hashMapOf(
            "start" to start?.toDate(),
            "end" to end?.toDate(),
            "type" to type
        )
    }

    companion object {
        fun fromParcelableHashMap(map : HashMap<String, Any?>) : Recurrence {
            return Recurrence(
                start = Timestamp(map["start"] as Date),
                end = Timestamp(map["end"] as Date),
                type = map["type"] as String
            )
        }
    }
}



