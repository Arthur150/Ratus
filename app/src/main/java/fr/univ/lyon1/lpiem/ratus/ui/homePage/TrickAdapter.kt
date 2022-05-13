package fr.univ.lyon1.lpiem.ratus.ui.homePage

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.univ.lyon1.lpiem.ratus.model.Trick

class TrickAdapter(private val trickList: List<Trick>) :
    RecyclerView.Adapter<TrickViewHolder>() {

    companion object {
        private const val TAG = "TrickAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrickViewHolder {
        return TrickViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: TrickViewHolder, position: Int) {
        trickList[position].color = position % 2
        Log.d(TAG, "onBindViewHolder: ${trickList[position].color}")
        holder.bind(trickList[position])
    }

    override fun getItemCount() = trickList.size
}