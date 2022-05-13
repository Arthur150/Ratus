package fr.univ.lyon1.lpiem.ratus.ui

import android.content.Context
import androidx.core.content.ContextCompat
import fr.univ.lyon1.lpiem.ratus.R
import fr.univ.lyon1.lpiem.ratus.model.Transaction
import fr.univ.lyon1.lpiem.ratus.model.TransactionCategory
import java.text.NumberFormat
import kotlin.math.absoluteValue

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
            "+${numberFormat.format(amount)} ${numberFormat.currency?.symbol}"
        } else {
            "${numberFormat.format(amount)} ${numberFormat.currency?.symbol}"
        }
    }

    fun getCategoryPrecents(transactions: List<Transaction>): Map<TransactionCategory, Float> {
        var total = 0.0
        val totalCategories = mutableMapOf<TransactionCategory, Double>()

        TransactionCategory.values().forEach { transactionCategory ->
            totalCategories[transactionCategory] = 0.0
        }
        transactions.forEach { transaction ->
            total += transaction.amount.absoluteValue
            val category = TransactionCategory.stringToTransactionCategory(transaction.category)
            totalCategories[category]?.plus(transaction.amount.absoluteValue)?.let {
                totalCategories[category] = it
            }
        }

        val totalCategoriesPercents = mutableMapOf<TransactionCategory, Float>()
        TransactionCategory.values().forEach { transactionCategory ->
            totalCategoriesPercents[transactionCategory] =
                (totalCategories[transactionCategory]?.div(
                    total
                ))?.toFloat() ?: 0f
        }

        return totalCategoriesPercents
    }

    fun formatAmount(amount: Double): CharSequence? {
        val numberFormat = NumberFormat.getInstance()
        return "${numberFormat.format(amount)} ${numberFormat.currency?.symbol}"
    }
}