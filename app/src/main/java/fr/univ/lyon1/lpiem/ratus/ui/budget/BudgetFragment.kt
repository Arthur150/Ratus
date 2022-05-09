package fr.univ.lyon1.lpiem.ratus.ui.budget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.univ.lyon1.lpiem.ratus.MainActivity
import fr.univ.lyon1.lpiem.ratus.R
import fr.univ.lyon1.lpiem.ratus.ui.Tools
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
        }

        return view
    }
}