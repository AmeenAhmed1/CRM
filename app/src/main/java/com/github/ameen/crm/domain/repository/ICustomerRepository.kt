package com.github.ameen.crm.domain.repository

import com.github.ameen.crm.domain.model.CustomerDomain

interface ICustomerRepository {
    suspend fun getAllCustomer(): List<CustomerDomain>
    suspend fun addNewCustomer(customerData: CustomerDomain): Long
    suspend fun deleteCustomer(customerData: CustomerDomain)
}