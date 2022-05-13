package fr.univ.lyon1.lpiem.ratus.ui.fund_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.univ.lyon1.lpiem.ratus.MainActivity
import fr.univ.lyon1.lpiem.ratus.R
import fr.univ.lyon1.lpiem.ratus.ui.Tools
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent.inject
import kotlin.math.roundToInt

class FundFragment : Fragment() {

    companion object {
        private const val TAG = "FundFragment"
    }

    private val viewModel by viewModel<FundViewModel>()
    private val tools: Tools by inject(Tools::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fund, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
        }
        val id = arguments?.getString("fundId")

        if (id != null) {
            viewModel.getFund(id)
        }

        val contributorsRecyclerView = view.findViewById<RecyclerView>(R.id.fundDetailContributors)
        contributorsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        viewModel.fund.observe(viewLifecycleOwner) { fund ->
            view.findViewById<TextView>(R.id.fundDetailTitle).text = fund.title
            view.findViewById<TextView>(R.id.fundDetailAmount).text =
                tools.formatAmount(fund.amount)
            val percent = ((fund.amount / fund.goal) * 100).roundToInt()
            view.findViewById<TextView>(R.id.fundDetailPercent).text = "$percent%"
            view.findViewById<ProgressBar>(R.id.fundDetailProgressBar).let {
                it.max = fund.goal.roundToInt()
                it.progress = fund.amount.roundToInt()
            }
            contributorsRecyclerView.adapter = UserAdapter(fund.contributors)

            val addContributorView =
                View.inflate(requireContext(), R.layout.add_contributor_layout, null)

            val alertDialog = AlertDialog.Builder(requireContext())
                .setView(addContributorView)
                .create()

            addContributorView.findViewById<Button>(R.id.addCancelButton).setOnClickListener {
                alertDialog.cancel()
            }

            addContributorView.findViewById<Button>(R.id.addContributorPopupButton)
                .setOnClickListener {
                    val uid = addContributorView.findViewById<EditText>(R.id.userUidInput).text

                    if (uid.isNotEmpty()) {
                        viewModel.addContributor(uid.toString())
                        alertDialog.cancel()
                    }
                }

            viewModel.getError().observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                alertDialog.show()
            }

            view.findViewById<ImageView>(R.id.fundDetailAddContributor).setOnClickListener {
                alertDialog.show()
            }
        }

    }
}