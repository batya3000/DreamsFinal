package com.android.batya.dreams.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun FieldLabel(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.White,
    fontSize: TextUnit = 13.sp,
    fontWeight: FontWeight = FontWeight.Bold
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight,
    )
}