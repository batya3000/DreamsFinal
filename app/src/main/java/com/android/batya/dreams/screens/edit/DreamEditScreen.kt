package com.android.batya.dreams.screens.edit

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.android.batya.dreams.navigation.DreamScreens
import com.android.batya.dreams.ui.theme.TabRowSelectedBackgroundColor
import com.android.batya.dreams.ui.theme.TabRowUnselectedBackgroundColor
import com.android.batya.dreams.ui.theme.TabUnselectedIconColor
import kotlinx.coroutines.launch
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.zIndex
import androidx.navigation.compose.rememberNavController
import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.repository.DreamRepository

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DreamEditScreen(navController: NavHostController, dreamId: String) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val editPages = listOf(DreamScreens.General, DreamScreens.Mood, DreamScreens.Lucid)
    val dream by remember {
        mutableStateOf(
            if (dreamId != "") DreamRepository().getDreams()[dreamId.toInt()]
            else Dream()
        )
    }
    //val dream: Dream
    //if (dreamId != "") dream = DreamRepository().getDreams()[dreamId.toInt()]
    //else dream = Dream()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                backgroundColor = TabRowUnselectedBackgroundColor,
                modifier = Modifier
                    .padding(40.dp)
                    .width(150.dp)
                    .height(30.dp)
                    .clip(RoundedCornerShape(30.dp)),
                indicator = { tabPositions ->
                    CustomIndicator(tabPositions, pagerState)
                },
                divider = { }
            ) {
                editPages.forEachIndexed { index, page ->

                    val tabIconColor = remember {
                        Animatable(Color.Transparent)
                    }
                    LaunchedEffect(key1 = pagerState.currentPage == index) {
                        tabIconColor.animateTo(
                            if (pagerState.currentPage == index) {
                                Color.White
                            } else {
                                TabUnselectedIconColor
                            }
                        )
                    }
                    Tab(
                        selected = pagerState.currentPage == index,
                        modifier = Modifier
                            .background(color = Color.Transparent)
                            .zIndex(2f),
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = page.icon!!,
                            contentDescription = "Icon",
                            tint = tabIconColor.value
                        )
                    }
                }
            }
        }

        HorizontalPager(
            pageCount = 3,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) { page ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when(page) {
                    0 -> {
                        DreamGeneralScreen(dream)
                    }
                    1 -> {
                        DreamMoodScreen(dream)
                    }
                    2 -> {
                        DreamLucidScreen(navController, dream)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CustomIndicator(tabPositions: List<TabPosition>, pagerState: PagerState) {
    val transition = updateTransition(pagerState.currentPage, label = "")
    val indicatorStart by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 0.7f, stiffness = 40f)
            } else {
                spring(dampingRatio = 0.7f, stiffness = 120f)
            }
        }, label = ""
    ) {
        tabPositions[it].left
    }

    val indicatorEnd by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 0.7f, stiffness = 120f)
            } else {
                spring(dampingRatio = 0.7f, stiffness = 40f)
            }
        }, label = ""
    ) {
        tabPositions[it].right
    }

    Box(
        modifier = Modifier
            .offset(x = indicatorStart)
            .wrapContentSize(align = Alignment.BottomStart)
            .width(indicatorEnd - indicatorStart)
            //.padding(3.dp)
            .fillMaxSize()
            .background(color = TabRowSelectedBackgroundColor, RoundedCornerShape(50))
            .zIndex(1f)
    )
}
