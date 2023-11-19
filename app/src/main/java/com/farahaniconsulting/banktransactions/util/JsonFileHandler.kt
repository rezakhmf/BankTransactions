package com.farahaniconsulting.banktransactions.util

import android.content.Context
import java.nio.charset.Charset
import com.google.gson.Gson
import java.io.IOException

fun getJsonFromAssets(context: Context, fileName: String): String? {
    var json: String? = null
    try {
        val inputStream = context.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        json = String(buffer, Charset.defaultCharset())
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return json
}

fun <T> parseJsonToModel(json: String, clazz: Class<T>): T {
    val gson = Gson()
    return gson.fromJson(json, clazz)
}