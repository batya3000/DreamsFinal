package com.android.batya.dreams.navigation.graphs

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.android.batya.dreams.navigation.DREAM_ID_ARGUMENT_KEY
import com.android.batya.dreams.navigation.DreamScreens

import com.android.batya.dreams.navigation.Graph
import com.android.batya.dreams.screens.edit.DreamGeneralScreen
import com.android.batya.dreams.screens.edit.DreamLucidScreen
import com.android.batya.dreams.screens.edit.DreamMoodScreen


fun NavGraphBuilder.editDreamNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.EDIT_DREAM,
        startDestination = DreamScreens.General.route
    ) {
        composable(
            route = DreamScreens.General.route,
            arguments = listOf(
                navArgument(DREAM_ID_ARGUMENT_KEY) {
                    type = NavType.StringType
                    defaultValue = "default"
                }
            )
        ) {
            DreamGeneralScreen(
                navController = navController,
                dreamId = it.arguments?.getString(DREAM_ID_ARGUMENT_KEY).toString()
            )
        }

        composable(route = DreamScreens.Mood.route) {
            DreamMoodScreen(navController = navController)
        }
        composable(route = DreamScreens.Lucid.route) {
            DreamLucidScreen(navController = navController)
        }
    }
}


