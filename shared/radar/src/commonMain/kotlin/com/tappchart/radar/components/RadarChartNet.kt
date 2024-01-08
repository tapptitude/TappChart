package com.tappchart.radar.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.isSpecified
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import com.tappchart.radar.Constants.CIRCLE_DEGREE
import com.tappchart.radar.Constants.FIRST_ELEMENT_POSITION
import com.tappchart.radar.math.PointCalculator
import com.tappchart.radar.model.CircularNetStyle
import com.tappchart.radar.model.RingsStyle

internal fun DrawScope.radarChartNet(
    pointsCount: Int,
    circleRadius: Float,
    startAngleOffset: Float = 0f,
    netStyle: CircularNetStyle,
) {

    drawNet(pointsCount, circleRadius, startAngleOffset, netStyle)

    drawNetCrosslines(
        circleRadius = circleRadius,
        pointsCount = pointsCount,
        startAngleOffset = startAngleOffset,
        netStyle = netStyle,
    )
}

private fun DrawScope.drawNet(
    pointsCount: Int,
    circleRadius: Float,
    startAngleOffset: Float = 0f,
    netStyle: CircularNetStyle,
) {
    val spaceBetweenLayers = circleRadius / netStyle.ringsCount
    for (i in 0 until netStyle.ringsCount) {
        // we start from 1, because if we'd start from 0, it would be just a point in the middle
        when (netStyle.ringsStyle) {
            RingsStyle.ROUNDED -> drawRoundedNet(
                radius = (i + 1) * spaceBetweenLayers,
                startAngleOffset = startAngleOffset,
                netStyle = netStyle,
            )
            RingsStyle.POLYGONS -> drawPolygonalNet(
                radius = (i + 1) * spaceBetweenLayers,
                pointsCount = pointsCount,
                startAngleOffset = startAngleOffset,
                netStyle = netStyle
            )

        }
    }
}

private fun DrawScope.drawRoundedNet(
    radius: Float,
    startAngleOffset: Float,
    netStyle: CircularNetStyle
) {
    // Even if we draw circles, we must rotate the circles as well
    //  Otherwise, in animations, if we're using a Dashed path, nothing will happen
    rotate(startAngleOffset) {
        drawCircle(
            color = netStyle.color,
            radius = radius,
            style = netStyle.ringsDrawStyle,
            center = this.center,
        )
    }
}

private fun DrawScope.drawPolygonalNet(
    radius: Float,
    pointsCount: Int,
    startAngleOffset: Float,
    netStyle: CircularNetStyle
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
        cap = netStyle.linesDrawStyle.cap,
        pathEffect = netStyle.linesDrawStyle.pathEffect,
    )
}

private fun DrawScope.drawNetCrosslines(
    circleRadius: Float,
    pointsCount: Int,
    startAngleOffset: Float,
    netStyle: CircularNetStyle
) {
    val degreesPerAngle: Double = CIRCLE_DEGREE / pointsCount
    val spaceBetweenRings = circleRadius / netStyle.ringsCount

    for (i in FIRST_ELEMENT_POSITION..pointsCount) {
        val angle = startAngleOffset + degreesPerAngle * i

        val startOffset: Offset = if (netStyle.connectInCenter) {
            Offset(
                x = size.width / 2f,
                y = size.height / 2f
            )
        } else {
            Offset(
                x = size.width / 2f + PointCalculator.xCoordinateOnCircle(angle, radius = spaceBetweenRings),
                y = size.height / 2f + PointCalculator.yCoordinateOnCircle(angle, radius = spaceBetweenRings),
            )
        }

        val endOffset = Offset(
            x = size.width / 2 + PointCalculator.xCoordinateOnCircle(angle = angle, radius = circleRadius),
            y = size.height / 2 + PointCalculator.yCoordinateOnCircle(angle = angle, radius = circleRadius)
        )

        drawLine(
            color = netStyle.color,
            strokeWidth = netStyle.width,
            start = startOffset,
            end = endOffset,
            pathEffect = netStyle.linesDrawStyle.pathEffect,
        )
    }
}
