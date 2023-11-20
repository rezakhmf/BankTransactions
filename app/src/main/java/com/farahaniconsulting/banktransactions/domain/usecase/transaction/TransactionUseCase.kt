package com.farahaniconsulting.banktransactions.domain.usecase.transaction

import com.farahaniconsulting.banktransactions.data.model.dto.transactions.toTransaction
import com.farahaniconsulting.banktransactions.data.repository.transaction.TransactionMapperRepository
import com.farahaniconsulting.banktransactions.domain.model.Transactions
import com.farahaniconsulting.banktransactions.util.FileHelperRepository
import com.farahaniconsulting.banktransactions.util.ResultData

class TransactionUseCase(
    private val fileHelperRepository: FileHelperRepository,
    private val transactionRepository: TransactionRepository,
    private val transactionMapperRepository: TransactionMapperRepository
) {
    suspend fun getTransactions(): ResultData<Transactions?> {
        fileHelperRepository.readFile("exercise.json")?.let {

            return ResultData.Success(
                transactionMapperRepository.convertJsonToClass(it).toTransaction()
            )
        }

        //transactionMapperRepository.getTransactions().account.accountName
        //transactionMapperRepository.getTransactions().account.accountNumber
        return ResultData.DoNothing
    }
}