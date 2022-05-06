package fr.univ.lyon1.lpiem.ratus.ui.homePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import fr.univ.lyon1.lpiem.ratus.MainActivity
import fr.univ.lyon1.lpiem.ratus.R
import fr.univ.lyon1.lpiem.ratus.ui.Tools
import fr.univ.lyon1.lpiem.ratus.ui.budget.TransactionAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomePageFragment : Fragment() {

    val viewModel by viewModel<HomePageViewModel>()
    private val tools by inject<Tools>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_page, container, false)

        (activity as MainActivity).supportActionBar?.apply {
            title = getString(R.string.home)
            setDisplayHomeAsUpEnabled(false)
        }

        val budgetCard = view.findViewById<CardView>(R.id.homeBudgetCard)
        val budgetBalance = view.findViewById<TextView>(R.id.homeBudgetBalanceTextView)

        val transactionTextViews = arrayOf<TextView>(
            view.findViewById(R.id.homeTransaction1),
            view.findViewById(R.id.homeTransaction2),
            view.findViewById(R.id.homeTransaction3)
        )

        val addTransactionButton = view.findViewById<Button>(R.id.homeAddTransactionButton)

        val showAllTips = view.findViewById<TextView>(R.id.showAllTipsTextView)

        val tipsRecyclerView = view.findViewById<RecyclerView>(R.id.homeTipsRecyclerView)

        val showAllFunds = view.findViewById<TextView>(R.id.showAllFundsTextView)
        val addFund = view.findViewById<ImageView>(R.id.addFundImageView)

        val fundsRecyclerView = view.findViewById<RecyclerView>(R.id.homeFundsRecyclerView)

        addTransactionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_transactionInfosFragment)
        }

        showAllTips.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_trickListFragment)
        }

        tipsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(tipsRecyclerView)

        showAllFunds.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_fundListFragment)
        }

        addFund.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_editFundFragment)
        }

        fundsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.user.observe(viewLifecycleOwner) { user ->
            budgetCard.setOnClickListener {
                findNavController().navigate(R.id.action_homePageFragment_to_budgetFragment)
            }
            budgetBalance.apply {
                setTextColor(tools.getCurrencyTextColor(requireContext(), user.budget.balance))
                text = tools.formatCurrency(user.budget.balance)
            }
            val sortedTransaction = user.budget.transactions.sortedByDescending { transaction ->
                transaction.date
            }
            transactionTextViews.forEachIndexed { index, textView ->
                textView.apply {
                    if (index < sortedTransaction.size) {
                        sortedTransaction[index].let {
                            setTextColor(
                                tools.getCurrencyTextColor(
                                    requireContext(),
                                    sortedTransaction[index].amount
                                )
                            )
                            text = tools.formatCurrency(sortedTransaction[index].amount)
                        }
                    }
                }
            }

            //TODO change transactions to tricks
            tipsRecyclerView.adapter = TransactionAdapter(sortedTransaction)

            //TODO change transactions to funds
            fundsRecyclerView.adapter =
                TransactionAdapter(sortedTransaction.plus(sortedTransaction))
        }

        return view
    }
}