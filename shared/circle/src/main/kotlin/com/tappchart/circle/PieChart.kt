package com.tappchart.circle

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tappchart.circle.model.Slice

/**
 * @param angleOffset by default, drawing starts from 12 o'clock.
 * You can provide a value, [0, 359] to offset the start drawing position
 */
@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    slices: List<Slice>,
    angleOffset: Float = 0f,
    marginBetweenSlices: Dp = 0.dp,
    labelStyle: TextStyle,
) {
    CircleChart(modifier, slices, angleOffset, marginBetweenSlices)
}

@Composable
fun CircleChart(
    modifier: Modifier,
    slices: List<Slice>,
    startAngle: Float,
    marginBetweenSlices: Dp
) {
    val arcStroke: Stroke = with(LocalDensity.current) {
        Stroke(
            width = marginBetweenSlices.toPx(),
            cap = StrokeCap.Round,
        )
    }
    var angle = startAngle - 90F
    Canvas(modifier.padding(4.dp)) {
        slices.forEach { slice ->
            val sweepAngle = 360 * slice.percentage / 100
            drawArc(
                color = slice.color,
                useCenter = true,
                startAngle = angle,
                sweepAngle = sweepAngle,
                style = Fill
            )
            drawArc(
                color = Color.White,
                useCenter = true,
                startAngle = angle,
                sweepAngle = sweepAngle,
                style = arcStroke
            )
            angle += sweepAngle
        }
    }
}