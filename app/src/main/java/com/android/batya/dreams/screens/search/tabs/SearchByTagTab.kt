package com.android.batya.dreams.screens.search.tabs

import DreamItem
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.navigation.DreamScreens
import com.android.batya.dreams.screens.search.SearchScreenViewModel

@Composable
fun SearchByTagTab(dreams: List<Dream>, viewModel: SearchScreenViewModel, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 0.dp,
                end = 0.dp,
                top = 10.dp,
                bottom = 53.dp
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(top = 20.dp, bottom = 30.dp),
        ) {

            itemsIndexed(dreams.sortedWith(compareBy(Dream::date, Dream::time)).reversed()) { _, dream ->
                DreamItem(dream = dream) {
                    // onClick
                    navController.navigate(
                        DreamScreens.Details.passDreamId(dream.id)
                    ) {
                        //popUpTo(navController.graph.findStartDestination().id) {
                        //}
                        //launchSingleTop = true
                    }
                    Log.d("TAG", "ListOfDreams: opened ${dream.id}")
                }
            }
        }
    }
}