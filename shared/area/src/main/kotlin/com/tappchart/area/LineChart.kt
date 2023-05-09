package com.tappchart.area

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tappchart.area.model.ChartPoint
import com.tapptitude.tappchart.model.AxisInterval
import com.tapptitude.tappchart.model.ValueLabel
import com.tapptitude.tappchart.ui.BackgroundStyle

@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    data: List<ChartPoint>,
    xAxisLabels: List<ValueLabel>,
    yAxisLabels: List<ValueLabel>,
    yAxisInterval: AxisInterval,
    xAxisInterval: AxisInterval,
    lineChartStyle: AreaChartDefaults.LineChartStyle = AreaChartDefaults.lineChartStyle(),
    background: List<BackgroundStyle> = listOf(),
) {
}
