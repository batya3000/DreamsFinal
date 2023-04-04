package com.android.batya.dreams.screens.edit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.android.batya.dreams.components.FieldLabel
import com.android.batya.dreams.components.InputField
import com.android.batya.dreams.navigation.DreamScreens

@Composable
fun DreamGeneralScreen(
    navController: NavHostController,
    dreamId: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 37.dp),
        ) {
            Text(
                modifier = Modifier.clickable {
                    navController.popBackStack()
                    navController.navigate(DreamScreens.Mood.route)
                },
                text = "Dream General",
                fontSize = MaterialTheme.typography.h3.fontSize,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = dreamId,
                fontSize = MaterialTheme.typography.h6.fontSize,
            )

            val textFieldValue = rememberSaveable { mutableStateOf("") }

            FieldLabel(
                modifier = Modifier
                    .padding(start = 5.dp),
                text = "Название",
            )
            InputField(
                valueState = textFieldValue,
                placeholder = "Название сна",
                enabled = true,
                onAction = KeyboardActions { }
            )
        }
    }
}