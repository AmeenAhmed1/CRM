package com.github.ameen.crm.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.ameen.crm.data.local.entity.CustomerEntity

@Dao
interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewCustomer(customerEntityData: CustomerEntity): Long

    @Query("UPDATE CustomerTable SET customerActionType = :customerType WHERE customerId = :customerId")
    suspend fun changeCustomerActionType(customerId: Int, customerType: Int): Int

    @Query("SELECT * FROM CustomerTable")
    suspend fun getAllCustomer(): List<CustomerEntity>

}