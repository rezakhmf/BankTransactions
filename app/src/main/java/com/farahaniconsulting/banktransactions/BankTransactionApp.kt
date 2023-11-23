package com.farahaniconsulting.banktransactions

import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BankTransactionApp : BankTransactionsCoreApplication() {
    override fun onCreate() {
        super.onCreate()
    }
}