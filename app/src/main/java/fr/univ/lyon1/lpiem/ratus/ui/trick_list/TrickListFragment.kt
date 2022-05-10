package fr.univ.lyon1.lpiem.ratus.ui.trick_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.univ.lyon1.lpiem.ratus.MainActivity
import fr.univ.lyon1.lpiem.ratus.R
import fr.univ.lyon1.lpiem.ratus.ui.homePage.TrickAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrickListFragment : Fragment() {

    private val viewModel by viewModel<TrickListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as MainActivity).supportActionBar?.apply {
            title = getString(R.string.tips)
            setDisplayHomeAsUpEnabled(true)
        }

        val view = inflater.inflate(R.layout.fragment_trick_list, container, false)

        val tricksRecyclerView = view.findViewById<RecyclerView>(R.id.tricksRecyclerView)
        tricksRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.tricks.observe(viewLifecycleOwner) { tricks ->
            tricksRecyclerView.adapter = TrickListAdapter(tricks)
        }

        return view
    }
}