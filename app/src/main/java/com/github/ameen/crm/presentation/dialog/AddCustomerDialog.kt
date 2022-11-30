package com.github.ameen.crm.presentation.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.github.ameen.crm.R
import com.github.ameen.crm.databinding.DialogAddCustomerBinding
import com.github.ameen.crm.domain.model.CustomerDomain
import com.github.ameen.crm.presentation.util.ActionType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCustomerDialog(
    private val customerData: CustomerDomain? = null,
    private val confirmAddCustomer: ((CustomerDomain) -> Unit)?
) : DialogFragment() {

//    constructor() : this(isFromHome, null)

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private var _binding: DialogAddCustomerBinding? = null
    private val binding get() = _binding!!

    private lateinit var actionType: ActionType

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DialogAddCustomerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (customerData != null)
            initView()

        binding.saveButton.setOnClickListener {

            val customerName = binding.customerNameText.text.toString()

            if (this::actionType.isInitialized && customerName.isNotEmpty()) {
                if (customerData != null)
                    confirmAddCustomer?.invoke(
                        CustomerDomain(
                            customerId = customerData.customerId,
                            customerName = customerName,
                            customerActionType = actionType.type
                        )
                    )
                else
                    confirmAddCustomer?.invoke(
                        CustomerDomain(
                            customerName = customerName,
                            customerActionType = actionType.type
                        )
                    )
                this.dismiss()
            } else
                Toast.makeText(requireContext(), "Add Valid Data", Toast.LENGTH_SHORT).show()
        }

        binding.radioActionGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            when (checkedId) {
                R.id.radio_call -> actionType = ActionType.CALL
                R.id.radio_visit -> actionType = ActionType.VISIT
                R.id.radio_followup -> actionType = ActionType.FOLLOW_UP
            }
        }

    }

    private fun initView() {
        binding.customerNameText.setText(customerData?.customerName ?: "")

        when (customerData?.customerActionType) {
            0 -> {
                binding.radioActionGroup.check(R.id.radio_call)
                actionType = ActionType.CALL
            }
            1 -> {
                binding.radioActionGroup.check(R.id.radio_visit)
                actionType = ActionType.VISIT
            }
            2 -> {
                binding.radioActionGroup.check(R.id.radio_followup)
                actionType = ActionType.FOLLOW_UP
            }
        }
    }
}