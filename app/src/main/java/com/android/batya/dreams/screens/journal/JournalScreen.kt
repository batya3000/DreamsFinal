package com.android.batya.dreams.screens.journal

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.batya.dreams.components.DreamItem
import com.android.batya.dreams.repository.DreamRepository

@Composable
fun JournalScreen(
//    name: String,
//    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ListOfDreams()
    }
}

@Composable
fun ListOfDreams() {
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
                DreamItem(dream = dream)
            }
        }
    }
}