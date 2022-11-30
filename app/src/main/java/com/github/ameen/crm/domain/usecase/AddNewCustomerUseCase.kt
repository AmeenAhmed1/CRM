package com.github.ameen.crm.domain.usecase

import com.github.ameen.crm.domain.model.CustomerDomain
import com.github.ameen.crm.domain.repository.ICustomerRepository
import com.github.ameen.crm.presentation.DataState
import javax.inject.Inject

class AddNewCustomerUseCase @Inject constructor(private val repository: ICustomerRepository) {
    suspend fun execute(customerData: CustomerDomain): DataState<Long> {
        return DataState.Success(repository.addNewCustomer(customerData))
    }
}