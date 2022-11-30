package com.github.ameen.crm.domain.usecase

import com.github.ameen.crm.domain.model.CustomerDomain
import com.github.ameen.crm.domain.repository.ICustomerRepository
import com.github.ameen.crm.presentation.DataState
import javax.inject.Inject

class DeleteCustomerUseCase @Inject constructor(private val repository: ICustomerRepository) {
    suspend fun execute(customer: CustomerDomain): DataState.Success<Unit> {
        return DataState.Success(repository.deleteCustomer(customer))
    }
}