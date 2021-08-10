package com.example.orders.ext

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm"
const val TIME_AND_MINUTE = "hh:mm aa"

fun String.toLongDate(): Long {
    return runCatching {
        val simpleDateFormat = SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.getDefault())
        return simpleDateFormat.parse(this)?.time ?: System.currentTimeMillis()
    }.getOrElse {
        System.currentTimeMillis()
    }
}

fun calculateDifferenceInMinutes(timeout: Long) =
    TimeUnit.MILLISECONDS.toMinutes(timeout - System.currentTimeMillis()).toInt()

fun String.toTime(): String {
    val longDate = this.toLongDate()
    val simpleDateFormat = SimpleDateFormat(TIME_AND_MINUTE, Locale.getDefault())
    return simpleDateFormat.format(Date(longDate))
}