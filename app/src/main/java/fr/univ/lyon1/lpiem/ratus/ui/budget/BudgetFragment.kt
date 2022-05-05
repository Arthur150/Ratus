package fr.univ.lyon1.lpiem.ratus.ui.budget

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.univ.lyon1.lpiem.ratus.R

class BudgetFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_budget, container, false)

        //TODO call

        val amountTextView = view.findViewById<TextView>(R.id.budgetAmount)
        val addTransactionButton = view.findViewById<Button>(R.id.budgetAddTransactionButton)
        val transactionRecyclerView = view.findViewById<RecyclerView>(R.id.budgetTransactionList)

        return view
    }
}