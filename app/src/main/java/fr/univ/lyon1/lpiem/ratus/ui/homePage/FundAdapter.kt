package fr.univ.lyon1.lpiem.ratus.ui.homePage

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.univ.lyon1.lpiem.ratus.model.Fund
import fr.univ.lyon1.lpiem.ratus.ui.budget.TransactionViewHolder

class FundAdapter(private val fundList: List<Fund>) :
    RecyclerView.Adapter<FundViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FundViewHolder {
        return FundViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: FundViewHolder, position: Int) {
        holder.bind(fundList[position])
    }

    override fun getItemCount() = fundList.size
}