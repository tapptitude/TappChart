package com.tappchart.radar.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.isSpecified
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.tappchart.radar.Constants.CIRCLE_DEGREE
import com.tappchart.radar.Constants.FIRST_ELEMENT_POSITION
import com.tappchart.radar.math.PointCalculator
import com.tappchart.radar.model.NetStyle

internal fun DrawScope.radarChartNet(
    pointsCount: Int,
    circleRadius: Float,
    startAngleOffset: Float = 0f,
    netStyle: NetStyle,
) {
    val spaceBetweenLayers = (circleRadius - netStyle.interiorRadius) / netStyle.layersCount.dec()
    for (i in 0 until netStyle.layersCount) {
        drawNetCircle(
            radius = netStyle.interiorRadius + i * spaceBetweenLayers,
            pointsCount = pointsCount,
            startAngleOffset = startAngleOffset,
            netStyle = netStyle
        )
    }

    drawNetLines(
        pointsCount = pointsCount,
        spaceBetweenLayers = spaceBetweenLayers,
        startAngleOffset = startAngleOffset,
        netStyle = netStyle
    )
}

private fun DrawScope.drawNetCircle(
    radius: Float,
    pointsCount: Int,
    startAngleOffset: Float,
    netStyle: NetStyle
) {
    val degreesPerAngle: Double = CIRCLE_DEGREE / pointsCount
    var startOffset = Offset.Unspecified
    var previousOffset = Offset.Unspecified
    for (i in FIRST_ELEMENT_POSITION..pointsCount) {
        val angle = startAngleOffset + degreesPerAngle * i

        val offset = Offset(
            x = size.width / 2 + PointCalculator.xCoordinateOnCircle(angle = angle, radius = radius),
            y = size.height / 2 + PointCalculator.yCoordinateOnCircle(angle = angle, radius = radius)
        )
        if (i == FIRST_ELEMENT_POSITION) {
            startOffset = offset
        }

        if (previousOffset.isSpecified) {
            drawLine(
                color = netStyle.color,
                strokeWidth = netStyle.width,
                start = previousOffset,
                end = offset
            )
        }

        previousOffset = offset
    }

    drawLine(
        color = netStyle.color,
        strokeWidth = netStyle.width,
        start = previousOffset,
        end = startOffset,
    )
}

private fun DrawScope.drawNetLines(
    pointsCount: Int,
    spaceBetweenLayers: Float,
    startAngleOffset: Float,
    netStyle: NetStyle
) {
    val degreesPerAngle: Double = CIRCLE_DEGREE / pointsCount

    for (i in FIRST_ELEMENT_POSITION..pointsCount) {
        val angle = startAngleOffset + degreesPerAngle * i
        val endRadius = netStyle.interiorRadius + (netStyle.layersCount - 1) * spaceBetweenLayers

        val startOffset = Offset(
            x = size.width / 2f,
            y = size.height / 2f
        )

        val endOffset = Offset(
            x = size.width / 2 + PointCalculator.xCoordinateOnCircle(angle = angle, radius = endRadius),
            y = size.height / 2 + PointCalculator.yCoordinateOnCircle(angle = angle, radius = endRadius)
        )

        drawLine(
            color = netStyle.color,
            strokeWidth = netStyle.width,
            start = startOffset,
            end = endOffset,
        )
    }
}
