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
import com.android.batya.dreams.model.Lucidity
import com.android.batya.dreams.model.buttons.LucidItemModel
import com.android.batya.dreams.navigation.DreamScreens
import com.android.batya.dreams.screens.search.SearchScreenViewModel

@Composable
fun SearchByLucidTab(dreams: List<Dream>, navController: NavController) {

    var filteredDreams by remember {
        mutableStateOf(dreams)
    }

    val context = LocalContext.current
    val chips = listOf(
        LucidItemModel(title = context.getString(R.string.lucid_title), icon = R.drawable.ic_lucid),
        LucidItemModel(title = context.getString(R.string.not_lucid_title), icon = R.drawable.ic_non_lucid),
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
                    iconSize = 20.dp,
                    selected = selectedChips.isEmpty()
                ) {
                    selectedChips.clear()
                }
            }
            items(chips) { item ->
                FilterChip(
                    text = item.title,
                    icon = item.icon,
                    iconSize = 20.dp,
                    selected = item.title in selectedChips
                ) {
                    if (item.title in selectedChips) selectedChips -= item.title
                    else selectedChips += item.title
                }
            }
        }
        Log.d("TAG", "SearchByLucidTab: ${selectedChips.toList()}")
        filteredDreams = dreams.filter { dream ->
            getTitleFromLucidity(dream.lucidity, context) in selectedChips
        }

        Spacer(modifier = Modifier.height(15.dp))
        if ((filteredDreams.isEmpty() && selectedChips.isNotEmpty()) || (dreams.isEmpty() && selectedChips.isEmpty())) {
            Text(
                text = stringResource(R.string.search_no_dreams_lucidity),
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
                        Log.d("TAG", "SearchByLucid: opened ${dream.id}")
                    }
                }
            } else {
                itemsIndexed(filteredDreams.sortedWith(compareBy(Dream::date, Dream::time)).reversed()) { _, dream ->
                    DreamItem(dream = dream) {
                        navController.navigate(DreamScreens.Details.passDreamId(dream.id))
                        Log.d("TAG", "SearchByLucid: opened ${dream.id}")
                    }
                }
            }
        }
    }
}

fun getTitleFromLucidity(lucidity: Lucidity, context: Context): String {
    return when(lucidity) {
        Lucidity.LUCID -> context.getString(R.string.lucid_title)
        Lucidity.NOT_LUCID -> context.getString(R.string.not_lucid_title)
        else -> context.getString(R.string.title_not_selected)
    }
}
