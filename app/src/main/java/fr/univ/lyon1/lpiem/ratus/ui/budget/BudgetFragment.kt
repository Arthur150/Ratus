package fr.univ.lyon1.lpiem.ratus.ui.budget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.univ.lyon1.lpiem.ratus.MainActivity
import fr.univ.lyon1.lpiem.ratus.R
import fr.univ.lyon1.lpiem.ratus.ui.Tools
import ir.mahozad.android.PieChart
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.NumberFormat
import java.util.*

class BudgetFragment : Fragment() {

    private val viewModel by viewModel<BudgetViewModel>()
    private val tools by inject<Tools>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_budget, container, false)

        (activity as MainActivity).supportActionBar?.apply {
            title = getString(R.string.budget)
            setDisplayHomeAsUpEnabled(true)
        }

        val amountTextView = view.findViewById<TextView>(R.id.budgetAmount)
        val addTransactionButton = view.findViewById<Button>(R.id.budgetAddTransactionButton)
        val transactionRecyclerView = view.findViewById<RecyclerView>(R.id.budgetTransactionList)

        val pieChart = view.findViewById<PieChart>(R.id.budgetPieChart)

        pieChart.apply {
            isAnimationEnabled = true
            isLegendEnabled = false
            isCenterLabelEnabled = false
            isLegendsPercentageEnabled = false
            labelType = PieChart.LabelType.NONE
        }

        transactionRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        addTransactionButton.setOnClickListener {
            this.findNavController()
                .navigate(R.id.action_budgetFragment_to_transactionInfosFragment)
        }

        val numberFormat = NumberFormat.getInstance()
        numberFormat.maximumFractionDigits = 2
        numberFormat.currency = Currency.getInstance("EUR")

        viewModel.user.observe(viewLifecycleOwner) { user ->
            amountTextView.apply {
                setTextColor(tools.getCurrencyTextColor(requireContext(), user.balance))
                text = tools.formatCurrency(user.balance)
            }
            transactionRecyclerView.adapter =
                TransactionAdapter(user.transactions.sortedByDescending { transaction ->
                    transaction.date
                })

            val totalCategoriesPercent = tools.getCategoryPrecents(user.transactions)
            val sliceList = ArrayList<PieChart.Slice>()
            totalCategoriesPercent.forEach { totalCategory ->
                sliceList.add(PieChart.Slice(totalCategory.value,
                    ContextCompat.getColor(requireContext(), totalCategory.key.color)
                ))
            }
            pieChart.apply {
                slices = sliceList
            }

        }

        return view
    }
}