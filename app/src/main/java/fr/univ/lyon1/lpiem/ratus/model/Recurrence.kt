package fr.univ.lyon1.lpiem.ratus.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude

data class Recurrence(
    val start : Timestamp? = null,
    val end : Timestamp? = null,
    val type : String = ""
){
    @Exclude
    fun recurrenceType() : RecurrenceType {
        when (type) {
            "none" -> return RecurrenceType.NONE
            "daily" -> return RecurrenceType.DAILY
            "weekly" -> return RecurrenceType.WEEKLY
            "biweekly" -> return RecurrenceType.BIWEEKLY
            "monthly" -> return RecurrenceType.MONTHLY
            "bimonthly" -> return RecurrenceType.BIMONTHLY
            "quarterly" -> return RecurrenceType.QUARTERLY
            "annual" -> return RecurrenceType.ANNUAL
            "biannual" -> return RecurrenceType.BIANNUAL
        }
        return RecurrenceType.UNKNOWN
    }
}



