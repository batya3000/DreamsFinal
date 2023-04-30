package com.android.batya.dreams.navigation

import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.android.batya.dreams.R

const val DREAM_ID_ARGUMENT_KEY = "dream_id"
const val DREAM_OPENED_FROM_DETAILS_ARGUMENT_KEY = "id"
const val SEARCH_QUERY_ARGUMENT_KEY = "query"


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

    object Home: DreamScreens(route = "home")


//    Edit Dream Screens

    object DreamEdit : DreamScreens(route = "dreamEdit?id={$DREAM_ID_ARGUMENT_KEY}&isOpenedFromDetails={$DREAM_OPENED_FROM_DETAILS_ARGUMENT_KEY}") {
        fun passArguments(id: String = "", isOpenedFromDetails: Boolean = false): String {
            return "dreamEdit?id=$id&isOpenedFromDetails=$isOpenedFromDetails"
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
        Icons.Default.NightlightRound
    )


//    Auth Screens

    object SignIn: DreamScreens(route = "signIn")

//    Dream Details Screen
    object Details: DreamScreens(route = "details?id={$DREAM_ID_ARGUMENT_KEY}") {
        fun passDreamId(id: String = "default"): String {
            return "details?id=$id"
        }
    }
//    Search screens
    object SearchByQuery: DreamScreens(route = "searchByQuery?query={$SEARCH_QUERY_ARGUMENT_KEY}") {
        fun passQuery(query: String = ""): String {
            return "searchByQuery?query=$query"
        }
    }
    object SearchByDate : DreamScreens(
        "searchByDate",
        "Date",
        //Icons.Default.CalendarMonth
    )
    object SearchByMood : DreamScreens(
        "searchByMood",
        "Mood",
       // Icons.Default.Mood
    )
    object SearchByLucidity : DreamScreens(
        "searchByLucid",
        "Lucid",
        //Icons.Default.NightlightRound
    )
}


