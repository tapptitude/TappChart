package com.tappchart.area


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.tappchart.area.model.StackedAreaEntry
import com.tapptitude.tappchart.model.AxisInterval
import com.tapptitude.tappchart.model.DataLabel
import com.tapptitude.tappchart.model.ValueLabel
import com.tapptitude.tappchart.ui.BackgroundStyle

@Composable
fun StackedAreaChart(
    modifier: Modifier = Modifier,
    data: List<StackedAreaEntry>,
    xAxisLabels: List<ValueLabel>,
    yAxisLabels: List<ValueLabel>,
    yAxisInterval: AxisInterval,
    xAxisInterval: AxisInterval,
    stackedAreaChartStyle: AreaChartDefaults.StackedAreaChartStyle = AreaChartDefaults.stackedAreaChartStyle(),
    background: List<BackgroundStyle> = listOf(),
    showPoints: Boolean = false,
    showLine: Boolean = false,
    areaColor: Map<DataLabel, Brush>,
    decorationColors: Map<DataLabel, Color>,
) {
}
