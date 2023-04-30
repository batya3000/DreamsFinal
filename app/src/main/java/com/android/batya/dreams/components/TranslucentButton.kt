package com.android.batya.dreams.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.batya.dreams.R
import com.android.batya.dreams.ui.theme.CardBackgroundColor

@Composable
fun TranslucentButton(
    text: String,
    shapeCorners: Dp = 15.dp,
    iconDrawable: Int = 0,
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = CardBackgroundColor
        ),
        border = BorderStroke(0.dp, Color.Transparent),
        shape = RoundedCornerShape(shapeCorners)
    ) {
        if (iconDrawable != 0) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = iconDrawable),
                contentDescription = "Button Icon",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
        Text(
            text = text,
            color = Color.White,
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )
    }
}