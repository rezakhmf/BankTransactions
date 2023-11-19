package com.farahaniconsulting.banktransactions.data.model.dto.account

data class AccountDto(
    val accountName: String,
    val accountNumber: String,
    val available: String,
    val balance: String,
    val bsb: String
)