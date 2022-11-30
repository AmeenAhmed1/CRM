package com.github.ameen.crm.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.ameen.crm.databinding.ItemCustomerBinding
import com.github.ameen.crm.domain.model.CustomerDomain
import com.github.ameen.crm.presentation.util.ActionType

class CustomerAdapter() :
    RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>() {

    inner class CustomerViewHolder(val binding: ItemCustomerBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var _binding: ItemCustomerBinding? = null

    private val differCallBack = object : DiffUtil.ItemCallback<CustomerDomain>() {
        override fun areItemsTheSame(oldItem: CustomerDomain, newItem: CustomerDomain): Boolean {
            return oldItem.customerId == newItem.customerId
        }

        override fun areContentsTheSame(oldItem: CustomerDomain, newItem: CustomerDomain): Boolean {
            return oldItem == newItem
        }
    }

    val diff = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        _binding = ItemCustomerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomerViewHolder(_binding!!)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val currentItem = diff.currentList[position]

        holder.binding.customerName.text = currentItem.customerName

        var actionTypeText = ""
        when (currentItem.customerActionType) {
            ActionType.CALL.type -> actionTypeText = ActionType.CALL.name
            ActionType.VISIT.type -> actionTypeText = ActionType.VISIT.name
            ActionType.CALL.type -> actionTypeText = ActionType.FOLLOW_UP.name
        }

        holder.binding.customerAction.text = actionTypeText
    }

    override fun getItemCount(): Int = diff.currentList.size

    private var onItemClickListener: ((CustomerDomain) -> Unit)? = null
    fun onItemClicked(listener: (CustomerDomain) -> Unit) {
        onItemClickListener = listener
    }
}