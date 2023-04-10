package com.android.batya.dreams.screens.journal

import DreamItemNew
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.android.batya.dreams.navigation.DreamScreens
import com.android.batya.dreams.repository.DreamRepository

@Composable
fun JournalScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ListOfDreams(navController = navController)
    }
}

@Composable
fun ListOfDreams(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 53.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(top = 20.dp, bottom = 30.dp),
        ) {
            itemsIndexed(DreamRepository().getDreams()) { _, dream ->
                DreamItemNew(dream = dream) {
                    // onClick
                    navController.navigate(
                        DreamScreens.Details.passDreamId(dream.id)
                    ) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }

                    Log.d("TAG", "ListOfDreams: clicked ${dream.id}")
                }
            }
        }
    }
}