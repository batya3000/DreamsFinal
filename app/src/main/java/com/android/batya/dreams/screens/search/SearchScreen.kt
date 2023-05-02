package com.android.batya.dreams.screens.search

import androidx.compose.animation.Animatable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.android.batya.dreams.components.InputField
import com.android.batya.dreams.components.NoDreamsBanner
import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.navigation.DreamScreens
import com.android.batya.dreams.screens.journal.JournalScreenViewModel
import com.android.batya.dreams.screens.search.tabs.SearchByDateTab
import com.android.batya.dreams.screens.search.tabs.SearchByLucidTab
import com.android.batya.dreams.screens.search.tabs.SearchByMoodTab
import com.android.batya.dreams.ui.theme.TabUnselectedIconColor
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(viewModel: JournalScreenViewModel, navController: NavController) {


    var listOfDreams = emptyList<Dream>()
    val searchValue = remember { mutableStateOf("") }

    if(!viewModel.data.value.data.isNullOrEmpty()) {
        listOfDreams = viewModel.data.value.data!!.toList()
    }

    if (listOfDreams.isEmpty()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(bottom = 53.dp)
        ) {
            NoDreamsBanner()
        }
    } else {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            InputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 30.dp,
                        bottom = 0.dp
                    ),
                placeholder = "Search dream",
                valueState = searchValue,
                isSingleLine = true,
                enabled = false
            ) { // onCliсk
                navController.navigate(DreamScreens.SearchByQuery.route)
            }

            SearchTabs(
                dreams = listOfDreams.sortedWith(compareBy(Dream::date, Dream::time)).reversed(),
                navController = navController
            )
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchTabs(dreams: List<Dream>, navController: NavController) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val editPages = listOf(
        DreamScreens.SearchByDate,
        DreamScreens.SearchByMood,
        DreamScreens.SearchByLucidity
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(0.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                backgroundColor = Color.Transparent,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 20.dp)
                    .height(45.dp),
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                            .padding(horizontal = 10.dp),
                        color = Color.White,
                        height = 2.dp
                    )
                },
                divider = { }
            ) {
                editPages.forEachIndexed { index, page ->

                    val tabTextColor = remember {
                        Animatable(Color.Transparent)
                    }
                    LaunchedEffect(key1 = pagerState.currentPage == index) {
                        tabTextColor.animateTo(
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
                        Text(
                            text = page.title!!,
                            color = tabTextColor.value,
                            fontSize = 14.sp,
                            fontWeight =
                                if (pagerState.currentPage == index) FontWeight.Bold
                                else FontWeight.Normal
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
                        SearchByDateTab(dreams = dreams, navController = navController)
                    }
                    1 -> {
                        SearchByMoodTab(dreams = dreams, navController = navController)
                    }
                    2 -> {
                        SearchByLucidTab(dreams = dreams, navController = navController)
                    }
                }
            }
        }
    }



}
