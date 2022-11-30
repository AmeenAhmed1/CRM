package com.github.ameen.crm.presentation

sealed class DataState<out T : Any> {
    object Loading : DataState<Nothing>()
    class Success<out T : Any>(val data: T) : DataState<T>()
    class Error(val error: String) : DataState<Nothing>()
}
