package com.android.batya.dreams.navigation.graphs


import android.annotation.SuppressLint

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.android.batya.dreams.navigation.DREAM_ID_ARGUMENT_KEY
import com.android.batya.dreams.navigation.DREAM_OPENED_FROM_DETAILS_ARGUMENT_KEY
import com.android.batya.dreams.navigation.DreamScreens
import com.android.batya.dreams.navigation.SEARCH_QUERY_ARGUMENT_KEY
import com.android.batya.dreams.screens.*
import com.android.batya.dreams.screens.details.DreamDetailsScreen
import com.android.batya.dreams.screens.details.DreamDetailsViewModel
import com.android.batya.dreams.screens.edit.DreamEditScreen
import com.android.batya.dreams.screens.edit.EditDreamViewModel
import com.android.batya.dreams.screens.journal.JournalScreen
import com.android.batya.dreams.screens.journal.JournalScreenViewModel
import com.android.batya.dreams.screens.signin.SignInScreen
import com.android.batya.dreams.screens.profile.ProfileScreen
import com.android.batya.dreams.screens.search.SearchByQueryScreen
import com.android.batya.dreams.screens.search.SearchScreen
import com.android.batya.dreams.screens.stats.StatisticsScreen
import com.android.batya.dreams.screens.stats.StatsScreenViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DreamNavigation(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination =
            if (Firebase.auth.currentUser == null) DreamScreens.SignIn.route
            else DreamScreens.Journal.route
    ) {
        composable(
            route = DreamScreens.DreamEdit.route,
            arguments = listOf(
                navArgument(DREAM_ID_ARGUMENT_KEY) {
                    type = NavType.StringType
                    defaultValue = ""
                },

                navArgument(DREAM_OPENED_FROM_DETAILS_ARGUMENT_KEY) {
                    type = NavType.BoolType
                    defaultValue = false
                }
            )
        ) {
            val viewModel = hiltViewModel<EditDreamViewModel>()
            DreamEditScreen(
                navController = navController,
                viewModel = viewModel,
                dreamId = it.arguments?.getString(DREAM_ID_ARGUMENT_KEY).toString(),
                isOpenedFromDetails = it.arguments?.getBoolean(DREAM_OPENED_FROM_DETAILS_ARGUMENT_KEY) ?: false
            )
        }

        composable(route = DreamScreens.SignIn.route) {
            SignInScreen(navController = navController)
        }

        composable(route = DreamScreens.Journal.route) {
            val journalScreenViewModel: JournalScreenViewModel = hiltViewModel()

            JournalScreen(navController = navController, viewModel = journalScreenViewModel)
        }
        composable(route = DreamScreens.Statistics.route) {
            val viewModel: StatsScreenViewModel = hiltViewModel()

            StatisticsScreen(viewModel = viewModel, navController = navController)
        }
        composable(route = DreamScreens.Search.route) {
            val viewModel: JournalScreenViewModel = hiltViewModel()

            SearchScreen(viewModel, navController)
        }

        composable(
            route = DreamScreens.SearchByQuery.route,
            arguments = listOf(
                navArgument(SEARCH_QUERY_ARGUMENT_KEY) {
                    type = NavType.StringType
                    defaultValue = ""
                },
            )
        ) {
            val viewModel: JournalScreenViewModel = hiltViewModel()

            SearchByQueryScreen(
                viewModel = viewModel,
                query = it.arguments?.getString(SEARCH_QUERY_ARGUMENT_KEY).toString(),
                navController = navController
            )
        }

        composable(route = DreamScreens.Profile.route) {
            ProfileScreen(navController)
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
            val viewModel: DreamDetailsViewModel = hiltViewModel()

            DreamDetailsScreen(
                navController = navController,
                dreamId = it.arguments?.getString(DREAM_ID_ARGUMENT_KEY).toString(),
                viewModel = viewModel
            )
        }
    }
}
