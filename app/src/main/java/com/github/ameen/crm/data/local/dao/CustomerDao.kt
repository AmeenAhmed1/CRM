package com.github.ameen.crm.data.local.dao

import androidx.room.*
import com.github.ameen.crm.data.local.entity.CustomerEntity

@Dao
interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewCustomer(customerEntityData: CustomerEntity): Long

    @Delete
    suspend fun deleteCustomer(customerData: CustomerEntity)

    @Query("SELECT * FROM CustomerTable")
    suspend fun getAllCustomer(): List<CustomerEntity>

}