package com.madbox.dazntest.app.utils

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import java.util.Date
import java.util.Locale

object DateConverterHelper {

    private const val SERVER_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    private const val SIMPLIFIED_DATE_FORMAT = "dd.MM.yyyy"

    fun convertToTimestamp(dateString: String): Long {
        val dateFormat = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.getDefault())
        val date: Date =
            dateFormat.parse(dateString) ?: throw IllegalArgumentException("Invalid date format")

        return date.time
    }


    @SuppressLint("SimpleDateFormat")
    fun formatTimestamp(timestamp: Long): String {
        val date = Date(timestamp)
        return if (isToday(date)) {
            "Today, ${getTimeInHHMM(date)}"
        } else if (isYesterday(date)) {
            "Yesterday, ${getTimeInHHMM(date)}"
        } else {
            changeDateFormat(SimpleDateFormat(SERVER_DATE_FORMAT).format(date))
        }
    }

    private fun changeDateFormat(originalDate: String): String {
        val originalFormat = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.getDefault())
        val date: Date = originalFormat.parse(originalDate)
            ?: throw IllegalArgumentException("Invalid date format")

        val newFormat = SimpleDateFormat(SIMPLIFIED_DATE_FORMAT, Locale.getDefault())
        return newFormat.format(date)
    }

    private fun getTimeInHHMM(date: Date): String {
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return timeFormat.format(date)
    }

    private fun isYesterday(dateToCheck: Date): Boolean {
        val calendar = Calendar.getInstance()

        calendar.add(Calendar.DAY_OF_YEAR, -1)
        val yesterday = calendar.time

        return SimpleDateFormat(SIMPLIFIED_DATE_FORMAT, Locale.getDefault()).format(dateToCheck) ==
                SimpleDateFormat(SIMPLIFIED_DATE_FORMAT, Locale.getDefault()).format(yesterday)
    }

    private fun isToday(dateToCheck: Date): Boolean {
        val calendar = Calendar.getInstance()

        val tomorrow = calendar.time

        return SimpleDateFormat(SIMPLIFIED_DATE_FORMAT, Locale.getDefault()).format(dateToCheck) ==
                SimpleDateFormat(SIMPLIFIED_DATE_FORMAT, Locale.getDefault()).format(tomorrow)
    }
}



