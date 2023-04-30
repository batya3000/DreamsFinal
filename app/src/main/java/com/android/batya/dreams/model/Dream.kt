package com.android.batya.dreams.model

import com.google.firebase.firestore.PropertyName
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

data class Dream (
    var id: String = UUID.randomUUID().toString(),
    var title: String = "",
    var description: String = "",
    var date: Long = LocalDate.now().toEpochDay(),
    var time: Long = LocalTime.now().toNanoOfDay(),
    var mood: Mood = Mood.NOT_SELECTED,
    var lucidity: Lucidity = Lucidity.NOT_SELECTED,
    var tags: List<Tag> = listOf<Tag>(),
)