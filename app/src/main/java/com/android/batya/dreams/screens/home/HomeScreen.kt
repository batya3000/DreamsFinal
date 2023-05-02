package com.android.batya.dreams.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.batya.dreams.R
import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.navigation.DreamScreens
import com.android.batya.dreams.navigation.graphs.DreamNavigation
import com.android.batya.dreams.ui.theme.BottomBarBackgroundColor
import com.android.batya.dreams.ui.theme.FabBackgroundColor
import com.android.batya.dreams.ui.theme.InactiveColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    var isFabVisible by rememberSaveable { mutableStateOf(false) }

    val screens = listOf(
        DreamScreens.Journal,
        DreamScreens.Statistics,
        DreamScreens.Search,
        DreamScreens.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    isFabVisible = screens.any { it.route == navBackStackEntry?.destination?.route }
    MainScaffold(isFabVisible, navController, viewModel)

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScaffold(
    isFabVisible: Boolean,
    navController: NavHostController,
    viewModel: HomeScreenViewModel,
) {
    Scaffold(
        bottomBar = { BottomBarWithFab(navController = navController) },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = { NewDreamFloatingActionButton(navController, isFabVisible, viewModel) },
        isFloatingActionButtonDocked = true,
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.img_3),
                contentScale = ContentScale.Crop
            )
            .padding(top = 28.dp),
        ) {
            DreamNavigation(
                navController = navController
            )
        }
    }
}

@Composable
fun BottomBarWithFab(navController: NavHostController) {
    val screens = listOf(
        DreamScreens.Journal,
        DreamScreens.Statistics,
        DreamScreens.Search,
        DreamScreens.Profile,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        BottomAppBar(
            modifier = Modifier
                .padding(
                    start = 0.dp,
                    end = 0.dp,
                    top = 0.dp,
                    bottom = 0.dp
                )
                .clip(RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp)),
            backgroundColor = BottomBarBackgroundColor,
            cutoutShape = MaterialTheme.shapes.small.copy(
                CornerSize(percent = 50),
            ),
            elevation = 0.dp
        ) {
            BottomNav(navController = navController, screens)
        }
    }
}
@Composable
fun BottomNav(navController: NavHostController, screens: List<DreamScreens>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        modifier = Modifier
            .height(72.dp),
        backgroundColor = BottomBarBackgroundColor,
        elevation = 5.dp
    ) {
        screens.forEachIndexed { index, screen ->
            if(index == 2) {
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController,
                    isEmptyElement = true
                )
            }

            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController,
                isEmptyElement = false
            )
        }
    }
}

@Composable
fun NewDreamFloatingActionButton(
    navController: NavHostController,
    isFabVisible: Boolean,
    viewModel: HomeScreenViewModel
) {
    if(isFabVisible) {
        FloatingActionButton(
            shape = CircleShape,
            onClick = {
                val dream = Dream()
                viewModel.addDream(dream = dream)
                navController.navigate(
                    DreamScreens.DreamEdit.passArguments(id = dream.id, isOpenedFromDetails = false)
                ) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }

            },
            backgroundColor = FabBackgroundColor,
            contentColor = Color.White,
        ) {
            Icon(
                modifier = Modifier
                    .size(36.dp),
                imageVector = Icons.Rounded.Add,
                contentDescription = "Add Dream Icon"
            )
        }
    }

}
@Composable
fun RowScope.AddItem(
    screen: DreamScreens,
    currentDestination: NavDestination?,
    navController: NavHostController,
    isEmptyElement: Boolean
) {

    BottomNavigationItem(
        modifier = Modifier
            .alpha(
                if (isEmptyElement) 0f
                else 1f
            ),
        alwaysShowLabel = true,
        label = {
            Text(
                modifier = Modifier,
                text = screen.title!!,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                overflow = TextOverflow.Visible,
                softWrap = false,
                letterSpacing = 0.1.sp,
            )
        },
        icon = {
            Icon(
                modifier = Modifier
                    .size(25.dp),
                //.background(Color.Cyan),
                imageVector = screen.icon!!,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        unselectedContentColor = InactiveColor,
        selectedContentColor = Color.White,
        enabled = !isEmptyElement
    )
}

