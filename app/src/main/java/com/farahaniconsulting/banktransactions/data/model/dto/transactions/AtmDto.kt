package com.farahaniconsulting.banktransactions.data.model.dto.transactions

data class AtmDto(
    val address: String,
    val id: String,
    val location: LocationDto,
    val name: String
)