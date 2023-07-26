package com.tappchart.bar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.tappchart.bar.model.BarEntrySet
import com.tapptitude.tappchart.model.AxisInterval
import com.tapptitude.tappchart.model.ValueLabel
import com.tapptitude.tappchart.ui.AxisStyle
import com.tapptitude.tappchart.ui.BackgroundStyle


@Composable
fun GroupedBarChart(
    modifier: Modifier = Modifier,
    data: List<BarEntrySet>,
    axisInterval: AxisInterval,
    verticalLabels: List<ValueLabel>,
    labelStyle: TextStyle,
    spacingBetweenBars: Dp,
    axisStyle: AxisStyle? = null,
    background: List<BackgroundStyle> = listOf(),
) {

}