package com.android.batya.dreams.screens.edit

import androidx.activity.compose.BackHandler
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
import kotlinx.coroutines.launch
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.batya.dreams.data.DataOrException
import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.ui.theme.*


@Composable
fun DreamEditScreen(
    navController: NavHostController,
    viewModel: EditDreamViewModel = hiltViewModel(),
    dreamId: String = "",
    isOpenedFromDetails: Boolean = false,

    ) {

    var alertDialogOpened by remember {
        mutableStateOf(false)
    }

    BackHandler {
        alertDialogOpened = true
    }

    if (alertDialogOpened) {
        AlertDialog(
            onDismissRequest = {
                alertDialogOpened = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        navController.navigate(DreamScreens.Journal.route) {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }
                        if (!isOpenedFromDetails) {
                            viewModel.deleteDreamById(dreamId)
                        }


                        alertDialogOpened = false
                    }
                ) {
                    Text(text = "Discard", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        // close the dialog
                        alertDialogOpened = false
                    }
                ) {
                    Text(text = "Cancel", color = Color.White)
                }
            },
            text = {
                Text(
                    text = "Discard unsaved changes and quit?",
                    color = DateTimeTextColor,
                    fontSize = 13.sp
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            shape = RoundedCornerShape(8.dp),
            backgroundColor = BottomBarBackgroundColor
        )
    }



    val dreamInfo = produceState(initialValue = DataOrException(data = Dream(),
        true, Exception("")) ){
        value = viewModel.dreamOrException.value
    }.value

    if (dreamInfo.loading == true) {
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
        dreamInfo.loading = false
    } else {
        EditDreamTabs(dream = viewModel.dreamOrException.value.data!!, viewModel = viewModel) {
            navController.navigate(DreamScreens.Journal.route) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EditDreamTabs(
    dream: Dream,
    viewModel: EditDreamViewModel,
    onSave: () -> Unit
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val editPages = listOf(DreamScreens.General, DreamScreens.Mood, DreamScreens.Lucid)

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
                        GeneralScreenContent(dream = dream, viewModel = viewModel)
                    }
                    1 -> {
                        MoodScreenContent(dream = dream, viewModel = viewModel)
                    }
                    2 -> {
                        LucidScreenContent(dream = dream, viewModel = viewModel) { // onSave
                            onSave()
                        }
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
