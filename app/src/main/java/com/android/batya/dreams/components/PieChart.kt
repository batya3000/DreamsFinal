package com.android.batya.dreams.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.batya.dreams.ui.theme.LucidColor
import com.android.batya.dreams.ui.theme.NotLucidColor
import com.android.batya.dreams.ui.theme.NotSelectedColor

@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    data: Map<String, Int>,
    radiusOuter: Dp = 90.dp,
    chartBarWidth: Dp = 15.dp,
) {
    val totalSum = data.values.sum()
    val floatValue = mutableListOf<Float>()

    data.values.forEachIndexed { index, values ->
        floatValue.add(index, 360*values.toFloat() / totalSum.toFloat())
    }

    val colors = listOf(
        LucidColor,
        NotLucidColor,
        NotSelectedColor
    )

    var lastValue = 0f

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DetailsPieChart(
            modifier = Modifier
                .fillMaxWidth(0.6f),
            data = data,
            colors = colors,
            totalSum = totalSum,
        )
        if (totalSum != 0) {
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .padding(10.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Canvas(
                    modifier = Modifier
                        .size(radiusOuter * 1.3f),
                ) {
                    floatValue.forEachIndexed { index, value ->
                        drawArc(
                            color = colors[index],
                            lastValue,
                            value,
                            useCenter = false,
                            style = Stroke(chartBarWidth.toPx(), cap = StrokeCap.Butt)
                        )
                        lastValue += value
                    }
                }
            }
        }
    }

}


@Composable
fun DetailsPieChart(
    modifier: Modifier = Modifier,
    data: Map<String, Int>,
    colors: List<Color>,
    totalSum: Int
) {
    Column(
        modifier = modifier
    ) {
        data.values.forEachIndexed { index, value ->
            DetailsPieChartItem(
                data = Pair(data.keys.elementAt(index), value),
                color = colors[index],
                totalSum = totalSum
            )
            Spacer(modifier = Modifier.height(2.dp))
        }
    }
}

@Composable
fun DetailsPieChartItem(
    data: Pair<String, Int>,
    height: Dp = 16.dp,
    color: Color,
    totalSum: Int
) {
    Surface(
        modifier = Modifier
            .padding(
                vertical = 3.dp,
                horizontal = 0.dp
            ),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = color,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .size(height)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = data.first,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = color
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "${data.second} (${(100*data.second.toFloat() / totalSum.toFloat()).toInt()}%)",
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color.White
                )
            }

        }
    }
}