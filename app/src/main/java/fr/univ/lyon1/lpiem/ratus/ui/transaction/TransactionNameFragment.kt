package fr.univ.lyon1.lpiem.ratus.ui.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.Timestamp
import fr.univ.lyon1.lpiem.ratus.MainActivity
import fr.univ.lyon1.lpiem.ratus.R
import fr.univ.lyon1.lpiem.ratus.model.Recurrence
import fr.univ.lyon1.lpiem.ratus.model.Transaction
import fr.univ.lyon1.lpiem.ratus.model.TransactionCategory
import java.util.*

class TransactionNameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transaction_name, container, false)

        (activity as MainActivity).supportActionBar?.apply {
            title = getString(R.string.new_transaction)
            setDisplayHomeAsUpEnabled(true)
        }

        val nextButton = view.findViewById<Button>(R.id.transactionNameNextbutton)
        val nameEditText = view.findViewById<EditText>(R.id.transactionNameEditText)

        nextButton.setOnClickListener {
            if (nameEditText.text.isNotEmpty()){
                val transaction = Transaction(nameEditText.text.toString(),
                    requireArguments().getDouble("amount"),
                    (requireArguments().get("category") as TransactionCategory).toFirestoreString(),
                    Timestamp(requireArguments().getSerializable("transactionDate") as Date),
                    requireArguments().getSerializable("recurrence") as Recurrence?
                )
                val bundle = Bundle()
                bundle.putSerializable("transaction", transaction.toParcelableHashMap())
                findNavController().navigate(R.id.action_transactionNameFragment_to_transactionSummaryFragment, bundle)
            } else {
                Toast.makeText(requireContext(),R.string.please_complete_all_fields, Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}