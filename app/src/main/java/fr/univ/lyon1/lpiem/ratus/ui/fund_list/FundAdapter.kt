package fr.univ.lyon1.lpiem.ratus.ui.fund_list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.univ.lyon1.lpiem.ratus.model.Fund

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