package fr.univ.lyon1.lpiem.ratus.ui.homePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import fr.univ.lyon1.lpiem.ratus.MainActivity
import fr.univ.lyon1.lpiem.ratus.R
import fr.univ.lyon1.lpiem.ratus.ui.Tools
import fr.univ.lyon1.lpiem.ratus.ui.fund_list.FundAdapter
import ir.mahozad.android.PieChart
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

        val pieChart = view.findViewById<PieChart>(R.id.pieChart)

        pieChart.apply {
            isAnimationEnabled = true
            isLegendEnabled = false
            isCenterLabelEnabled = false
            isLegendsPercentageEnabled = false
            labelType = PieChart.LabelType.NONE
        }

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

        viewModel.tricks.observe(viewLifecycleOwner) { trickList ->
            tipsRecyclerView.adapter = TrickAdapter(trickList)
        }

        viewModel.getFunds().observe(viewLifecycleOwner) { fundList ->
            fundsRecyclerView.adapter = FundAdapter(fundList)
        }


        showAllFunds.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_fundListFragment)
        }

        addFund.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_editFundFragment)
        }

        fundsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getUser().observe(viewLifecycleOwner) { user ->
            budgetCard.setOnClickListener {
                findNavController().navigate(R.id.action_homePageFragment_to_budgetFragment)
            }
            budgetBalance.apply {
                setTextColor(tools.getCurrencyTextColor(requireContext(), user.balance))
                text = tools.formatCurrency(user.balance)
            }
            val sortedTransaction = user.transactions.sortedByDescending { transaction ->
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
                        visibility = View.VISIBLE
                    }
                }
            }

            val totalCategoriesPercent = tools.getCategoryPrecents(user.transactions)
            val sliceList = ArrayList<PieChart.Slice>()
            totalCategoriesPercent.forEach { totalCategory ->
                sliceList.add(
                    PieChart.Slice(
                        totalCategory.value,
                        getColor(requireContext(), totalCategory.key.color)
                    )
                )
            }
            pieChart.apply {
                slices = sliceList
            }

        }

        return view
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadUserDetails()
    }

}