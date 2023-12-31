package com.farahaniconsulting.banktransactions.data.repository.transaction

import android.content.Context
import android.util.Log
import com.farahaniconsulting.banktransactions.dispatcher.DispatcherProvider
import com.google.gson.Gson
import kotlinx.coroutines.withContext
import java.io.IOException

interface TransactionMapperRepository {
    suspend fun readFile(fileName: String): String?
    suspend fun <T> convertJsonToClass(json: String, clazz: Class<T>): T?
}

class TransactionMapperRepositoryImpl(
    private val context: Context,
    private val dispatcher: DispatcherProvider,
) : TransactionMapperRepository {
    override suspend fun readFile(fileName: String): String ?{
        return try {
            withContext(dispatcher.io) {
                context.assets.open(fileName).bufferedReader().use {
                    it.readText()
                }
            }
        } catch (e: IOException) {
            Log.e(e.message ,e.printStackTrace().toString())
            return null
        }
    }

    override suspend fun <T> convertJsonToClass(json: String, clazz: Class<T>): T? {
        return try {
            Gson().fromJson(json, clazz)
        } catch (e: Exception) {
            Log.e(e.message ,e.printStackTrace().toString())
            return null
        }
    }
}