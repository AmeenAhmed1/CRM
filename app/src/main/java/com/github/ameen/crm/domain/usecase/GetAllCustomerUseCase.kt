package com.github.ameen.crm.domain.usecase

import com.github.ameen.crm.domain.model.CustomerDomain
import com.github.ameen.crm.domain.repository.ICustomerRepository
import com.github.ameen.crm.presentation.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCustomerUseCase @Inject constructor(private val repository: ICustomerRepository) {
    suspend fun execute(): DataState.Success<List<CustomerDomain>> {
        return DataState.Success(repository.getAllCustomer())
    }
}