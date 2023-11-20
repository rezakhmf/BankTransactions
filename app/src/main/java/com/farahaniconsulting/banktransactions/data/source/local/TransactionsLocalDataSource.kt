package com.farahaniconsulting.banktransactions.data.source.local

import com.farahaniconsulting.banktransactions.data.model.dto.transactions.TransactionDto
import com.farahaniconsulting.banktransactions.data.source.transaction.TransactionDataSource

class TransactionsLocalDataSource() : TransactionDataSource {
    override suspend fun getTransactions(
        transactionDto: TransactionDto,
        bsb: String,
        accountNumber: String
    ): TransactionDto {
        TODO("Not yet implemented")
    }
}