package com.android.batya.dreams.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.batya.dreams.R
import com.android.batya.dreams.navigation.DreamScreens
import com.android.batya.dreams.navigation.graphs.HomeNavGraph
import com.android.batya.dreams.ui.theme.BottomBarBackgroundColor
import com.android.batya.dreams.ui.theme.FabBackgroundColor
import com.android.batya.dreams.ui.theme.InactiveColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    var isFabInvisible by rememberSaveable { mutableStateOf(false) }

    val editDreamScreens = listOf(
        DreamScreens.General,
        DreamScreens.Mood,
        DreamScreens.Lucid
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    isFabInvisible = editDreamScreens.any { it.route == navBackStackEntry?.destination?.route}
    Scaffold(
        bottomBar = { BottomBarWithFab(navController = navController) },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = { NewDreamFloatingActionButton(navController, isFabInvisible) },
        isFloatingActionButtonDocked = true,
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.img_14),
                contentScale = ContentScale.Crop
            )
        ) {
            HomeNavGraph(navController = navController)
        }
    }
}

@Composable
fun BottomBarWithFab(navController: NavHostController) {
    val screens = listOf(
        DreamScreens.Journal,
        DreamScreens.Statistics,
        DreamScreens.Search,
        DreamScreens.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        BottomAppBar(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)),
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
            .height(70.dp),
        //.padding(4.dp, 0.dp, 4.dp, 0.dp),
        backgroundColor = BottomBarBackgroundColor,

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
fun NewDreamFloatingActionButton(navController: NavHostController, isFabInvisible: Boolean) {
    //var fabIsVisible by remember { mutableStateOf(true) }

    if(!isFabInvisible) {
        FloatingActionButton(
            shape = CircleShape,
            onClick = {
                navController.navigate(
                    DreamScreens.General
                        .passDreamId("123test")
                ) {
                    popUpTo(navController.currentDestination!!.id) {
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
                if(isEmptyElement) 0f
                else 1f
            ),
        label = {
            Text(
                text = screen.title!!,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
        },
        icon = {
            Icon(
                modifier = Modifier
                    .size(25.dp),
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