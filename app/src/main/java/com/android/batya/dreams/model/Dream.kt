package com.android.batya.dreams.model

import java.time.LocalDate
import java.time.LocalTime

data class Dream (
    var id: String = "",
    var title: String = "",
    var description: String = "",
    var date: LocalDate = LocalDate.now(),
    var time: LocalTime = LocalTime.now(),
    var mood: Mood = Mood.NOT_SELECTED,
    var isLucid: Lucidity = Lucidity.NOT_SELECTED,
    var tags: List<Tag> = listOf<Tag>(),
)