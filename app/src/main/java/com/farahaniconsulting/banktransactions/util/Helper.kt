package com.farahaniconsulting.banktransactions.util

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.util.Log
import java.util.Locale

sealed class ResultData<out T> {
    object DoNothing: ResultData<Nothing>()
    object Loading: ResultData<Nothing>()
    data class Success<T>(val data: T? = null): ResultData<T>()
}

fun getDaysDifference(dateString: String, locale: Locale = Locale.getDefault()): String? {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", locale)

    return try {
        val inputDate = dateFormat.parse(dateString)
        val currentDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"))

        inputDate?.let {
            val inputCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            inputCalendar.time = it

            val differenceInMillis = inputCalendar.timeInMillis - currentDate.timeInMillis
            val differenceInDays = differenceInMillis / (24 * 60 * 60 * 1000)
            val daysBeforeString = when {
                differenceInDays == 0L -> "today"
                differenceInDays == 1L -> "tomorrow"
                differenceInDays == -1L -> "yesterday"
                differenceInDays > 0L -> "$differenceInDays days from now"
                else -> "${-differenceInDays} days ago"
            }

            return daysBeforeString
        }
    } catch (e: Exception) {
        Log.e(e.toString(), e.printStackTrace().toString())
       return ""
    }
}


