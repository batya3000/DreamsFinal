package com.android.batya.dreams.utils

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun getFormattedDate(epochDays: Long): String {
    return LocalDate.ofEpochDay(epochDays).format(DateTimeFormatter.ofPattern("dd MMMM yyyy г."))
}
fun getShortFormattedDate(epochDays: Long): String {
    return LocalDate.ofEpochDay(epochDays).format(DateTimeFormatter.ofPattern("d MMMM"))
}

fun getFormattedTime(nanoOfDay: Long): String {
        return LocalTime.ofNanoOfDay(nanoOfDay).format(DateTimeFormatter.ofPattern("HH:mm"))
}