package fr.univ.lyon1.lpiem.ratus.ui.transaction

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.creageek.segmentedbutton.SegmentedButton
import com.google.firebase.Timestamp
import fr.univ.lyon1.lpiem.ratus.MainActivity
import fr.univ.lyon1.lpiem.ratus.R
import fr.univ.lyon1.lpiem.ratus.model.Recurrence
import fr.univ.lyon1.lpiem.ratus.model.RecurrenceType
import fr.univ.lyon1.lpiem.ratus.model.TransactionCategory
import java.text.SimpleDateFormat
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
        val transactionDateButton = view.findViewById<Button>(R.id.transactionDateButton)
        val nextButton = view.findViewById<Button>(R.id.transactionRecurrenceNextButton)

        val dateConstraintLayout =
            view.findViewById<ConstraintLayout>(R.id.transactionDateConstraintLayout)
        val recurrenceConstraintLayout =
            view.findViewById<ConstraintLayout>(R.id.transactionRecurrenceConstraintLayout)

        val recurrenceStartDateButton = view.findViewById<Button>(R.id.recurrenceStartDateButton)
        val recurrenceEndDateButton = view.findViewById<Button>(R.id.recurrenceEndDateButton)

        val recurrenceTypeSpinner = view.findViewById<Spinner>(R.id.recurrenceTypeSpinner)

        dateConstraintLayout.visibility = View.GONE
        recurrenceConstraintLayout.visibility = View.GONE
        nextButton.visibility = View.GONE

        segmentedControl.apply {
            initWithItems {
                listOf(getString(R.string.yes), getString(R.string.no))
            }
            // notifies when segment was checked
            onSegmentChecked { segment ->
                Log.d("segmented", "Segment ${segment.text} checked")
                nextButton.visibility = View.VISIBLE
                when (segment.text) {
                    getString(R.string.yes) -> {
                        dateConstraintLayout.visibility = View.GONE
                        recurrenceConstraintLayout.visibility = View.VISIBLE
                    }
                    getString(R.string.no) -> {
                        dateConstraintLayout.visibility = View.VISIBLE
                        recurrenceConstraintLayout.visibility = View.GONE
                    }
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

        val calendar = Calendar.getInstance(TimeZone.getDefault())

        var transactionDate: Timestamp? = null
        var recurrenceStartDate: Timestamp? = null
        var recurrenceEndDate: Timestamp? = null
        transactionDateButton.setOnClickListener { button ->

            // create an OnDateSetListener
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, monthOfYear)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    transactionDate = Timestamp(calendar.time)
                    (button as Button).text =
                        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time)
                }

            DatePickerDialog(
                requireContext(),
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        recurrenceTypeSpinner.adapter =
            RecurrenceTypeAdapter(requireContext(), RecurrenceType.values().toList())

        recurrenceStartDateButton.setOnClickListener { button ->

            // create an OnDateSetListener
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, monthOfYear)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    transactionDate = Timestamp(calendar.time)
                    recurrenceStartDate = Timestamp(calendar.time)
                    (button as Button).text =
                        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time)
                }

            DatePickerDialog(
                requireContext(),
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        recurrenceEndDateButton.setOnClickListener { button ->

            // create an OnDateSetListener
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, monthOfYear)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    recurrenceEndDate = Timestamp(calendar.time)
                    (button as Button).text =
                        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time)
                }

            DatePickerDialog(
                requireContext(),
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        nextButton.setOnClickListener {
            if (transactionDate != null && segmentedControl.checkedRadioButtonId != -1) {
                val bundle = Bundle()
                bundle.putSerializable(
                    "category",
                    requireArguments().get("category") as TransactionCategory
                )
                bundle.putDouble("amount", requireArguments().getDouble("amount"))
                bundle.putSerializable("transactionDate", transactionDate?.toDate())

                when (recurrenceConstraintLayout.isGone) {
                    false -> {
                        if (recurrenceStartDate != null && recurrenceTypeSpinner.selectedItem != null) {
                            bundle.putSerializable(
                                "recurrence",
                                Recurrence(
                                    recurrenceStartDate,
                                    recurrenceEndDate,
                                    (recurrenceTypeSpinner.selectedItem as RecurrenceType).toFirestoreString()
                                )
                            )
                            findNavController().navigate(
                                R.id.action_transactionRecurrenceFragment_to_transactionNameFragment,
                                bundle
                            )
                        } else {
                            Toast.makeText(
                                requireContext(),
                                R.string.please_complete_all_fields,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    true -> {
                        findNavController().navigate(
                            R.id.action_transactionRecurrenceFragment_to_transactionNameFragment,
                            bundle
                        )
                    }
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    R.string.please_complete_all_fields,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return view
    }
}