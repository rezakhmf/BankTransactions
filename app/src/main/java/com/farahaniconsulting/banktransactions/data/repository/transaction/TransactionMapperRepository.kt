package com.farahaniconsulting.banktransactions.data.repository.transaction

import android.content.Context
import com.farahaniconsulting.banktransactions.data.model.dto.transactions.AccountTransactionsDto
import com.farahaniconsulting.banktransactions.util.getJsonFromAssets
import com.farahaniconsulting.banktransactions.util.parseJsonToModel

class TransactionMapperRepository(private val context: Context) {
    fun getTransactions() =
         getJsonFromAssets(context, "exercise.json")?.let { parseJsonToModel(it, AccountTransactionsDto::class.java) }
}