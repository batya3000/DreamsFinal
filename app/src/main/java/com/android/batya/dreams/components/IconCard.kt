package com.android.batya.dreams.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
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
    title: String,
    icon: Int,
    iconSize: Dp,
    cardBackgroundColor: Color = CardBackgroundColor
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(38.dp),
        shape = RoundedCornerShape(10.dp),
        //border = BorderStroke(width = 0.5.dp, color = BorderColor),
        backgroundColor = cardBackgroundColor,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier.padding(
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
                fontWeight = FontWeight.Normal
            )
            Image(
                modifier = Modifier.size(iconSize),
                painter = painterResource(id = icon),
                contentDescription = "Button Icon",
            )
        }

    }
}