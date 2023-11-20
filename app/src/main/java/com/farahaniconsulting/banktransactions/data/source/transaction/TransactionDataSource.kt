package com.farahaniconsulting.banktransactions.data.source.transaction

import com.farahaniconsulting.banktransactions.data.model.dto.transactions.TransactionDto

interface TransactionDataSource {

    suspend fun getTransactions(
        transactionDto: TransactionDto,
        bsb: String,
        accountNumber: String
    ): TransactionDto
}