package com.android.batya.dreams.screens.edit


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.android.batya.dreams.R
import com.android.batya.dreams.components.CustomRadioButton
import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.model.Lucidity
import com.android.batya.dreams.model.Mood
import com.android.batya.dreams.model.buttons.RadioButtonItemModel
import com.android.batya.dreams.navigation.DreamScreens
import com.android.batya.dreams.ui.theme.BorderColor
import com.android.batya.dreams.ui.theme.CardBackgroundColor
import com.android.batya.dreams.ui.theme.GrayTextColor

@Composable
fun DreamLucidScreen (navController: NavController, dream: Dream) {
    var selectedLucidity by remember {
        mutableStateOf(dream.isLucid.title)
    }
    val buttonsList = listOf(
        RadioButtonItemModel(title = "Not lucid", icon = R.drawable.ic_non_lucid),
        RadioButtonItemModel(title = "Lucid", icon = R.drawable.ic_lucid),
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 35.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Was the dream lucid?",
                color = Color.White,
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            buttonsList.forEach { button ->
                CustomRadioButton(
                    title = button.title,
                    icon = button.icon,
                    iconSize = 28.dp,
                    selected = selectedLucidity == button.title
                ) {
                    selectedLucidity = button.title
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
            Spacer(modifier = Modifier.width(25.dp))
            Text(
                text = "A lucid dream means you recognized that you are in the dream.",
                color = GrayTextColor,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 40.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        onClick = {
                            // save into repository
                            navController.navigate(DreamScreens.Journal.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = ButtonDefaults.outlinedButtonColors(
                            backgroundColor = CardBackgroundColor
                        ),
                        border = BorderStroke(0.dp, Color.Transparent),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Text(
                            text = "SAVE",
                            color = Color.White,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
    dream.isLucid = getLucidityFromTitle(selectedLucidity)
}

fun getLucidityFromTitle(title: String): Lucidity {
    return when(title) {
        "Lucid" -> Lucidity.LUCID
        "Not lucid" -> Lucidity.NOT_LUCID
        else -> Lucidity.NOT_SELECTED
    }
}
