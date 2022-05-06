package fr.univ.lyon1.lpiem.ratus.ui.budget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getDrawable
import androidx.recyclerview.widget.RecyclerView
import fr.univ.lyon1.lpiem.ratus.databinding.ViewHolderTransactionBinding
import fr.univ.lyon1.lpiem.ratus.model.Transaction
import fr.univ.lyon1.lpiem.ratus.model.TransactionCategory
import fr.univ.lyon1.lpiem.ratus.ui.Tools
import org.koin.java.KoinJavaComponent.inject
import java.text.DateFormat

class TransactionViewHolder private constructor(
    private val binding: ViewHolderTransactionBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val tools: Tools by inject(Tools::class.java)

    companion object {
        fun newInstance(parent: ViewGroup): TransactionViewHolder {
            return TransactionViewHolder(
                ViewHolderTransactionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun bind(transaction: Transaction) {
        val dateFormat = DateFormat.getDateInstance()

        with(binding) {
            transactionAmount.setTextColor(
                tools.getCurrencyTextColor(
                    root.context,
                    transaction.amount
                )
            )
            transactionAmount.text = tools.formatCurrency(transaction.amount)
            transactionDate.text = transaction.date?.toDate()?.let { dateFormat.format(it) }
            transationName.text = transaction.title
            transactionCategoryImageView.setImageDrawable(
                getDrawable(
                    root.context,
                    TransactionCategory.stringToTransactionCategory(transaction.category).icon
                )
            )
        }
    }
}