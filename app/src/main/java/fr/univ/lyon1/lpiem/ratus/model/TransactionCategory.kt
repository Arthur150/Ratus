package fr.univ.lyon1.lpiem.ratus.model

import android.graphics.Color
import fr.univ.lyon1.lpiem.ratus.R

enum class TransactionCategory(val icon: Int, val text: Int, val color: Int) {
    /** @since Courses */
    SHOPPING(R.drawable.shopping_basket, R.string.shopping, R.color.shopping_color),

    /** @since Vehicle */
    VEHICLE(R.drawable.directions_car, R.string.vehicle, R.color.vehicle_color),

    /** @since Sport */
    SPORT(R.drawable.sports_soccer, R.string.sport, R.color.sport_color),

    /** @since Culture */
    CULTURE(R.drawable.auto_stories, R.string.culture, R.color.culture_color),

    /** @since Entertainment */
    ENTERTAINMENT(R.drawable.movie, R.string.entertaiment, R.color.entertainment_color),

    /** @since Travel */
    TRAVEL(R.drawable.flight_takeoff, R.string.travel, R.color.travel_color),

    /** @since Savings */
    SAVINGS(R.drawable.savings, R.string.savings, R.color.savings_color),

    /** @since Taxes */
    TAXES(R.drawable.account_balance, R.string.taxes, R.color.taxes_color),

    /** @since Misc */
    MISC(R.drawable.inventory_2, R.string.misc, R.color.misc_color),

    /** @since Gift */
    GIFT(R.drawable.redeem, R.string.gift, R.color.gift_color),

    /** @since Refund */
    REFUND(R.drawable.loyalty, R.string.refund, R.color.refund_color),

    /** @since Pay */
    PAY(R.drawable.payments, R.string.pay, R.color.pay_color),

    /** @since Help */
    HELP(R.drawable.volunteer_activism, R.string.help, R.color.help_color),

    /** @since Health */
    HEALTH(R.drawable.medical_services, R.string.health, R.color.health_color);

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