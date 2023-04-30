package com.android.batya.dreams.screens.edit


import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.batya.dreams.components.CustomRadioButton
import com.android.batya.dreams.R
import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.model.Mood
import com.android.batya.dreams.model.buttons.MoodItemModel

@Composable
fun MoodScreenContent(dream: Dream, viewModel: EditDreamViewModel) {
    var selectedMood by remember {
        mutableStateOf(dream.mood)
    }
    val context = LocalContext.current
    val buttonsList = listOf(
        MoodItemModel(title = stringResource(R.string.mood_title_breathtaking), icon = R.drawable.ic_mood_breathtaking),
        MoodItemModel(title = stringResource(R.string.mood_title_good), icon = R.drawable.ic_mood_good),
        MoodItemModel(title = stringResource(R.string.mood_title_normal), icon = R.drawable.ic_mood_normal),
        MoodItemModel(title = stringResource(R.string.mood_title_bad), icon = R.drawable.ic_mood_bad),
        MoodItemModel(title = stringResource(R.string.mood_title_horror), icon = R.drawable.ic_mood_horror),
        MoodItemModel(title = stringResource(R.string.mood_title_bored), icon = R.drawable.ic_mood_bored),
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
                text = stringResource(R.string.edit_dream_mood_question),
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
                    selected = selectedMood == getMoodFromTitle(button.title, context)
                ) {
                    selectedMood = getMoodFromTitle(button.title, context)
                    dream.mood = selectedMood
                    viewModel.updateDream(dream)
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }

}
fun getMoodFromTitle(title: String, context: Context): Mood {
    return when(title) {
        context.getString(R.string.mood_title_bored) -> Mood.BORED
        context.getString(R.string.mood_title_horror) -> Mood.HORROR
        context.getString(R.string.mood_title_bad) -> Mood.BAD
        context.getString(R.string.mood_title_normal) -> Mood.NORMAL
        context.getString(R.string.mood_title_good) -> Mood.GOOD
        context.getString(R.string.mood_title_breathtaking) -> Mood.BREATHTAKING
        else -> Mood.NOT_SELECTED
    }
}