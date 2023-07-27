package com.tappchart.area

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.tapptitude.tappchart.ui.AxisStyle

object AreaChartDefaults {

    data class LineChartStyle internal constructor(
        val labelStyle: TextStyle,
        val axisStyle: AxisStyle,
        val pointBrush: Brush,
        val pointRadius: Dp,
        val lineBrush: Brush,
        val lineWidth: Dp,
        val linePathEffect: PathEffect? = null
    )

    data class AreaChartStyle internal constructor(
        val labelStyle: TextStyle,
        val axisStyle: AxisStyle,
        val pointBrush: Brush,
        val pointRadius: Dp,
        val lineBrush: Brush,
        val lineWidth: Dp,
        val linePathEffect: PathEffect? = null,
        val fillStyle: Brush,
    )

    data class StackedAreaChartStyle internal constructor(
        val labelStyle: TextStyle,
        val axisStyle: AxisStyle,
        val pointRadius: Dp,
        val lineBrush: Brush,
        val lineWidth: Dp,
        val linePathEffect: PathEffect? = null,
    )

    fun lineChartStyle(
        labelStyle: TextStyle = TODO(),
        axisStyle: AxisStyle = TODO(),
        pointBrush: Brush = TODO(),
        pointRadius: Dp = TODO(),
        lineBrush: Brush = TODO(),
        lineWidth: Dp = TODO(),
        linePathEffect: PathEffect? = null
    ): LineChartStyle = TODO()

    fun areaChartStyle(
        labelStyle: TextStyle = TODO(),
        axisStyle: AxisStyle = TODO(),
        pointBrush: Brush = TODO(),
        pointRadius: Dp = TODO(),
        lineBrush: Brush = TODO(),
        lineWidth: Dp = TODO(),
        linePathEffect: PathEffect? = null,
        fillStyle: Brush = TODO(),
    ): AreaChartStyle = TODO()

    fun stackedAreaChartStyle(
        labelStyle: TextStyle = TODO(),
        axisStyle: AxisStyle = TODO(),
        pointRadius: Dp = TODO(),
        lineWidth: Dp = TODO(),
        linePathEffect: PathEffect? = null,
    ): StackedAreaChartStyle = TODO()
}