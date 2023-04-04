package com.android.batya.dreams.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.batya.dreams.R.*
import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.ui.theme.BorderColor
import com.android.batya.dreams.ui.theme.Shapes
import com.android.batya.dreams.ui.theme.CardBackground
import com.android.batya.dreams.ui.theme.DateTextColor

@Composable
fun DreamItem(dream: Dream) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        shape = Shapes.large,
        backgroundColor = CardBackground,
        elevation = 0.dp,
        border = BorderStroke(1.dp, BorderColor)
    ) {
        Row(
            modifier = Modifier
                //.background(Color.DarkGray.copy(alpha = 0.08f))
                .height(90.dp)
                .padding(end = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                painter = painterResource(id = drawable.lucid_indicator),
                contentDescription = "Lucid image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(9.dp)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = dream.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = dream.date,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = DateTextColor
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = dream.description,
                    fontSize = 11.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
            }
        }
    }
}


@Preview
@Composable
fun DreamItemPreview() {
    DreamItem(
        Dream(
            id = "3",
            title = "Три",
            description = "Текст... Описание. ".repeat(5),
            date = "11 февраля"
        )
    )
}