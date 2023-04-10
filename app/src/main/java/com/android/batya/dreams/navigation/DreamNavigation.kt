package com.android.batya.dreams.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.batya.dreams.navigation.graphs.authNavGraph
import com.android.batya.dreams.navigation.graphs.editDreamNavGraph
import com.android.batya.dreams.screens.edit.DreamEditScreen
import com.android.batya.dreams.screens.home.HomeScreen


@Composable
fun DreamNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH
    ) {
        authNavGraph(navController)
        composable(route = Graph.HOME) {
            HomeScreen()
        }
    }
}