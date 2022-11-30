package com.github.ameen.crm.domain.repository

import com.github.ameen.crm.domain.model.CustomerDomain
import kotlinx.coroutines.flow.Flow

interface ICustomerRepository {
    suspend fun getAllCustomer(): Flow<List<CustomerDomain>>
    suspend fun addNewCustomer(customerData: CustomerDomain): Long
    suspend fun updateCustomerActionType(customerId: Int, customerActionType: Int): Int
}