package com.android.batya.dreams.screens.search

import DreamItem
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.batya.dreams.R
import com.android.batya.dreams.components.SearchForm
import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.navigation.DreamScreens
import com.android.batya.dreams.screens.journal.JournalScreenViewModel

@Composable
fun SearchByQueryScreen(
    viewModel: JournalScreenViewModel,
    query: String = "",
    navController: NavHostController
) {
    val queryState = remember {
        mutableStateOf(query)
    }
    var dreams = listOf<Dream>()
    var filteredDreams by remember {
        mutableStateOf(dreams)
    }

    val focusRequester = remember { FocusRequester() }

    if(!viewModel.data.value.data.isNullOrEmpty()) {
        dreams = viewModel.data.value.data!!.toMutableList()
    }

    if (dreams.isEmpty()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Loading...",
                color = Color.White
            )
        }
    } else {
        if (query != "") {
            filteredDreams = dreams.filter { dream ->
                query in dream.title.lowercase() ||
                query in dream.description.lowercase() ||
                query in dream.tags.map { it.title }
            }
        }

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        top = 30.dp,
                        bottom = 0.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
                        }
                        .padding(start = 5.dp),
                    imageVector = Icons.Default.ArrowBack,
                    tint = Color.White,
                    contentDescription = "Back Icon"
                )
                Spacer(modifier = Modifier.width(15.dp))
                SearchForm(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            end = 10.dp,
                            bottom = 0.dp
                        )
                        .focusRequester(focusRequester)
                        .onGloballyPositioned {
                            if (query == "") focusRequester.requestFocus()
                        },
                    placeholder = "Search dream",
                    valueState = queryState,
                ) { query ->
                    filteredDreams = dreams.filter { dream ->
                        query in dream.title.lowercase() ||
                        query in dream.description.lowercase() ||
                        query in dream.tags.map { it.title }
                    }
                }
            }


            Spacer(modifier = Modifier.height(35.dp))

            if (filteredDreams.isEmpty()) {
                Text(
                    text = stringResource(R.string.search_no_dreams_text),
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
            LazyColumn(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(top = 5.dp, bottom = 30.dp),
            ) {
                items(filteredDreams.sortedWith(compareBy(Dream::date, Dream::time)).reversed()) {dream ->
                    DreamItem(dream = dream) {
                        navController.navigate(DreamScreens.Details.passDreamId(dream.id))
                        Log.d("TAG", "SearchByQuery: opened ${dream.id}")
                    }
                }
            }
        }
    }
}