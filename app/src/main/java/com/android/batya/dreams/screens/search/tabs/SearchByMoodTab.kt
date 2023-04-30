package com.android.batya.dreams.screens.search.tabs

import DreamItem
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.batya.dreams.R
import com.android.batya.dreams.components.FilterChip
import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.model.Mood
import com.android.batya.dreams.model.buttons.MoodItemModel
import com.android.batya.dreams.navigation.DreamScreens
import com.android.batya.dreams.screens.search.SearchScreenViewModel

@Composable
fun SearchByMoodTab(dreams: List<Dream>, navController: NavController) {

    var filteredDreams by remember {
        mutableStateOf(dreams)
    }

    val context = LocalContext.current
    val chips = listOf(
        MoodItemModel(title = context.getString(R.string.mood_title_breathtaking), icon = R.drawable.ic_mood_breathtaking),
        MoodItemModel(title = context.getString(R.string.mood_title_good), icon = R.drawable.ic_mood_good),
        MoodItemModel(title = context.getString(R.string.mood_title_normal), icon = R.drawable.ic_mood_normal),
        MoodItemModel(title = context.getString(R.string.mood_title_bad), icon = R.drawable.ic_mood_bad),
        MoodItemModel(title = context.getString(R.string.mood_title_horror), icon = R.drawable.ic_mood_horror),
        MoodItemModel(title = context.getString(R.string.mood_title_bored), icon = R.drawable.ic_mood_bored),
    )

    val selectedChips = remember {
        mutableStateListOf<String>()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 0.dp,
                end = 0.dp,
                top = 5.dp,
                bottom = 53.dp
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 0.dp,
                    bottom = 0.dp
                )
        ) {
            item {
                FilterChip(
                    text = "All",
                    icon = 0,
                    selected = selectedChips.isEmpty()
                ) {
                    selectedChips.clear()
                }
            }

            items(chips) { mood ->
                FilterChip(
                    text = mood.title,
                    icon = mood.icon,
                    selected = mood.title in selectedChips
                ) {
                    if (mood.title in selectedChips) selectedChips -= mood.title
                    else selectedChips += mood.title
                }
            }
        }

        filteredDreams = dreams.filter { dream ->
            getTitleFromMood(dream.mood, context) in selectedChips
        }

        Spacer(modifier = Modifier.height(15.dp))
        if ((filteredDreams.isEmpty() && selectedChips.isNotEmpty()) || (dreams.isEmpty() && selectedChips.isEmpty())) {
            Text(
                text = stringResource(R.string.search_no_dreams_mood),
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
            if (selectedChips.isEmpty()) {
                itemsIndexed(dreams.sortedWith(compareBy(Dream::date, Dream::time)).reversed()) { _, dream ->
                    DreamItem(dream = dream) {
                        navController.navigate(DreamScreens.Details.passDreamId(dream.id))
                        Log.d("TAG", "SearchByMood: opened ${dream.id}")
                    }
                }
            } else {
                itemsIndexed(filteredDreams.sortedWith(compareBy(Dream::date, Dream::time)).reversed()) { _, dream ->
                    DreamItem(dream = dream) {
                        navController.navigate(DreamScreens.Details.passDreamId(dream.id))
                        Log.d("TAG", "SearchByMood: opened ${dream.id}")
                    }
                }
            }
        }
    }
}

fun getTitleFromMood(mood: Mood, context: Context): String {
    return when(mood) {
        Mood.BORED -> context.getString(R.string.mood_title_bored)
        Mood.HORROR -> context.getString(R.string.mood_title_horror)
        Mood.BAD -> context.getString(R.string.mood_title_bad)
        Mood.NORMAL -> context.getString(R.string.mood_title_normal)
        Mood.GOOD -> context.getString(R.string.mood_title_good)
        Mood.BREATHTAKING -> context.getString(R.string.mood_title_breathtaking)
        else -> context.getString(R.string.title_not_selected)
    }
}
