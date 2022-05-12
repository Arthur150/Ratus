package fr.univ.lyon1.lpiem.ratus.ui.homePage

import android.content.res.Resources.Theme
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.R
import fr.univ.lyon1.lpiem.ratus.databinding.ViewHolderTrickBinding
import fr.univ.lyon1.lpiem.ratus.model.Trick
import fr.univ.lyon1.lpiem.ratus.R as RatusR

class TrickViewHolder private constructor(
    private val binding: ViewHolderTrickBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(parent: ViewGroup): TrickViewHolder {
            return TrickViewHolder(
                ViewHolderTrickBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun bind(trick: Trick) {

        with(binding){
            root.setOnClickListener { view ->
                val bundle = bundleOf("trickId" to trick.id)
                view.findNavController().navigate(RatusR.id.action_homePageFragment_to_trickDetailFragment, bundle)
            }
            val theme: Theme = root.context.theme
            val cardColor = TypedValue()
            val textColor = TypedValue()
            if (trick.color == 0) {
                theme.resolveAttribute(R.attr.colorPrimary, cardColor, true)
                theme.resolveAttribute(R.attr.colorOnPrimary, textColor, true)

            }
            else {
                theme.resolveAttribute(R.attr.colorSecondary, cardColor, true)
                theme.resolveAttribute(R.attr.colorOnSecondary, textColor, true)
            }
            trickImage.load(trick.image)
            trickTitle.text = trick.title
            trickDesc.text = trick.description
            cardView.setCardBackgroundColor(cardColor.data)
            trickTitle.setTextColor(textColor.data)
            trickDesc.setTextColor(textColor.data)

        }
    }
}