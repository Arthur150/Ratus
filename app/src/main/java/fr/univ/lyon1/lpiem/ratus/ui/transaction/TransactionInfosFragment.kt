package fr.univ.lyon1.lpiem.ratus.ui.transaction

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.creageek.segmentedbutton.SegmentedButton
import fr.univ.lyon1.lpiem.ratus.MainActivity
import fr.univ.lyon1.lpiem.ratus.R
import fr.univ.lyon1.lpiem.ratus.model.TransactionCategory
import org.w3c.dom.Text
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
        val transactionAmountLabel = view.findViewById<TextView>(R.id.transactionAmountTextView)
        val transactionAmountEditText = view.findViewById<EditText>(R.id.transactionAmountEditText)
        val transactionCategoryLabel = view.findViewById<TextView>(R.id.transactionCategoryTextView)
        val transactionCategorySpinner = view.findViewById<Spinner>(R.id.transactionCategorySpinner)
        val nextButton = view.findViewById<Button>(R.id.transactionInfosNextButton)

        transactionAmountLabel.visibility = View.GONE
        transactionAmountEditText.visibility = View.GONE
        transactionCategoryLabel.visibility = View.GONE
        transactionCategorySpinner.visibility = View.GONE
        nextButton.visibility = View.GONE

        var segmentCheckedId = -1

        segmentedControl.apply {
            initWithItems {
                listOf(getString(R.string.`in`),getString(R.string.out))
            }
            // notifies when segment was checked
            onSegmentChecked { segment ->
                Log.d("segmented", "Segment ${segment.text} checked")
                transactionAmountLabel.visibility = View.VISIBLE
                transactionAmountEditText.visibility = View.VISIBLE
                transactionCategoryLabel.visibility = View.VISIBLE
                transactionCategorySpinner.visibility = View.VISIBLE
                nextButton.visibility = View.VISIBLE

                when(segment.text){
                    getString(R.string.`in`) -> segmentCheckedId = 0
                    getString(R.string.out) -> segmentCheckedId = 1
                }
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
            if (transactionAmountEditText.text.isNotEmpty() && segmentedControl.checkedRadioButtonId != -1) {
                val bundle = Bundle()
                bundle.putSerializable("category", transactionCategorySpinner.selectedItem as TransactionCategory)
                bundle.putDouble("amount", getAmountValue(transactionAmountEditText.text.toString(), segmentCheckedId))
                findNavController().navigate(R.id.action_transactionInfosFragment_to_transactionRecurrenceFragment, bundle)
            } else {
                Toast.makeText(requireContext(),R.string.please_complete_all_fields, Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun getAmountValue(amount: String, inOut: Int): Double {

        return if (inOut == 0) {
            amount.toDouble().absoluteValue
        } else {
            -amount.toDouble().absoluteValue
        }
    }
}