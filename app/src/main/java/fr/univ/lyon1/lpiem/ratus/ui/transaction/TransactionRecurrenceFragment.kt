package fr.univ.lyon1.lpiem.ratus.ui.transaction

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.creageek.segmentedbutton.SegmentedButton
import fr.univ.lyon1.lpiem.ratus.MainActivity
import fr.univ.lyon1.lpiem.ratus.R
import fr.univ.lyon1.lpiem.ratus.model.TransactionCategory
import java.util.*

class TransactionRecurrenceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transaction_recurrence, container, false)

        (activity as MainActivity).supportActionBar?.apply {
            title = getString(R.string.new_transaction)
            setDisplayHomeAsUpEnabled(true)
        }

        val segmentedControl = view.findViewById<SegmentedButton>(R.id.recurrenceSegmentedControl)
        val transactionDateEditText = view.findViewById<EditText>(R.id.transactionDateEditText)
        val nextButton = view.findViewById<Button>(R.id.transactionRecurrenceNextButton)

        segmentedControl.apply {
            initWithItems {
                listOf(getString(R.string.yes),getString(R.string.no))
            }
            initialCheckedIndex = 1
            // notifies when segment was checked
            onSegmentChecked { segment ->
                Log.d("segmented", "Segment ${segment.text} checked")
            }
            // notifies when segment was unchecked
            onSegmentUnchecked { segment ->
                Log.d("segmented", "Segment ${segment.text} unchecked")
            }
            // notifies when segment was rechecked
            onSegmentRechecked { segment ->
                Log.d("segmented", "Segment ${segment.text} rechecked")
            }

        }

        var cal = Calendar.getInstance()

        // create an OnDateSetListener
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                //updateDateInView()
            }

        transactionDateEditText.setOnClickListener {
            DatePickerDialog(requireContext(),
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        nextButton.setOnClickListener {
            findNavController().navigate(R.id.action_transactionRecurrenceFragment_to_transactionNameFragment)
        }

        return view
    }
}