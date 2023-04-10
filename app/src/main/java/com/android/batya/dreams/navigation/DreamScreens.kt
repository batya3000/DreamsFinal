package com.android.batya.dreams.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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

    object DreamEdit : DreamScreens(route = "dreamEdit?id={$DREAM_ID_ARGUMENT_KEY}") {//?dreamId={$DREAM_ID_ARGUMENT_KEY}") {
        fun passDreamId(id: String = "default"): String {
            return "dreamEdit?id=$id"
        }
    }
    object General : DreamScreens(
        "general",
        "General",
        Icons.Default.EditNote
    )
    object Mood : DreamScreens(
        "mood",
        "Mood",
        Icons.Default.Mood
    )
    object Lucid : DreamScreens(
        "lucid",
        "Lucid",
        Icons.Default.Voicemail
    )


//    Auth Screens

    object Login: DreamScreens(route = "login")

//    Dream Details Screen
    object Details: DreamScreens(route = "details?id={$DREAM_ID_ARGUMENT_KEY}") {
        fun passDreamId(id: String = "default"): String {
            return "details?id=$id"
        }
    }

}


