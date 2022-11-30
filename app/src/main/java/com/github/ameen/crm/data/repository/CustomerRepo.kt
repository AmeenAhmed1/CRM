package com.github.ameen.crm.data.repository

import com.github.ameen.crm.data.local.AppDatabase
import com.github.ameen.crm.data.mapper.toDomain
import com.github.ameen.crm.data.mapper.toEntity
import com.github.ameen.crm.domain.model.CustomerDomain
import com.github.ameen.crm.domain.repository.ICustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CustomerRepo @Inject constructor(
    private val localDb: AppDatabase
) : ICustomerRepository {

    override suspend fun getAllCustomer(): Flow<List<CustomerDomain>> {
        return flow {
            localDb.customerDao().getAllCustomer().map { it.toDomain() }
        }
    }

    override suspend fun addNewCustomer(customerData: CustomerDomain): Long {
        return localDb.customerDao().addNewCustomer(customerData.toEntity())
    }

    override suspend fun updateCustomerActionType(customerId: Int, customerActionType: Int): Int {
        return localDb.customerDao().changeCustomerActionType(customerId, customerActionType)
    }
}