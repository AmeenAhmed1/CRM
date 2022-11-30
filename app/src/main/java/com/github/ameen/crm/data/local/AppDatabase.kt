package com.github.ameen.crm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.ameen.crm.data.local.dao.CustomerDao
import com.github.ameen.crm.data.local.entity.CustomerEntity

@Database(
    entities = [CustomerEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao
}