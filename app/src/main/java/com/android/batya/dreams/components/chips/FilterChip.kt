package com.android.batya.dreams.components.chips

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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.batya.dreams.ui.theme.BorderColor
import com.android.batya.dreams.ui.theme.SelectedFilterChipBackgroundColor
import com.android.batya.dreams.ui.theme.UnselectedFilterChipBackgroundColor

@Composable
fun FilterChip(
    text: String,
    icon: Int,
    iconSize: Dp = 22.dp,
    selected: Boolean,
    onClick: (String) -> Unit,
) {
    val textColor: Color
    val backgroundColor: Color

    if (selected) {
        textColor = Color.White
        backgroundColor = SelectedFilterChipBackgroundColor
    } else {
        textColor = Color.White.copy(0.75f)
        backgroundColor = UnselectedFilterChipBackgroundColor
    }
    Card(
        modifier = Modifier
            .padding(end = 6.dp)
            .clickable {
                onClick(text)
            },
        shape = RoundedCornerShape(12.dp),
        backgroundColor = backgroundColor,
        elevation = 0.dp,
        border = BorderStroke(0.7.dp, BorderColor)
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 7.dp,
                    end = 7.dp,
                    top = 3.dp,
                    bottom = 3.dp
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != 0) {
                Image(
                    modifier = Modifier.size(iconSize),
                    painter = painterResource(id = icon),
                    contentDescription = "Mood Icon!"
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
            Box(
                modifier = Modifier.defaultMinSize(minHeight = iconSize),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text,
                    color = textColor,
                    fontSize = 10.sp
                )
            }

        }
    }
}