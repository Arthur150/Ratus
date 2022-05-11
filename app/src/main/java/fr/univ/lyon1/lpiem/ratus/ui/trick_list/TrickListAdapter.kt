package fr.univ.lyon1.lpiem.ratus.ui.trick_list

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.univ.lyon1.lpiem.ratus.R
import fr.univ.lyon1.lpiem.ratus.model.Trick

class TrickListAdapter(private val trickList: List<Trick>) :
    RecyclerView.Adapter<TrickListViewHolder>() {

    companion object {
        private const val TAG = "TrickAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrickListViewHolder {
        return TrickListViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: TrickListViewHolder, position: Int) {
        trickList[position].color = position % 2
        Log.d(TAG, "onBindViewHolder: ${trickList[position].color}")
        holder.bind(trickList[position])
    }

    override fun getItemCount() = trickList.size
}