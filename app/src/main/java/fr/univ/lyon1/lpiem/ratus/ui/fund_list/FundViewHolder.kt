package fr.univ.lyon1.lpiem.ratus.ui.fund_list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import fr.univ.lyon1.lpiem.ratus.R
import fr.univ.lyon1.lpiem.ratus.databinding.ViewHolderFundBinding
import fr.univ.lyon1.lpiem.ratus.model.Fund
import fr.univ.lyon1.lpiem.ratus.ui.Tools
import fr.univ.lyon1.lpiem.ratus.ui.homePage.HomePageFragment
import org.koin.java.KoinJavaComponent
import java.text.NumberFormat
import kotlin.math.roundToInt

class FundViewHolder private constructor(
    private val binding: ViewHolderFundBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val tools: Tools by KoinJavaComponent.inject(Tools::class.java)

    companion object {
        fun newInstance(parent: ViewGroup): FundViewHolder {
            return FundViewHolder(
                ViewHolderFundBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
        private const val TAG = "FundViewHolder"
    }

    fun bind(fund: Fund) {

        with(binding) {
            root.setOnClickListener { view ->
                val start = view.findNavController().currentDestination
                if (start != null) {
                    val bundle = bundleOf("fundId" to fund.id)
                    if (start.displayName.compareTo("fr.univ.lyon1.lpiem.ratus:id/homePageFragment") == 0){
                        view.findNavController().navigate(R.id.action_homePageFragment_to_fundFragment, bundle)
                    }
                    else if (start.displayName.compareTo("fr.univ.lyon1.lpiem.ratus:id/fundListFragment") == 0){
                        view.findNavController().navigate(R.id.action_fundListFragment_to_fundFragment, bundle)
                    }
                }
            }
            val numberFormat = NumberFormat.getInstance()
            fundTitle.text = fund.title.replaceFirstChar { it.uppercase() }
            fundGoal.text = "${numberFormat.format(fund.goal)} ${numberFormat.currency?.symbol}"
            fundImage.load("https://imgs.search.brave.com/TKa5b4yJKYD8wF0fvgJtZ7MP39weL0AeHFSkW6xNJU4/rs:fit:870:580:1/g:ce/aHR0cHM6Ly9mb3Jl/aWducG9saWN5aS5v/cmcvd3AtY29udGVu/dC91cGxvYWRzLzIw/MTkvMDgvdmFjYXRp/b24ucG5n")
            fundProgressBar.max = fund.goal.roundToInt()
            fundProgressBar.progress= fund.amount.roundToInt()
        }
    }
}