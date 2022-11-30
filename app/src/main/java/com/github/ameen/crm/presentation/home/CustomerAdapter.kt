package com.github.ameen.crm.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.ameen.crm.R
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
            ActionType.CALL.type -> actionTypeText =
                holder.binding.root.resources.getString(R.string.call)
            ActionType.VISIT.type -> actionTypeText =
                holder.binding.root.resources.getString(R.string.visit)
            ActionType.FOLLOW_UP.type -> actionTypeText =
                holder.binding.root.resources.getString(R.string.follow_up)
        }

        holder.binding.customerAction.text = actionTypeText

        holder.binding.editCustomer.setOnClickListener {
            onItemClickListener?.invoke(currentItem, false)
        }

        holder.binding.deleteButton.setOnClickListener {
            onItemClickListener?.invoke(currentItem, true)
        }
    }

    override fun getItemCount(): Int = diff.currentList.size

    private var onItemClickListener: ((CustomerDomain, Boolean) -> Unit)? = null
    fun onItemClicked(listener: (CustomerDomain, Boolean) -> Unit) {
        onItemClickListener = listener
    }
}