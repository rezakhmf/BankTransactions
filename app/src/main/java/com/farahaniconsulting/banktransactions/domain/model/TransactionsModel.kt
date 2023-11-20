package com.farahaniconsulting.banktransactions.domain.model

import com.farahaniconsulting.banktransactions.data.model.dto.account.AccountDto
import com.farahaniconsulting.banktransactions.data.model.dto.transactions.TransactionDto

data class Transactions (
    val account: AccountDto,
    val transactions: List<Transaction>
)

data class Transaction(
    val amount: String,
    val category: String,
    val description: String,
    val effectiveDate: String,
    val id: String,
    val isPending: Boolean
)