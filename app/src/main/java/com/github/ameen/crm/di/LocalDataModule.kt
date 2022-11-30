package com.github.ameen.crm.di

import android.app.Application
import androidx.room.Room
import com.github.ameen.crm.R
import com.github.ameen.crm.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(application: Application) = Room.databaseBuilder(
        application.applicationContext,
        AppDatabase::class.java,
        application.getString(R.string.app_name)
    ).fallbackToDestructiveMigration().build()
}
