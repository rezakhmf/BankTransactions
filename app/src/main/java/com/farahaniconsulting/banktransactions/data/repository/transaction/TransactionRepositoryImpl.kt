package com.farahaniconsulting.banktransactions.data.repository.transaction

import com.farahaniconsulting.banktransactions.data.model.dto.transactions.AccountTransactionsDto
import com.farahaniconsulting.banktransactions.domain.usecase.transaction.TransactionRepository

class TransactionRepositoryImpl : TransactionRepository {

    /**
     * Get Transactions by account
     */
    override suspend fun getTransactions(
        accountNumber: String,
        bsb: String
    ): AccountTransactionsDto {
        TODO("Not yet implemented")
    }
}