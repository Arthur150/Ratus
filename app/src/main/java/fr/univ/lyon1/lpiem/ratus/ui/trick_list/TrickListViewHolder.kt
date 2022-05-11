package fr.univ.lyon1.lpiem.ratus.ui.trick_list

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
import fr.univ.lyon1.lpiem.ratus.databinding.ViewHolderBigTrickBinding
import fr.univ.lyon1.lpiem.ratus.databinding.ViewHolderTrickBinding
import fr.univ.lyon1.lpiem.ratus.model.Trick


class TrickListViewHolder private constructor(
    private val binding: ViewHolderBigTrickBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(parent: ViewGroup): TrickListViewHolder {
            return TrickListViewHolder(
                ViewHolderBigTrickBinding.inflate(
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
                view.findNavController().navigate(fr.univ.lyon1.lpiem.ratus.R.id.action_trickListFragment_to_trickDetailFragment, bundle)
            }
            val theme: Theme = root.context.theme
            val cardColor = TypedValue()
            val textColor = TypedValue()
            if (trick.color == 0) {
                bigTrickImage.load(trick.image)
                bigTrickTitle.text = trick.title
                bigTrickDesc.text = trick.description
                bigCardView.visibility = View.VISIBLE
                theme.resolveAttribute(R.attr.colorPrimary, cardColor, true)
                theme.resolveAttribute(R.attr.colorOnPrimary, textColor, true)
                bigCardView.setCardBackgroundColor(cardColor.data)
                bigTrickTitle.setTextColor(textColor.data)
                bigTrickDesc.setTextColor(textColor.data)
            }
            else {
                alternativeBigTrickImage.load(trick.image)
                alternativeBigTrickTitle.text = trick.title
                alternativeBigTrickDesc.text = trick.description
                alternativeBigCardView.visibility = View.VISIBLE
                theme.resolveAttribute(R.attr.colorSecondary, cardColor, true)
                theme.resolveAttribute(R.attr.colorOnSecondary, textColor, true)
                alternativeBigCardView.setCardBackgroundColor(cardColor.data)
                alternativeBigTrickTitle.setTextColor(textColor.data)
                alternativeBigTrickDesc.setTextColor(textColor.data)
            }

        }
    }
}