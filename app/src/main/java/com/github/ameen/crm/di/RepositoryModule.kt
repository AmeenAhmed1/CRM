package com.github.ameen.crm.di


import com.github.ameen.crm.data.local.AppDatabase
import com.github.ameen.crm.data.repository.CustomerRepo
import com.github.ameen.crm.domain.repository.ICustomerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideCustomerRepository(localDb: AppDatabase) =
        CustomerRepo(localDb) as ICustomerRepository

}