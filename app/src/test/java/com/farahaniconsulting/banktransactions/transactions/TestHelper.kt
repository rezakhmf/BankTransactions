package com.farahaniconsulting.banktransactions.transactions

import com.farahaniconsulting.banktransactions.data.model.dto.account.AccountDto
import com.farahaniconsulting.banktransactions.data.model.dto.transactions.AccountTransactionsDto
import com.farahaniconsulting.banktransactions.data.model.dto.transactions.TransactionDto
import com.farahaniconsulting.banktransactions.domain.model.Transaction
import com.farahaniconsulting.banktransactions.domain.model.Transactions

fun accountDtoTestProvider() = AccountDto(
    bsb = "062005",
    accountNumber = "17095888",
    balance = "246.7",
    available = "226.76",
    accountName = "Complete Access"
)

fun transactionsDtoTestProvider(): AccountTransactionsDto {

    val transaction1 = TransactionDto(
        id = "B829C8C5-4F1F-46F7-87A2-090C23DE7DA9",
        amount = "-14.19",
        category = "shopping",
        isPending = true,
        effectiveDate = "2021-02-28",
        description = "Setapp (via Paddle.Net) +440808178853 GBR",
        atmId = null
    )

    val transaction2 = TransactionDto(
        id = "39062634-DFF7-4E23-B108-8289D07F9F73",
        amount = "-42.69",
        category = "business",
        isPending = true,
        effectiveDate = "2021-02-28",
        description = "Linode Philadelphia PA",
        atmId = null
    )

    val transaction3 = TransactionDto(
        id = "036B11AA-B93A-433E-B05B-42354A6C8BE1",
        amount = "-6.99",
        category = "entertainment",
        isPending = false,
        effectiveDate = "2021-02-27",
        description = "Amazon Prime AU Membership Sydney South NSW",
        atmId = null
    )

    val transaction4 = TransactionDto(
        id = "CEAF2D55-6666-447F-8222-EF0D3E84EC2E",
        amount = "-129.99",
        category = "groceries",
        isPending = false,
        effectiveDate = "2021-02-27",
        description = "Woolworths 1100 Redfern",
        atmId = null
    )

    val transaction5 = TransactionDto(
        id = "6366BDFE-2BD8-4F58-8F2A-C3DC20E938CC",
        amount = "-20.50",
        category = "groceries",
        isPending = false,
        effectiveDate = "2023-02-27",
        description = "Transfer To W SMITH<br/>PayID Phone from CommBank App<br/>Thanks for lunch champ 😋",
        atmId = null
    )

    val transactionsList = mutableListOf<TransactionDto>()
    with(transactionsList) {
        add(transaction1)
        add(transaction2)
        add(transaction3)
        add(transaction4)
        add(transaction5)
    }

    return AccountTransactionsDto(
        account = accountDtoTestProvider(),
        transactions = transactionsList,
        atms = null
    )
}
