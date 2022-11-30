package com.github.ameen.crm.domain.model

data class CustomerDomain(
    val customerId: Int? = null,
    val customerName: String,
    val customerActionType: Int
)