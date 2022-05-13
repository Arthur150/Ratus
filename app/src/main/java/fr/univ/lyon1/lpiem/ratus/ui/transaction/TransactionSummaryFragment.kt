package fr.univ.lyon1.lpiem.ratus.ui.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.univ.lyon1.lpiem.ratus.MainActivity
import fr.univ.lyon1.lpiem.ratus.R
import fr.univ.lyon1.lpiem.ratus.model.RecurrenceType
import fr.univ.lyon1.lpiem.ratus.model.Transaction
import fr.univ.lyon1.lpiem.ratus.model.TransactionCategory
import fr.univ.lyon1.lpiem.ratus.ui.Tools
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class TransactionSummaryFragment : Fragment() {

    private val tools by inject<Tools>()
    private val transactionViewModel by viewModel<TransactionViewModel>()

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

        val map = requireArguments().getSerializable("transaction") as HashMap<String, Any?>
        transactionViewModel.transaction = Transaction.fromParcelableHashMap(map)

        val alertDialog = AlertDialog.Builder(requireContext())
            .setView(ProgressBar(requireContext()))
            .setMessage(getString(R.string.loading))
            .create()

        transactionName.text = transactionViewModel.transaction.title
        transactionAmount.apply {
            text = tools.formatCurrency(transactionViewModel.transaction.amount)
            setTextColor(tools.getCurrencyTextColor(requireContext(),transactionViewModel.transaction.amount))
        }

        transactionCategory.text =  "${getString(R.string.category)} : ${getString(TransactionCategory.stringToTransactionCategory(transactionViewModel.transaction.category).text)}"

        if (transactionViewModel.transaction.recurrence == null){
            transactionDateRecurrenceLabel.text = getString(R.string.transaction_date)
            transactionRecurrenceLayout.visibility = View.GONE
            transactionDate.visibility = View.VISIBLE
            transactionDate.text =
                transactionViewModel.transaction.date?.toDate()
                    ?.let { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(it) }

        } else {
            transactionDateRecurrenceLabel.text = getString(R.string.recurrence)
            transactionDate.visibility = View.GONE
            transactionRecurrenceType.text = transactionViewModel.transaction.recurrence?.type?.let {
                RecurrenceType.stringToRecurrenceType(
                    it
                ).text
            }?.let { getString(it) }
            transactionRecurrenceStart.text = "${getString(R.string.from_the)} : ${
                transactionViewModel.transaction.recurrence?.start?.toDate()
                    ?.let { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(it) }
            }"

            transactionRecurrenceEnd.text = "${getString(R.string.until)} : ${
                transactionViewModel.transaction.recurrence?.end?.toDate()
                    ?.let { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(it) }
            }"

        }

        transactionCancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_transactionSummaryFragment_to_homePageFragment)
        }

        transactionValidateButton.setOnClickListener {
            transactionViewModel.sendTransaction()
            alertDialog.show()
        }

        transactionViewModel.getUser().observe(viewLifecycleOwner){
            alertDialog.dismiss()
            findNavController().navigate(R.id.action_transactionSummaryFragment_to_homePageFragment)
        }

        return view
    }
}