package com.android.batya.dreams.model

import com.android.batya.dreams.model.Dream
import java.util.*

data class User(
    val id: String? = null,
    val email: String? = null,
    val displayName: String? = null,
    var dreams: Map<String?, Dream?> = mapOf()
)
