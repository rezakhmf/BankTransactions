package com.farahaniconsulting.banktransactions.transactions

import com.farahaniconsulting.banktransactions.data.model.dto.transactions.toTransaction
import com.farahaniconsulting.banktransactions.data.repository.transaction.TransactionMapperRepository
import com.farahaniconsulting.banktransactions.domain.usecase.transaction.TransactionUseCase
import com.farahaniconsulting.banktransactions.util.ResultData
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class TransactionUseCaseTest {

    @Test
    fun `getTransactions should return Success when readFile and convertJsonToClass are successful`() {
        // Arrange
        val mockRepository = mockk<TransactionMapperRepository>()
        val useCase = TransactionUseCase(mockRepository)

        // Mock the behavior of readFile
        coEvery { mockRepository.readFile(any()) } returns "{}"
        coEvery { mockRepository.convertJsonToClass(any(), any<Class<*>>()) } returns transactionsDtoTestProvider()

        // Act
        val result = runBlocking {
            useCase.getTransactions()
        }

        // Assert
        assertEquals(ResultData.Success(transactionsDtoTestProvider().toTransaction()), result)
    }

    @Test
    fun `getTransactions should return DoNothing when readFile returns null`() {
        // Arrange
        val mockRepository = mockk<TransactionMapperRepository>()
        val useCase = TransactionUseCase(mockRepository)

        // Mock the behavior of readFile
        coEvery { mockRepository.readFile(any()) } returns null

        // Act
        val result = runBlocking {
            useCase.getTransactions()
        }

        // Assert
        assertEquals(ResultData.DoNothing, result)
    }

    @Test
    fun `getTransactions should return DoNothing when convertJsonToClass returns null`() {
        // Arrange
        val mockRepository = mockk<TransactionMapperRepository>()
        val useCase = TransactionUseCase(mockRepository)

        // Mock the behavior of readFile and convertJsonToClass
        coEvery { mockRepository.readFile(any()) } returns "{}"
        coEvery { mockRepository.convertJsonToClass(any(), any<Class<*>>()) } returns null

        // Act
        val result = runBlocking {
            useCase.getTransactions()
        }

        // Assert
        assertEquals(ResultData.DoNothing, result)
    }
}