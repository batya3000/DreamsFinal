package com.android.batya.dreams.data

data class DataOrException<T, Boolean, E : Exception?>(
    var data: T? = null,
    var loading: Boolean? = null,
    var e: E? = null
)