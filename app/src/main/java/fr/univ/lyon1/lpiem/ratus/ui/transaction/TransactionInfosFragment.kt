package fr.univ.lyon1.lpiem.ratus.ui.transaction

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.creageek.segmentedbutton.SegmentedButton
import fr.univ.lyon1.lpiem.ratus.MainActivity
import fr.univ.lyon1.lpiem.ratus.R
import fr.univ.lyon1.lpiem.ratus.model.TransactionCategory
import java.text.NumberFormat
import java.util.*
import kotlin.math.absoluteValue

class TransactionInfosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transaction_infos, container, false)

        (activity as MainActivity).supportActionBar?.apply {
            title = getString(R.string.new_transaction)
            setDisplayHomeAsUpEnabled(true)
        }

        val segmentedControl = view.findViewById<SegmentedButton>(R.id.transactionInfosSegmentedControl)
        val transactionAmountEditText = view.findViewById<EditText>(R.id.transactionAmountEditText)
        val transactionCategorySpinner = view.findViewById<Spinner>(R.id.transactionCategorySpinner)
        val nextButton = view.findViewById<Button>(R.id.transactionInfosNextButton)

        segmentedControl.apply {
            initWithItems {
                listOf(getString(R.string.`in`),getString(R.string.out))
            }
            initialCheckedIndex = 0
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

        transactionCategorySpinner.adapter = CategoryAdapter(requireContext(),TransactionCategory.values().toList())

        nextButton.setOnClickListener {
            if (transactionAmountEditText.text.isNotEmpty()) {
                val bundle = bundleOf(
                    "amount" to getAmountValueString(transactionAmountEditText.text.toString(), segmentedControl.checkedRadioButtonId),
                    "category" to transactionCategorySpinner.selectedItem as TransactionCategory
                )
                findNavController().navigate(R.id.action_transactionInfosFragment_to_transactionRecurrenceFragment, bundle)
            } else {
                Toast.makeText(requireContext(),R.string.please_complete_all_fields, Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun getAmountValueString(amount: String, inOut: Int): String {
        val numberFormat = NumberFormat.getInstance()
        numberFormat.maximumFractionDigits = 2
        numberFormat.currency = Currency.getInstance("EUR")

        return if (inOut == 0) {
            "+${numberFormat.format(amount.toDouble())}"
        } else {
            "-${numberFormat.format(amount.toDouble())}"
        }
    }
}