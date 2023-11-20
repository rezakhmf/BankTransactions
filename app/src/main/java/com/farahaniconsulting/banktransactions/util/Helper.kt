package com.farahaniconsulting.banktransactions.util

sealed class ResultData<out T> {
    object DoNothing: ResultData<Nothing>()
    object Loading: ResultData<Nothing>()
    data class Success<T>(val data: T? = null): ResultData<T>()

}

