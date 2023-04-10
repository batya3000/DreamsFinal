package com.android.batya.dreams.navigation.graphs

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.android.batya.dreams.navigation.DREAM_ID_ARGUMENT_KEY
import com.android.batya.dreams.navigation.DreamScreens

import com.android.batya.dreams.navigation.Graph
import com.android.batya.dreams.screens.details.DreamDetailsScreen
import com.android.batya.dreams.screens.edit.DreamEditScreen


fun NavGraphBuilder.editDreamNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.EDIT_DREAM,
        startDestination = DreamScreens.DreamEdit.route
    ) {
        composable(
            route = DreamScreens.DreamEdit.route,
            arguments = listOf(
                navArgument(DREAM_ID_ARGUMENT_KEY) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            DreamEditScreen(
                navController = navController,
                dreamId = it.arguments?.getString(DREAM_ID_ARGUMENT_KEY).toString()
            )
        }
    }
}

