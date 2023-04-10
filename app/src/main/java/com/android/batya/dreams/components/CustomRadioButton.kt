package com.android.batya.dreams.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import com.android.batya.dreams.ui.theme.*

@Composable
fun CustomRadioButton(
    title: String,
    icon: Int,
    iconSize: Dp,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val borderWidth: Dp
    val backgroundColor: Color
    if (selected) {
        borderWidth = 1.6.dp
        backgroundColor = MoodSelectedBackgroundColor
    } else {
        borderWidth = 1.dp
        backgroundColor = MoodUnselectedBackgroundColor
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .clickable {
                onClick.invoke()
            },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(width = borderWidth, color = BorderColor),
        backgroundColor = backgroundColor,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(12.dp))
            Image(
                modifier = Modifier.size(iconSize),
                painter = painterResource(id = icon),
                contentDescription = "Button Icon",
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = title,
                fontSize = 15.sp,
                color = Color.White,
                fontWeight = //FontWeight.Normal
                    if(selected) FontWeight.Bold
                    else FontWeight.Normal
            )
        }

    }
}

