package fr.univ.lyon1.lpiem.ratus.model

import com.google.type.DateTime

data class Recurrence(
    val start : DateTime?,
    val end : DateTime?,
    val type : String
){
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



