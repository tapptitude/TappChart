package com.tappchart.radar.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.isSpecified
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.tappchart.radar.math.PointCalculator

internal fun DrawScope.radarChartNet(
    netLayersCount: Int,
    anglesCount: Int,
    startAngleOffset: Float = 0f,
    innerSpaceRadius: Float = 0f,
    circleRadius: Float = 0f,
) {
    val spaceBetweenLayers = (circleRadius - innerSpaceRadius) / (netLayersCount - 1)
    for (i in 0 until netLayersCount) {
        drawNetLayer(
            radius = innerSpaceRadius + i * spaceBetweenLayers,
            anglesCount = anglesCount,
            startAngleOffset = startAngleOffset,
        )
    }
}

private fun DrawScope.drawNetLayer(
    radius: Float,
    anglesCount: Int,
    startAngleOffset: Float,
) {
    val degreesPerAngle: Double = 360.0 / anglesCount
    var startOffset = Offset.Unspecified
    var previousOffset = Offset.Unspecified
    for (i in 1..anglesCount) {
        val angle = startAngleOffset + degreesPerAngle * i

        val offset = Offset(
            x = size.width / 2 + PointCalculator.xCoordinateOnCircle(angle = angle, radius = radius),
            y = size.height / 2 + PointCalculator.yCoordinateOnCircle(angle = angle, radius = radius)
        )
        if (i == 1) {
            startOffset = offset
        }

        if (previousOffset.isSpecified) {
            drawLine(
                color = Color.Red,
                strokeWidth = 4f,
                start = previousOffset,
                end = offset
            )
        }

        previousOffset = offset
    }

    drawLine(
        color = Color.Red,
        strokeWidth = 4f,
        start = previousOffset,
        end = startOffset,
    )
}