package com.android.batya.dreams.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.batya.dreams.ui.theme.CardBackgroundColor

@Composable
fun IconCard(
    modifier: Modifier = Modifier,
    title: String,
    icon: Int,
    iconSize: Dp = 23.dp,
    fontWeight: FontWeight = FontWeight.Normal,
    cardBackgroundColor: Color = CardBackgroundColor
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(38.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = cardBackgroundColor,
        elevation = 0.dp
    ) {
        Row(
            modifier = modifier.padding(
                start = 14.dp,
                end = 14.dp,
                top = 4.dp,
                bottom = 4.dp
            ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = title,
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = fontWeight
            )

            Image(
                modifier = modifier.size(iconSize),
                painter = painterResource(id = icon),
                contentDescription = "Button Icon",
            )
        }

    }
}