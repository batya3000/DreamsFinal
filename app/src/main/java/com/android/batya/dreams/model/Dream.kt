package com.android.batya.dreams.model

data class Dream (
    val id: String,
    var title: String = "",
    var description: String = "",
    var date: String = "12 ноября",
    var time: String = "13:40",
    var isLucid: Boolean = false
)