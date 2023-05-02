package com.android.batya.dreams.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatsItem(
    modifier: Modifier = Modifier,
    number: Int,
    description: String = "",
    icon: Int = 0,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (icon != 0) {
            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = icon),
                contentDescription = "Mood Icon"
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = number.toString(),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
        } else {
            Text(
                text = number.toString(),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            Text(
                text = description,
                color = Color.White.copy(0.9f),
                fontWeight = FontWeight.Light,
                fontSize = 14.sp
            )
        }
    }
}