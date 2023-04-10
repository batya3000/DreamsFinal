package com.android.batya.dreams.navigation.graphs

import android.widget.Toast
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.android.batya.dreams.navigation.DreamScreens
import com.android.batya.dreams.navigation.Graph
import com.android.batya.dreams.screens.login.LoginScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTH,
        startDestination = DreamScreens.Login.route
    ) {
        composable(route = DreamScreens.Login.route) {
            LoginScreen(
                navController = navController
            )
        }
    }
}