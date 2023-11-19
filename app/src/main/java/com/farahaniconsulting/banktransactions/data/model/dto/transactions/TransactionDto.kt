package com.farahaniconsulting.banktransactions.data.model.dto.transactions

data class TransactionDto(
    val amount: String,
    val atmId: String,
    val category: String,
    val description: String,
    val effectiveDate: String,
    val id: String,
    val isPending: Boolean
)