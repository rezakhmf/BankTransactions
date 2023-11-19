package com.farahaniconsulting.banktransactions.domain.usecase.transaction

import com.farahaniconsulting.banktransactions.data.model.dto.transactions.AccountTransactionsDto

interface TransactionRepository {

    /**
     * Get Transactions by account
     */
    suspend fun getTransactions(accountNumber: String, bsb: String) : AccountTransactionsDto
}