package fr.univ.lyon1.lpiem.ratus.ui.budget

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.univ.lyon1.lpiem.ratus.model.Transaction

class TransactionAdapter(private val transactionList: List<Transaction>): RecyclerView.Adapter<TransactionViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
            return TransactionViewHolder.newInstance(parent)
        }

        override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
            holder.bind(transactionList[position])
        }

        override fun getItemCount() = transactionList.size
}