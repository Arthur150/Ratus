package fr.univ.lyon1.lpiem.ratus.ui.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.univ.lyon1.lpiem.ratus.R

class TransactionRecurrenceDisableFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transaction_recurrence_disable, container, false)
    }
}