package com.github.ameen.crm.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CustomerTable")
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true)
    val customerId: Int,
    val customerName: String?,
    val customerActionType: Int
)
