package com.android.batya.dreams.navigation.graphs


import android.annotation.SuppressLint
import androidx.compose.material.ExperimentalMaterialApi

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.android.batya.dreams.navigation.DREAM_ID_ARGUMENT_KEY
import com.android.batya.dreams.navigation.DreamScreens
import com.android.batya.dreams.navigation.Graph
import com.android.batya.dreams.screens.*
import com.android.batya.dreams.screens.details.DreamDetailsScreen
import com.android.batya.dreams.screens.journal.JournalScreen
import com.android.batya.dreams.screens.profile.ProfileScreen
import com.android.batya.dreams.screens.search.SearchScreen
import com.android.batya.dreams.screens.stats.StatisticsScreen

@OptIn(ExperimentalMaterialApi::class)
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
            //val journalViewModel: JournalViewModel = hiltViewModel()
            JournalScreen(navController = navController)
//            JournalScreen(
//                navController,
//                journalViewModel,
//            )
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
        composable(
            route = DreamScreens.Details.route,
            arguments = listOf(
                navArgument(DREAM_ID_ARGUMENT_KEY) {
                    type = NavType.StringType
                    defaultValue = "default"
                }
            )
        ) {
            DreamDetailsScreen(
                navController = navController,
                dreamId = it.arguments?.getString(DREAM_ID_ARGUMENT_KEY).toString()
            )
        }
    }
}
