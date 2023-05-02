package com.android.batya.dreams.screens.search.tabs

import DreamItem
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.batya.dreams.R
import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.navigation.DreamScreens
import com.android.batya.dreams.ui.theme.BottomBarBackgroundColor
import com.android.batya.dreams.ui.theme.CardBackgroundColor
import com.android.batya.dreams.ui.theme.DateTimeTextColor
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.color.KalendarThemeColor
import com.himanshoe.kalendar.component.day.config.KalendarDayColors
import com.himanshoe.kalendar.model.KalendarEvent
import com.himanshoe.kalendar.model.KalendarType
import kotlinx.datetime.LocalDate

@Composable
fun SearchByDateTab(dreams: List<Dream>, navController: NavController) {
    val calendarEvents = mutableListOf<KalendarEvent>()
    var filteredDreams by remember {
        mutableStateOf(dreams)
    }

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
            contentPadding = PaddingValues(top = 5.dp, bottom = 30.dp),
        ) {
            calendarEvents.clear()
            dreams.forEach {  dream ->
                val event = KalendarEvent(
                    date = LocalDate.fromEpochDays(dream.date.toInt()),
                    eventName = dream.title,
                    eventDescription = dream.description
                )
                calendarEvents.add(event)
            }
            item {
                Kalendar(
                    modifier = Modifier
                        .padding(
                            bottom = 30.dp
                        )
                        .height(420.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    kalendarType = KalendarType.Firey,
                    kalendarThemeColor = KalendarThemeColor(
                        backgroundColor = CardBackgroundColor,
                        dayBackgroundColor = BottomBarBackgroundColor,
                        headerTextColor = Color.White
                    ),
                    kalendarDayColors = KalendarDayColors(
                        textColor = DateTimeTextColor,
                        selectedTextColor = Color.White
                    ),
                    kalendarEvents = calendarEvents,
                    onCurrentDayClick = { kalendarDay, kalendarEvents ->
                        filteredDreams = dreams
                        filteredDreams = filteredDreams.filter { dream ->
                            LocalDate.fromEpochDays(dream.date.toInt()) == kalendarDay.localDate
                        }
                        Log.d("TAG", "SearchByDateTab: ${filteredDreams.size}")
                    }
                )
            }
            if (filteredDreams.isEmpty()) {
                item {
                    Text(
                        text = stringResource(R.string.search_no_dreams_date),
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
            itemsIndexed(filteredDreams.sortedWith(compareBy(Dream::date, Dream::time)).reversed()) { _, dream ->
                DreamItem(dream = dream) {
                    navController.navigate(
                        DreamScreens.Details.passDreamId(dream.id)
                    )
                    Log.d("TAG", "SearchByDate: opened ${dream.id}")
                }
            }
        }
    }
}