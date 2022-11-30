package com.github.ameen.crm.data.mapper

import com.github.ameen.crm.data.local.entity.CustomerEntity
import com.github.ameen.crm.domain.model.CustomerDomain

fun CustomerEntity.toDomain(): CustomerDomain {
    return CustomerDomain(
        customerId = this.customerId,
        customerName = this.customerName ?: "Not Found",
        customerActionType = this.customerActionType
    )
}

fun CustomerDomain.toEntity(): CustomerEntity {
    return CustomerEntity(
        customerId = this.customerId ?: 0,
        customerName = this.customerName,
        customerActionType = this.customerActionType
    )
}