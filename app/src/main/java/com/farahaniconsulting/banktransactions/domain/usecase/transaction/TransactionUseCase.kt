package com.farahaniconsulting.banktransactions.domain.usecase.transaction

import com.farahaniconsulting.banktransactions.data.model.dto.transactions.AccountTransactionsDto
import com.farahaniconsulting.banktransactions.data.model.dto.transactions.toTransaction
import com.farahaniconsulting.banktransactions.data.repository.transaction.TransactionMapperRepository
import com.farahaniconsulting.banktransactions.domain.model.Transactions
import com.farahaniconsulting.banktransactions.util.ResultData
import javax.inject.Inject

class TransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val transactionMapperRepository: TransactionMapperRepository
) {
    suspend fun getTransactions(): ResultData<Transactions?> {
        transactionMapperRepository.readFile("exercise.json")?.let { json ->
            return ResultData.Success(
                transactionMapperRepository.convertJsonToClass(json, AccountTransactionsDto::class.java)?.toTransaction()
            )
        }

        //transactionMapperRepository.getTransactions().account.accountName
        //transactionMapperRepository.getTransactions().account.accountNumber
        return ResultData.DoNothing
    }
}