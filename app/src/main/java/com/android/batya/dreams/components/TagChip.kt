package com.android.batya.dreams.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.batya.dreams.model.Tag
import com.android.batya.dreams.ui.theme.ChipBackgroundColor



@Composable
fun TagChip(
    tag: Tag,
    onClick: (Tag) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(end = 6.dp)
            .clickable {
                onClick(tag)
            },
        shape = RoundedCornerShape(6.dp),
        backgroundColor = ChipBackgroundColor,
        elevation = 0.dp
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 7.dp, vertical = 3.dp),
                text = tag.title.toString(),
                color = Color.White,
                fontSize = 10.sp
            )
        }
    }
}