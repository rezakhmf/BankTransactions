package com.farahaniconsulting.banktransactions.data.model.dto.transactions

import com.farahaniconsulting.banktransactions.data.model.dto.account.AccountDto

data class AccountTransactionsDto(
    val account: AccountDto,
    val atms: List<AtmDto>?,
    val transactions: List<TransactionDto>
)