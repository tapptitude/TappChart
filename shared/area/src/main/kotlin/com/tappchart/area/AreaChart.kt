package com.tappchart.area

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tappchart.area.model.ChartPoint
import com.tapptitude.tappchart.model.AxisInterval
import com.tapptitude.tappchart.model.ValueLabel
import com.tapptitude.tappchart.ui.BackgroundStyle

@Composable
fun AreaChart(
    modifier: Modifier = Modifier,
    data: List<ChartPoint>,
    xAxisLabels: List<ValueLabel>,
    yAxisLabels: List<ValueLabel>,
    yAxisInterval: AxisInterval,
    xAxisInterval: AxisInterval,
    areaChartStyle: AreaChartDefaults.AreaChartStyle = AreaChartDefaults.areaChartStyle(),
    background: List<BackgroundStyle> = listOf(),
    showPoints: Boolean = false,
    showLine: Boolean = false,
) {
}