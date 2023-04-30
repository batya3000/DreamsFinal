package com.android.batya.dreams.screens.edit


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.batya.dreams.R
import com.android.batya.dreams.components.CustomRadioButton
import com.android.batya.dreams.components.TranslucentButton
import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.model.Lucidity
import com.android.batya.dreams.model.buttons.MoodItemModel
import com.android.batya.dreams.ui.theme.GrayTextColor

@Composable
fun LucidScreenContent(dream: Dream, viewModel: EditDreamViewModel, onSave: () -> Unit) {
    var selectedLucidity by remember {
        mutableStateOf(dream.lucidity.title)
    }
    val buttonsList = listOf(
        MoodItemModel(title = stringResource(R.string.edit_dream_not_lucid_title), icon = R.drawable.ic_non_lucid),
        MoodItemModel(title = stringResource(R.string.edit_dream_lucid_title), icon = R.drawable.ic_lucid),
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
                text = stringResource(R.string.edit_dream_lucid_question),
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
                    dream.lucidity = getLucidityFromTitle(selectedLucidity)
                    viewModel.updateDream(dream)
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
            Spacer(modifier = Modifier.width(25.dp))
            Text(
                text = stringResource(R.string.edit_dream_lucid_explanation),
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
                    TranslucentButton(
                        text = stringResource(R.string.save_button)
                    ) {
                        Log.d("TAG", "adding ${dream.id}")
                        onSave()
                    }
                }
            }
        }
    }

}
fun getLucidityFromTitle(title: String): Lucidity {
    return when(title) {
        "Lucid" -> Lucidity.LUCID
        "Not lucid" -> Lucidity.NOT_LUCID
        else -> Lucidity.NOT_SELECTED
    }
}
