package com.android.batya.dreams.screens.details

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.android.batya.dreams.components.ChipGroup
import com.android.batya.dreams.components.IconCard
import com.android.batya.dreams.repository.DreamRepository
import com.android.batya.dreams.R
import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.navigation.DreamScreens
import com.android.batya.dreams.ui.theme.BorderColor
import com.android.batya.dreams.ui.theme.CardDarkerBackgroundColor
import com.android.batya.dreams.utils.getFormattedDate
import com.android.batya.dreams.utils.getFormattedTime

@Composable
fun DreamDetailsScreen(navController: NavHostController, dreamId: String) {
    val scrollableState = rememberScrollState()
    val context = LocalContext.current
    var menuExpanded by remember { mutableStateOf(false) }
    val dream = DreamRepository().getDreams()[dreamId.toInt()]



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 30.dp,
                bottom = 30.dp
            )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
                //.height(500.dp),
            shape = RoundedCornerShape(10.dp),
            //border = BorderStroke(width = 0.5.dp, color = BorderColor),
            backgroundColor = CardDarkerBackgroundColor,
            elevation = 0.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 8.dp,
                        bottom = 20.dp
                    )
                    .verticalScroll(scrollableState)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier,
                        text = "${getFormattedDate(dream.date)}, ${getFormattedTime(dream.time)}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                    Box() {
                        IconButton(onClick = { menuExpanded = !menuExpanded }) {
                            Icon(
                                modifier = Modifier.size(22.dp),
                                imageVector = Icons.Default.MoreVert,
                                tint = Color.White,
                                contentDescription = "More Icon"
                            )
                        }
                        DropdownMenu(
                            modifier = Modifier.background(Color.Blue.copy(0.85f)),
                            expanded = menuExpanded,
                            onDismissRequest = { menuExpanded = false }
                        ) {
                            DropdownMenuItem(
                                onClick = {
                                    Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show()
                                    menuExpanded = false

                                    navController.navigate(
                                        DreamScreens.DreamEdit.passDreamId(dream.id)
                                    ) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            ) {
                                Icon(
                                    modifier = Modifier.size(20.dp),
                                    imageVector = Icons.Default.Edit,
                                    tint = Color.White,
                                    contentDescription = "Edit Icon"
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "Edit", color = Color.White)
                            }
                            DropdownMenuItem(
                                onClick = {
                                    Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show()
                                    menuExpanded = false
                                }
                            ) {
                                Icon(
                                    modifier = Modifier.size(20.dp),
                                    imageVector = Icons.Default.Delete,
                                    tint = Color.White,
                                    contentDescription = "Delete Icon"
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "Delete", color = Color.White)
                            }
                        }
                    }

                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier,
                    text = dream.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    modifier = Modifier,
                    text = "${dream.description.repeat(1)}",
                    fontSize = 13.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column() {
                if (dream.tags.isNotEmpty()) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(38.dp),
                        shape = RoundedCornerShape(10.dp),
                        //border = BorderStroke(width = 0.5.dp, color = BorderColor),
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
                                        end = 14.dp,
                                        top = 4.dp,
                                        bottom = 4.dp
                                    ),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Tags",
                                    fontSize = 14.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Normal
                                )
                                Box(modifier = Modifier.width(160.dp), contentAlignment = Alignment.CenterEnd) {
                                    ChipGroup(tags = dream.tags)
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }

                IconCard(
                    title = "Mood",
                    icon = R.drawable.ic_mood_bad,
                    iconSize = 23.dp,
                    cardBackgroundColor = CardDarkerBackgroundColor
                )
                Spacer(modifier = Modifier.height(10.dp))
                IconCard(
                    title = "Lucidity",
                    icon = R.drawable.ic_non_lucid,
                    iconSize = 21.dp,
                    cardBackgroundColor = CardDarkerBackgroundColor
                )
            }
        }
    }
}