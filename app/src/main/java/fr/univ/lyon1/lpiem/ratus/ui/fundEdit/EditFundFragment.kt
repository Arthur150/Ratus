package fr.univ.lyon1.lpiem.ratus.ui.fundEdit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.univ.lyon1.lpiem.ratus.MainActivity
import fr.univ.lyon1.lpiem.ratus.R
import fr.univ.lyon1.lpiem.ratus.ui.fund_details.UserAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditFundFragment : Fragment() {

    private val viewModel by viewModel<EditFundViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_fund, container, false)

        val titleEditText = view.findViewById<EditText>(R.id.editFundTitleEditText)
        val goalEditText = view.findViewById<EditText>(R.id.editFundGoalEditText)

        val contributorLayout = view.findViewById<ConstraintLayout>(R.id.editFundContributorLayout)

        val contributorsEditText = view.findViewById<EditText>(R.id.editFundContributorIdEditText)
        val addContributorImageView = view.findViewById<ImageView>(R.id.editFundAddContributorImageView)
        val scannerQRCode = view.findViewById<ImageView>(R.id.editFundQRCodeScanner)
        val contributorsRecyclerView = view.findViewById<RecyclerView>(R.id.editFundContributorRecyclerView)

        val cancelButton = view.findViewById<Button>(R.id.editFundCancelButton)
        val validateButton = view.findViewById<Button>(R.id.editFundValidateButton)

        var titleToolBar = getString(R.string.create_fund)
        val fundId = requireArguments().getString("fundId")
        Log.d("Salope", "onCreateView:${fundId} ")

        if (fundId != null && fundId.isNotEmpty()){
            titleToolBar = getString(R.string.edit_fund)
            contributorLayout.visibility = View.GONE
            viewModel.loadFund(fundId)
        } else {
            contributorLayout.visibility = View.VISIBLE
            viewModel.loadUser()
            viewModel.getUser().observe(viewLifecycleOwner) { user ->
                viewModel.addUserInContributors(user.uid)
            }
        }

        (activity as MainActivity).supportActionBar?.apply {
            title = titleToolBar
            setDisplayHomeAsUpEnabled(true)
        }

        viewModel.getError().observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        }

        viewModel.getFund().observe(viewLifecycleOwner) { fund ->
            titleEditText.setText(fund.title)
            goalEditText.setText(fund.goal.toString())
        }

        contributorsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        contributorsRecyclerView.adapter = UserAdapter(emptyList())

        viewModel.getContributors().observe(viewLifecycleOwner) { contributors ->
            (contributorsRecyclerView.adapter as UserAdapter).updateValue(contributors)
        }

        addContributorImageView.setOnClickListener {
            if (contributorsEditText.text.toString().isNotEmpty()){
                viewModel.addUserInContributors(contributorsEditText.text.toString())
                contributorsEditText.text.clear()
            }
        }

        cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }

        validateButton.setOnClickListener {
            if (titleEditText.text.toString().isNotEmpty() && goalEditText.text.toString().isNotEmpty() && !viewModel.getContributors().value.isNullOrEmpty()) {
                viewModel.sendFund(titleEditText.text.toString(),goalEditText.text.toString().toDouble())
                viewModel.getFund().observe(viewLifecycleOwner) { fund ->
                    val bundle = bundleOf("fundId" to fund.id)
                    if (fundId.isNullOrEmpty()) {
                        findNavController().navigate(R.id.action_editFundFragment_to_fundFragment,bundle)
                    } else {
                        findNavController().popBackStack()
                    }
                }
            }
        }

        return view
    }
}