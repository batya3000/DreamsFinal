package com.android.batya.dreams.screens.stats

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.android.batya.dreams.R
import com.android.batya.dreams.ui.theme.CardDarkerBackgroundColor
import com.himanshoe.charty.bar.BarChart
import com.himanshoe.charty.bar.config.BarConfig
import com.himanshoe.charty.bar.model.BarData
import com.himanshoe.charty.common.axis.AxisConfig
import com.himanshoe.charty.pie.PieChart
import com.himanshoe.charty.pie.config.PieData

@Composable
fun StatisticsScreen(
//    name: String,
//    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 35.dp,
                bottom = 20.dp
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier
            .fillMaxWidth(),
            //.height(500.dp),
            shape = RoundedCornerShape(10.dp),
            //border = BorderStroke(width = 0.5.dp, color = BorderColor),
            backgroundColor = CardDarkerBackgroundColor,
            elevation = 0.dp
        ) {
            PieChart(
                modifier = Modifier.size(100.dp),
                pieData = listOf(
                    PieData(
                        data = 0.7f,
                        color = Color.Cyan
                    ),
                    PieData(
                        data = 2f,
                        color = Color.Green
                    ),
                )
                //animation = simpleChartAnimation(),
                //sliceDrawer = SimpleSliceDrawer()
            )

        }
        BarChart(
            modifier = Modifier.width(175.dp).height(100.dp),
            onBarClick = {},
            colors = listOf(Color.White, Color.DarkGray),
            barData = listOf(
                BarData(1f, 3f),
                BarData(2f, 5f),
                BarData(3f, 1f),
                BarData(4f, 4f),
                BarData(1f, 3f),
                BarData(2f, 5f),
                BarData(3f, 1f),
                BarData(4f, 4f),
            ),
            axisConfig = AxisConfig(
                xAxisColor = Color.LightGray,
                showAxis = true,
                isAxisDashed = false,
                showUnitLabels = true,
                showXLabels = true,
                yAxisColor = Color.LightGray,
                textColor = Color.White
            ),
        )
    }
}