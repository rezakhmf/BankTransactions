package com.farahaniconsulting.banktransactions.util

import android.icu.text.SimpleDateFormat
import android.util.Log
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

sealed class ResultData<out T> {
    object DoNothing : ResultData<Nothing>()
    object Loading : ResultData<Nothing>()
    data class Success<T>(val data: T? = null) : ResultData<T>()
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


fun formatMoneyAmount(amount: String): String {
    val amountAsDouble = amount.toDoubleOrNull() ?: 0.0
    return "$${String.format("%.2f", amountAsDouble)}"
}

fun splitStringIntoGroups(input: String, groupSize: Int = 4): String {
    return input.chunked(groupSize).joinToString(" ")
}


fun convertDateFormat(inputDate: String): String {
    return try {
        val originalDate = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(inputDate)
        val calendar = Calendar.getInstance()
        calendar.time = originalDate

        val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.ENGLISH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH)

        "$dayOfWeek $dayOfMonth $month"
    } catch (e: Exception) {
        Log.e(e.message, e.printStackTrace().toString())
        ""
    }
}

fun removeHtmlTags(input: String): String {
    return input.replace(Regex("<.*?>"), "")
}

