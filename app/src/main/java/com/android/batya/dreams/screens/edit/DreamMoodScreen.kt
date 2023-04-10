package com.android.batya.dreams.screens.edit


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.batya.dreams.components.CustomRadioButton
import com.android.batya.dreams.R
import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.model.Mood
import com.android.batya.dreams.model.buttons.RadioButtonItemModel

@Composable
fun DreamMoodScreen (dream: Dream) {
    var selectedMood by remember {
        mutableStateOf(dream.mood.title)
    }
    val buttonsList = listOf(
        RadioButtonItemModel(title = "Breathtaking", icon = R.drawable.ic_mood_breathtaking),
        RadioButtonItemModel(title = "Good", icon = R.drawable.ic_mood_good),
        RadioButtonItemModel(title = "Normal", icon = R.drawable.ic_mood_normal),
        RadioButtonItemModel(title = "Bad", icon = R.drawable.ic_mood_bad),
        RadioButtonItemModel(title = "Horror", icon = R.drawable.ic_mood_horror),
        RadioButtonItemModel(title = "Bored", icon = R.drawable.ic_mood_bored),
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
                text = "What was the mood of your dream?",
                color = Color.White,
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))
            buttonsList.forEach { button ->
                CustomRadioButton(
                    title = button.title,
                    icon = button.icon,
                    iconSize = 30.dp,
                    selected = selectedMood == button.title
                ) {
                    selectedMood = button.title
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
    dream.mood = getMoodFromTitle(selectedMood)
}

fun getMoodFromTitle(title: String): Mood {
    return when(title) {
        "Bored" -> Mood.BORED
        "Horror" -> Mood.HORROR
        "Bad" -> Mood.BAD
        "Normal" -> Mood.NORMAL
        "Good" -> Mood.GOOD
        "Breathtaking" -> Mood.BREATHTAKING
        else -> Mood.NOT_SELECTED
    }
}
