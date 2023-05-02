package com.android.batya.dreams.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.batya.dreams.R

@Composable
fun NoDreamsBanner(onClick: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp, vertical = 25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.journal_no_dreams),
            fontSize = 20.sp,
            color = Color.White.copy(0.9f),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(100.dp))

        Icon(
            modifier = Modifier
                .size(150.dp)
                .clickable {
                    onClick()
                },
            painter = painterResource(id = R.drawable.ic_no_dreams),
            contentDescription = "No dreams banner",
            tint = Color.White.copy(0.7f)
        )
    }

}