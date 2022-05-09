package fr.univ.lyon1.lpiem.ratus.model

import fr.univ.lyon1.lpiem.ratus.R

enum class RecurrenceType(val text: Int) {
    /** @since Unknown type */
    UNKNOWN(R.string.unknown),

    /** @since None */
    NONE(R.string.none),

    /** @since Every day */
    DAILY(R.string.daily),

    /** @since Every week */
    WEEKLY(R.string.weekly),

    /** @since Twice a week */
    BIWEEKLY(R.string.biweekly),

    /** @since Every month */
    MONTHLY(R.string.monthly),

    /** @since Every two months */
    BIMONTHLY(R.string.bimonthly),

    /** @since Every three months */
    QUARTERLY(R.string.quarterly),

    /** @since Every year */
    ANNUAL(R.string.annual),

    /** @since Twice a year */
    BIANNUAL(R.string.biannual);


    fun toFirestoreString(): String {
        return toString().lowercase()
    }

    companion object {
        fun stringToRecurrenceType(type: String): RecurrenceType {
            when (type) {
                NONE.toFirestoreString() -> return NONE
                DAILY.toFirestoreString() -> return DAILY
                WEEKLY.toFirestoreString() -> return WEEKLY
                BIWEEKLY.toFirestoreString() -> return BIWEEKLY
                MONTHLY.toFirestoreString() -> return MONTHLY
                BIMONTHLY.toFirestoreString() -> return BIMONTHLY
                QUARTERLY.toFirestoreString() -> return QUARTERLY
                ANNUAL.toFirestoreString() -> return ANNUAL
                BIANNUAL.toFirestoreString() -> return BIANNUAL
            }
            return UNKNOWN
        }
    }
}