package fr.univ.lyon1.lpiem.ratus.ui.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import fr.univ.lyon1.lpiem.ratus.MainActivity
import fr.univ.lyon1.lpiem.ratus.R
import fr.univ.lyon1.lpiem.ratus.model.Recurrence
import fr.univ.lyon1.lpiem.ratus.model.RecurrenceType
import fr.univ.lyon1.lpiem.ratus.model.Transaction
import fr.univ.lyon1.lpiem.ratus.model.TransactionCategory
import fr.univ.lyon1.lpiem.ratus.ui.Tools
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*

class TransactionSummaryFragment : Fragment() {

    private val tools by inject<Tools>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transaction_summary, container, false)

        (activity as MainActivity).supportActionBar?.apply {
            title = getString(R.string.new_transaction)
            setDisplayHomeAsUpEnabled(true)
        }

        val transactionName = view.findViewById<TextView>(R.id.transactionSummaryNameTextView)
        val transactionAmount =  view.findViewById<TextView>(R.id.transactionSummaryAmountTextView)
        val transactionCategory = view.findViewById<TextView>(R.id.transactionSummaryCategoryTextView)
        val transactionDateRecurrenceLabel = view.findViewById<TextView>(R.id.transactionSummaryDateRecurrenceTextView)

        val transactionRecurrenceType = view.findViewById<TextView>(R.id.transactionSummaryRecurrenceTypeTextView)
        val transactionRecurrenceStart = view.findViewById<TextView>(R.id.transactionSummaryRecurrenceStartTextView)
        val transactionRecurrenceEnd = view.findViewById<TextView>(R.id.transactionSummaryRecurrenceEndTextView)

        val transactionDate = view.findViewById<TextView>(R.id.transactionSummaryDateTextView)

        val transactionRecurrenceLayout = view.findViewById<ConstraintLayout>(R.id.transactionSummaryRecurrenceConstraintLayout)

        val transactionCancelButton = view.findViewById<Button>(R.id.transactionSummaryCancelButton)
        val transactionValidateButton = view.findViewById<Button>(R.id.transactionSummaryValidateButton)

        val transaction = requireArguments().getSerializable("transaction") as Transaction



        transactionName.text = transaction.title
        transactionAmount.text = tools.formatCurrency(transaction.amount)
        transactionCategory.text =  "${getString(R.string.category)} : ${getString(TransactionCategory.stringToTransactionCategory(transaction.category).text)}"

        if (transaction.recurrence == null){
            transactionDateRecurrenceLabel.text = getString(R.string.transaction_date)
            transactionRecurrenceLayout.visibility = View.GONE
            transactionDate.visibility = View.VISIBLE
            transactionDate.text =
                transaction.date?.toDate()
                    ?.let { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(it) }

        } else {
            transactionDateRecurrenceLabel.text = getString(R.string.recurrence)
            transactionDate.visibility = View.GONE
            transactionDate.visibility = View.VISIBLE
            transactionRecurrenceType.text = getString(RecurrenceType.stringToRecurrenceType(transaction.recurrence.type).text)
            transactionRecurrenceStart.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(transaction.recurrence.start)
            transactionRecurrenceEnd.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(transaction.recurrence.end)
        }

        transactionCancelButton.setOnClickListener {
            //TODO return to home
        }

        transactionValidateButton.setOnClickListener {
            //TODO send transaction to firebase and return to home
        }

        return view
    }
}