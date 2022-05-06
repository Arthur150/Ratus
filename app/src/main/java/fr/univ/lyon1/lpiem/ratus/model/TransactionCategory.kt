package fr.univ.lyon1.lpiem.ratus.model

import fr.univ.lyon1.lpiem.ratus.R

enum class TransactionCategory(val icon: Int, val text: Int) {
    /** @since Courses */
    SHOPPING(R.drawable.shopping_basket, R.string.shopping),

    /** @since Vehicle */
    VEHICLE(R.drawable.directions_car, R.string.vehicle),

    /** @since Sport */
    SPORT(R.drawable.sports_soccer, R.string.sport),

    /** @since Culture */
    CULTURE(R.drawable.auto_stories, R.string.culture),

    /** @since Entertainment */
    ENTERTAINMENT(R.drawable.movie, R.string.entertaiment),

    /** @since Travel */
    TRAVEL(R.drawable.flight_takeoff, R.string.travel),

    /** @since Savings */
    SAVINGS(R.drawable.savings, R.string.savings),

    /** @since Taxes */
    TAXES(R.drawable.account_balance, R.string.taxes),

    /** @since Misc */
    MISC(R.drawable.inventory_2, R.string.misc),

    /** @since Gift */
    GIFT(R.drawable.redeem, R.string.gift),

    /** @since Refund */
    REFUND(R.drawable.loyalty, R.string.refund),

    /** @since Pay */
    PAY(R.drawable.payments, R.string.pay),

    /** @since Help */
    HELP(R.drawable.volunteer_activism, R.string.help),

    /** @since Health */
    HEALTH(R.drawable.medical_services, R.string.health);

    fun toFirestoreString(): String {
        return toString().lowercase()
    }

    companion object {
        fun stringToTransactionCategory(type: String): TransactionCategory {
            when (type) {
                SHOPPING.toFirestoreString() -> return SHOPPING
                VEHICLE.toFirestoreString() -> return VEHICLE
                SPORT.toFirestoreString() -> return SPORT
                CULTURE.toFirestoreString() -> return CULTURE
                ENTERTAINMENT.toFirestoreString() -> return ENTERTAINMENT
                TRAVEL.toFirestoreString() -> return TRAVEL
                SAVINGS.toFirestoreString() -> return SAVINGS
                TAXES.toFirestoreString() -> return TAXES
                MISC.toFirestoreString() -> return MISC
                GIFT.toFirestoreString() -> return GIFT
                REFUND.toFirestoreString() -> return REFUND
                PAY.toFirestoreString() -> return PAY
                HELP.toFirestoreString() -> return HELP
                HEALTH.toFirestoreString() -> return HEALTH
            }
            return MISC
        }
    }

}