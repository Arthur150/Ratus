package fr.univ.lyon1.lpiem.ratus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class HomePageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_page, container, false)

        val budgetButton = view.findViewById<Button>(R.id.budgetButton)
        budgetButton.setOnClickListener {
            this.findNavController().navigate(R.id.action_homePageFragment_to_budgetFragment)
        }

        return view
    }
}