package fr.univ.lyon1.lpiem.ratus.ui.budget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.univ.lyon1.lpiem.ratus.databinding.ViewHolderTransactionBinding
import fr.univ.lyon1.lpiem.ratus.model.Transaction
import java.text.DateFormat
import java.text.NumberFormat

class TransactionViewHolder private constructor(
    private val binding: ViewHolderTransactionBinding
) : RecyclerView.ViewHolder(binding.root) {

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
        val numberFormat = NumberFormat.getInstance()
        val dateFormat = DateFormat.getDateInstance()

        with(binding){
            transactionAmount.text = "${numberFormat.format(transaction.amount)} ${numberFormat.currency.symbol}"
            transactionDate.text = transaction.date?.toDate()?.let { dateFormat.format(it) }
            transationName.text = transaction.title
        }
    }
}