package com.android.batya.dreams.screens.stats

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.batya.dreams.R
import com.android.batya.dreams.components.IconCard
import com.android.batya.dreams.components.PieChart
import com.android.batya.dreams.components.StatsIconItem
import com.android.batya.dreams.components.StatsItem
import com.android.batya.dreams.components.chips.TagChip
import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.model.Lucidity
import com.android.batya.dreams.model.Mood
import com.android.batya.dreams.model.Tag
import com.android.batya.dreams.navigation.DreamScreens
import com.android.batya.dreams.ui.theme.CardDarkerBackgroundColor


@Composable
fun StatisticsScreen(viewModel: StatsScreenViewModel, navController: NavHostController) {


    val scrollState = rememberScrollState()

    var dreams = emptyList<Dream>()

    if(!viewModel.data.value.data.isNullOrEmpty()) {
        dreams = viewModel.data.value.data!!.toList()
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 25.dp,
                bottom = 73.dp
            )
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            backgroundColor = CardDarkerBackgroundColor,
            elevation = 0.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 20.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp),
                    //horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StatsItem(
                        modifier = Modifier.weight(0.5f),
                        number = dreams.size,
                        description = "Total dreams"
                    )
                    StatsItem(
                        modifier = Modifier.weight(0.5f),
                        number = dreams.count { dream ->
                            dream.lucidity == Lucidity.LUCID
                        },
                        description = "Lucid dreams"
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    // horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StatsItem(
                        modifier = Modifier.weight(0.5f),
                        number = viewModel.getLongestStreak(),
                        description = "Longest streak"
                    )
                    StatsItem(
                        modifier = Modifier.weight(0.5f),
                        number = viewModel.getTagsCount(),
                        description = "Tags"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            backgroundColor = CardDarkerBackgroundColor,
            elevation = 0.dp
        ) {
            if (dreams.isEmpty()) {
                Column(modifier = Modifier.padding(15.dp)) {
                    Text(
                        text = stringResource(id = R.string.journal_no_dreams),
                        fontSize = 20.sp,
                        color = Color.White.copy(0.9f),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                PieChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            20.dp
                        ),
                    data = mapOf(
                        "Lucid" to dreams.count { it.lucidity == Lucidity.LUCID },
                        "Not lucid" to dreams.count { it.lucidity == Lucidity.NOT_LUCID },
                        "Not selected" to dreams.count { it.lucidity == Lucidity.NOT_SELECTED },
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))


        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                // horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StatsIconItem(
                    modifier = Modifier.weight(0.5f),
                    title = "Breathtaking",
                    number = dreams.count { it.mood == Mood.BREATHTAKING },
                    icon = R.drawable.ic_mood_breathtaking,
                )

                Spacer(modifier = Modifier.width(10.dp))

                StatsIconItem(
                    modifier = Modifier.weight(0.5f),
                    title = "Good",
                    number = dreams.count { it.mood == Mood.GOOD },
                    icon = R.drawable.ic_mood_good,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                // horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StatsIconItem(
                    modifier = Modifier.weight(0.5f),
                    title = "Normal",
                    number = dreams.count { it.mood == Mood.NORMAL },
                    icon = R.drawable.ic_mood_normal,
                )

                Spacer(modifier = Modifier.width(10.dp))

                StatsIconItem(
                    modifier = Modifier.weight(0.5f),
                    title = "Bad",
                    number = dreams.count { it.mood == Mood.BAD },
                    icon = R.drawable.ic_mood_bad,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                // horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StatsIconItem(
                    modifier = Modifier.weight(0.5f),
                    title = "Horror",
                    number = dreams.count { it.mood == Mood.HORROR },
                    icon = R.drawable.ic_mood_horror,
                )

                Spacer(modifier = Modifier.width(10.dp))

                StatsIconItem(
                    modifier = Modifier.weight(0.5f),
                    title = "Bored",
                    number = dreams.count { it.mood == Mood.BORED },
                    icon = R.drawable.ic_mood_bored,
                )
            }
        }

        val mostFrequentTag = viewModel.getMostFrequentTag()
        val mostFrequentTagCounter = viewModel.getMostFrequentTagCount()
        Spacer(modifier = Modifier.height(15.dp))
        if (mostFrequentTag != null) {
            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(38.dp),
                shape = RoundedCornerShape(10.dp),
                backgroundColor = CardDarkerBackgroundColor,
                elevation = 0.dp
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 14.dp,
                                end = 8.dp,
                                top = 4.dp,
                                bottom = 4.dp
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Most frequent tag (${mostFrequentTagCounter} times)",
                            fontSize = 14.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Light
                        )
                        Box(modifier = Modifier.width(160.dp), contentAlignment = Alignment.CenterEnd) {
                            TagChip(
                                tag = Tag(mostFrequentTag)
                            ) {
                                navController.navigate(
                                    DreamScreens.SearchByQuery.passQuery(mostFrequentTag)
                                )
                            }
                        }
                    }
                }
            }
        }

        val mostFrequentMood: String? = viewModel.getMostFrequentMood()
        val mostFrequentMoodCounter: Int? = viewModel.getMostFrequentMoodCount()
        if (mostFrequentMood != null) {
            Spacer(modifier = Modifier.height(10.dp))
            IconCard(
                title = "Most frequent mood ($mostFrequentMoodCounter times)",
                icon = when(mostFrequentMood) {
                    Mood.HORROR.title -> { R.drawable.ic_mood_horror }
                    Mood.BAD.title -> { R.drawable.ic_mood_bad }
                    Mood.NORMAL.title  -> { R.drawable.ic_mood_normal }
                    Mood.GOOD.title  -> { R.drawable.ic_mood_good }
                    Mood.BREATHTAKING.title -> { R.drawable.ic_mood_breathtaking}
                    else -> { R.drawable.ic_mood_bored }
                },
                iconSize = 23.dp,
                fontWeight = FontWeight.Light,
                cardBackgroundColor = CardDarkerBackgroundColor
            )
        }
    }
}

