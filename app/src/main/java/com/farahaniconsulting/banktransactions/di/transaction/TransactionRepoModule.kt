package com.farahaniconsulting.banktransactions.di.transaction

import android.content.Context
import com.farahaniconsulting.banktransactions.data.repository.transaction.TransactionMapperRepository
import com.farahaniconsulting.banktransactions.data.repository.transaction.TransactionMapperRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TransactionRepoModule {

    @Singleton
    @Provides
    fun provideTransactionMapperRepository(context: Context) : TransactionMapperRepository =
        TransactionMapperRepositoryImpl(context)
}