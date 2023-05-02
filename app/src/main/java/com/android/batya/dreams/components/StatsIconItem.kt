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
import com.android.batya.dreams.ui.theme.CardDarkerBackgroundColor

@Composable
fun StatsIconItem(
    modifier: Modifier = Modifier,
    title: String,
    number: Int,
    icon: Int,
    iconSize: Dp = 22.dp,
    cardBackgroundColor: Color = CardDarkerBackgroundColor
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
            modifier = Modifier.padding(
                start = 10.dp,
                end = 10.dp,
                top = 3.dp,
                bottom = 3.dp
            ),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(iconSize),
                painter = painterResource(id = icon),
                contentDescription = "Button Icon",
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = title,
                color = Color.White.copy(0.9f),
                fontWeight = FontWeight.Light,
                fontSize = 14.sp
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = number.toString(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            }
        }

    }
}