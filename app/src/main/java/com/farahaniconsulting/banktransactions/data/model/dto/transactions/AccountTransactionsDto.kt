package com.farahaniconsulting.banktransactions.data.model.dto.transactions

import com.farahaniconsulting.banktransactions.data.model.dto.account.AccountDto
import com.farahaniconsulting.banktransactions.domain.model.Transaction
import com.farahaniconsulting.banktransactions.domain.model.Transactions

data class AccountTransactionsDto(
    val account: AccountDto,
    val atms: List<AtmDto>?,
    val transactions: List<TransactionDto>
)

internal fun AccountTransactionsDto.toTransaction() = Transactions(
        account = account,
        transactions = transactions.map { transaction -> Transaction(
            amount = transaction.amount,
            category = transaction.category,
            description = transaction.description,
            effectiveDate = transaction.effectiveDate,
            id = transaction.id,
            isPending = transaction.isPending
        ) }
    )