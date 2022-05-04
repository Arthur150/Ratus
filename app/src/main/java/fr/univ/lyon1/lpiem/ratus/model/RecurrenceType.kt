package fr.univ.lyon1.lpiem.ratus.model

enum class RecurrenceType {
    /** @since Unknown type */
    UNKNOWN,
    /** @since None */
    NONE,
    /** @since Every day */
    DAILY,
    /** @since Every week */
    WEEKLY,
    /** @since Twice a week */
    BIWEEKLY,
    /** @since Every month */
    MONTHLY,
    /** @since Every two months */
    BIMONTHLY,
    /** @since Every three months */
    QUARTERLY,
    /** @since Every year */
    ANNUAL,
    /** @since Twice a year */
    BIANNUAL
}