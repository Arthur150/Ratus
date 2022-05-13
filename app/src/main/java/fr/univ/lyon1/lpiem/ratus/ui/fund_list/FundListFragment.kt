package fr.univ.lyon1.lpiem.ratus.ui.fund_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.univ.lyon1.lpiem.ratus.MainActivity
import fr.univ.lyon1.lpiem.ratus.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class FundListFragment : Fragment() {

    private val viewModel by viewModel<FundListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fund_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.apply {
            title = getString(R.string.my_funds)
            setDisplayHomeAsUpEnabled(true)
        }

        val fundRecyclerView = view.findViewById<RecyclerView>(R.id.fund_list)
        fundRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getFunds().observe(viewLifecycleOwner) { fundList ->
            fundRecyclerView.adapter = FundAdapter(fundList)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadFunds()
    }
}