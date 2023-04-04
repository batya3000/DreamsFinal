package com.android.batya.dreams.navigation.graphs


import android.annotation.SuppressLint

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.batya.dreams.navigation.DreamScreens
import com.android.batya.dreams.navigation.Graph
import com.android.batya.dreams.screens.*
import com.android.batya.dreams.screens.journal.JournalScreen
import com.android.batya.dreams.screens.profile.ProfileScreen
import com.android.batya.dreams.screens.search.SearchScreen
import com.android.batya.dreams.screens.stats.StatisticsScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController,
        route = Graph.HOME,
        startDestination = DreamScreens.Journal.route
    ) {
        editDreamNavGraph(navController)
        composable(route = DreamScreens.Journal.route) {
            JournalScreen()
        }
        composable(route = DreamScreens.Statistics.route) {
            StatisticsScreen()
        }
        composable(route = DreamScreens.Search.route) {
            SearchScreen()
        }
        composable(route = DreamScreens.Profile.route) {
            ProfileScreen()
        }
    }
}
