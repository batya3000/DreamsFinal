package com.android.batya.dreams.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

const val DREAM_ID_ARGUMENT_KEY = "dream_id"

sealed class DreamScreens(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {

//        Bottom Bar Screens

    object Journal : DreamScreens(
        "journal",
        "Journal",
        Icons.Default.FormatListBulleted
    )
    object Statistics : DreamScreens(
        "statistics",
        "Stats",
        Icons.Default.BarChart
    )
    object Search : DreamScreens(
        "search",
        "Search",
        Icons.Default.Search
    )
    object Profile : DreamScreens(
        "profile",
        "Profile",
        Icons.Default.Person
    )

//    Edit Dream Screens

    object General : DreamScreens(route = "general?dreamId={$DREAM_ID_ARGUMENT_KEY}") {
        fun passDreamId(id: String = "default"): String {
            return "general?dreamId=$id"
        }
    }

    object Mood : DreamScreens(route = "mood")
    object Lucid : DreamScreens(route = "lucid")

//    Auth Screens

    object Login: DreamScreens(route = "login")

}


