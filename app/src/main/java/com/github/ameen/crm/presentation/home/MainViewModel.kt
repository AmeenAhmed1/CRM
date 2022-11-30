package com.github.ameen.crm.presentation.home

import androidx.lifecycle.ViewModel
import com.github.ameen.crm.domain.model.CustomerDomain
import com.github.ameen.crm.domain.usecase.AddNewCustomerUseCase
import com.github.ameen.crm.presentation.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addNewCustomerUseCase: AddNewCustomerUseCase
) : ViewModel() {

    private val coroutineJob: Job = Job()
    private val coroutineDispatcher = Dispatchers.IO
    private val coroutineContext: CoroutineContext = coroutineJob + coroutineDispatcher

    fun addNewCustomer(customerDomain: CustomerDomain) =
        flow {

            emit(DataState.Loading)

            val result = withContext(coroutineContext) {
                addNewCustomerUseCase.execute(customerDomain)
            }

            emit(result)
        }
}