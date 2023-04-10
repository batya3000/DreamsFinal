package com.android.batya.dreams.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.model.Tag
import com.android.batya.dreams.ui.theme.*
import com.android.batya.dreams.utils.getFormattedDate

@Composable
fun DreamItemOld(dream: Dream) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = CardBackgroundColor,
            //if(dream.isLucid) CardLucidBackgroundColor
            //else CardNonLucidBackgroundColor,
        elevation = 0.dp,
        border = BorderStroke(1.dp, BorderColor)
    ) {
        Column {
//            Box(modifier = Modifier
//                .fillMaxWidth()
//                .height(20.dp)
//                .background(
//                    when (Random.nextInt(3)) {
//                        0 -> {
//                            ChipBackgroundColor
//                        }
//                        1 -> {
//                            Color.Cyan
//                        }
//                        2 -> {
//                            Color.Red
//                        }
//                        3 -> {
//                            Color.Green
//                        }
//                        else -> {
//                            Color.Blue
//                        }
//                    }
//                )
//            ) {
//
//            }

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 16.dp
                    )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        modifier = Modifier

                            .width(160.dp),
                        text = dream.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White
                    )
                    Text(
                        text = getFormattedDate(dream.date),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = DateTimeTextColor
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = dream.description,
                    fontSize = 11.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
                if (dream.tags != emptyList<Tag>()) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        ChipGroup(tags = dream.tags)
                    }
                }
            }

        }

    }
}

