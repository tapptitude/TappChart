package com.tappchart.bar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.tappchart.bar.model.Bar
import com.tapptitude.tappchart.model.AxisInterval
import com.tapptitude.tappchart.model.ValueLabel
import com.tapptitude.tappchart.ui.AxisStyle
import com.tapptitude.tappchart.ui.BackgroundStyle


@Composable
fun BarChart(
    modifier: Modifier = Modifier,
    yAxisInterval: AxisInterval,
    data: List<Bar>,
    verticalLabel: List<ValueLabel>,
    labelStyle: TextStyle,
    spacingBetweenBars: Dp,
    axisStyle: AxisStyle,
    background: List<BackgroundStyle> = listOf(),
    barColor: List<Color>,
) {
}