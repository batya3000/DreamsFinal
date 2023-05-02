package com.android.batya.dreams.screens.journal

import DreamItem
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.android.batya.dreams.components.NoDreamsBanner
import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.navigation.DreamScreens
import com.android.batya.dreams.screens.home.HomeScreenViewModel

@Composable
fun JournalScreen(
    navController: NavController,
    viewModel: JournalScreenViewModel = hiltViewModel(),
    homeViewModel: HomeScreenViewModel = hiltViewModel(),
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ListOfDreams(navController = navController, viewModel = viewModel, homeViewModel = homeViewModel)
    }
}
@Composable
fun ListOfDreams(
    navController: NavController,
    viewModel: JournalScreenViewModel,
    homeViewModel: HomeScreenViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 53.dp)
    ) {
        var listOfDreams = emptyList<Dream>()

        if(!viewModel.data.value.data.isNullOrEmpty()) {
            listOfDreams = viewModel.data.value.data!!.toList()
        }
        if (listOfDreams.isEmpty()) {
            NoDreamsBanner {
                // onClick
                val dream = Dream()
                homeViewModel.addDream(dream = dream)
                navController.navigate(
                    DreamScreens.DreamEdit.passArguments(id = dream.id, isOpenedFromDetails = false)
                ) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(top = 20.dp, bottom = 30.dp),
            ) {

                itemsIndexed(listOfDreams.sortedWith(compareBy(Dream::date, Dream::time)).reversed()) { _, dream ->
                    DreamItem(dream = dream) {
                        // onClick
                        navController.navigate(
                            DreamScreens.Details.passDreamId(dream.id)
                        ) {
                            popUpTo(navController.graph.findStartDestination().id) {
                            }
                            launchSingleTop = true
                        }
                        Log.d("TAG", "ListOfDreams: opened ${dream.id}")
                    }
                }
            }
        }
    }
}

