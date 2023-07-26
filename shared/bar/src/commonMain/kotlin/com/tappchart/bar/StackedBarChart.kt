package com.tappchart.bar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.tappchart.bar.model.BarEntrySet
import com.tapptitude.tappchart.model.AxisInterval
import com.tapptitude.tappchart.model.DataLabel
import com.tapptitude.tappchart.model.ValueLabel
import com.tapptitude.tappchart.ui.AxisStyle
import com.tapptitude.tappchart.ui.BackgroundStyle


@Composable
fun StackedBarChart(
    modifier: Modifier = Modifier,
    data: List<BarEntrySet>,
    yAxisInterval: AxisInterval,
    verticalLabels: List<ValueLabel>,
    labelStyle: TextStyle,
    axisStyle: AxisStyle? = null,
    background: List<BackgroundStyle> = listOf(),
    barColor: Map<DataLabel, Color>,
) {
}
