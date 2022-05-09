package fr.univ.lyon1.lpiem.ratus.ui

import android.content.Context
import androidx.core.content.ContextCompat
import fr.univ.lyon1.lpiem.ratus.R
import java.text.NumberFormat

class Tools {

    fun getCurrencyTextColor(context: Context, amount: Double): Int {
        return if (amount < 0) {
            ContextCompat.getColor(context, R.color.red)
        } else {
            ContextCompat.getColor(context, R.color.green)
        }
    }

    fun formatCurrency(amount: Double): String {
        val numberFormat = NumberFormat.getInstance()
        return if (amount > 0) {
            "+${numberFormat.format(amount)} ${numberFormat.currency.symbol}"
        } else {
            "${numberFormat.format(amount)} ${numberFormat.currency.symbol}"
        }
    }
}