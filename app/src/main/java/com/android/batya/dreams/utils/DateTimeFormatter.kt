package com.android.batya.dreams.utils

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter


fun getFormattedDate(year: Int, month: Int, dayOfMonth: Int): String {
    return LocalDate.of(year, month + 1, dayOfMonth).format(DateTimeFormatter.ofPattern("dd MMMM yyyy г."))
}
fun getFormattedDate(localDate: LocalDate): String {
    return localDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy г."))
}

fun getShortFormattedDate(year: Int, month: Int, dayOfMonth: Int): String {
    return LocalDate.of(year, month + 1, dayOfMonth).format(DateTimeFormatter.ofPattern("d MMMM"))
}
fun getShorFormattedDate(localDate: LocalDate): String {
    return localDate.format(DateTimeFormatter.ofPattern("d MMMM"))
}

fun parseDateFromString(date: String): LocalDate {
    return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd MMMM yyyy г."))
}
fun parseTimeFromString(time: String): LocalTime {
    return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"))
}


fun getFormattedTime(hours: Int, minutes: Int): String
    = when {
        minutes < 10 && hours < 10 -> "0$hours:0$minutes"
        minutes < 10 -> "$hours:0$minutes"
        hours < 10 -> "0$hours:$minutes"
        else -> "$hours:$minutes"
    }
fun getFormattedTime(localTime: LocalTime): String {
        return localTime.format(DateTimeFormatter.ofPattern("HH:mm"))
}