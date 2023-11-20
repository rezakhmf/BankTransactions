package com.farahaniconsulting.banktransactions.di.transaction

import com.farahaniconsulting.banktransactions.dispatcher.AppDispatcher
import com.farahaniconsulting.banktransactions.dispatcher.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Singleton
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider
        = AppDispatcher()
}